package cn.minuscu1e.file.service.impl;

import cn.minuscu1e.common.constant.SystemConstant;
import cn.minuscu1e.common.exception.GlobalException;
import cn.minuscu1e.common.util.IdUitls;
import cn.minuscu1e.common.util.ResponseUtil;
import cn.minuscu1e.common.vo.Res;
import cn.minuscu1e.file.config.MinioClientWrapper;
import cn.minuscu1e.file.pojo.SystemFile;
import cn.minuscu1e.file.service.IFileService;
import cn.minuscu1e.file.service.ISystemFileService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mysql.cj.util.StringUtils;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements IFileService {
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    private MinioClient minioClient;

    private String bucketName;

    private ISystemFileService systemFileService;


    public FileServiceImpl(MinioClientWrapper minioClient, ISystemFileService systemFileService) {
        this.minioClient = minioClient.minioClient();
        this.bucketName = minioClient.getBucketName();
        this.systemFileService = systemFileService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Res<Object> upload(MultipartFile file) {
        final SystemFile systemFile = buildSystemFilePojo(file);

        try (InputStream inputStream = file.getInputStream()) {
            // 保存到 minio 中
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .stream(inputStream, systemFile.getSize(), -1)
                            .object(systemFile.getSystemFilename())
                            .bucket(bucketName)
                            .contentType(systemFile.getType())
                            .build()
            );

            // 保存到 DB 中
            final boolean save = systemFileService.save(systemFile);
            if (save) {
                return Res.success();
            }
            throw new Exception("Upload file fail");
        } catch (Exception e) {
            logger.error(e.getMessage());
            try {
                minioClient.removeObject(RemoveObjectArgs.builder()
                        .bucket(bucketName)
                        .object(systemFile.getSystemFilename())
                        .build());
            } catch (Exception exp) {
                logger.error(exp.getMessage());
            }
        }
        return Res.fail();
    }

    @Override
    public Res<List<SystemFile>> list(String filename) {
        final List<SystemFile> files = systemFileService.list(new QueryWrapper<SystemFile>()
                .like(!StringUtils.isNullOrEmpty(filename), "filename", filename)
                .eq("is_delete", false)
        );
        return Res.success(files);
    }

    @Override
    public Res<SystemFile> get(String id) {
        final SystemFile systemFile = systemFileService.getById(id);
        return Res.success(systemFile);
    }

    @Override
    public void preview(String id, HttpServletRequest request, HttpServletResponse response)
            throws GlobalException {
        final SystemFile systemFile = systemFileService.getById(id);
        if (null == systemFile || systemFile.getIsDelete() || !systemFile.getType().contains(SystemConstant.IMAGE)) {
            throw new GlobalException("Error file id or not allowed file type.");
        }
        final String systemFilename = systemFile.getSystemFilename();
        try {
            ResponseUtil.downloadOrPreviewSetting(response, systemFilename,
                    systemFile.getSize(), systemFile.getType(), false);
            final InputStream in = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(systemFilename).build());
            final ServletOutputStream out = response.getOutputStream();
            write(in, out);
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    @Override
    public void download(String id, HttpServletRequest request, HttpServletResponse response) throws GlobalException {
        final SystemFile systemFile = systemFileService.getById(id);
        if (null == systemFile || systemFile.getIsDelete()) {
            throw new GlobalException(String.format("Error file id %s. ", id));
        }
        final String filename = systemFile.getFilename();

        try {
            ResponseUtil.downloadOrPreviewSetting(response, filename,
                    systemFile.getSize(), SystemConstant.DOWNLOAD_CONTENT_TYPE, true);

            final InputStream in = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(systemFile.getSystemFilename()).build());
            final ServletOutputStream out = response.getOutputStream();
            write(in, out);
        } catch (IOException | ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new GlobalException(e.getMessage());
        }
    }

    @Override
    public Res<Void> remove(String id) {
        final boolean u = systemFileService.update(
                new UpdateWrapper<SystemFile>()
                        .set("is_delete", true)
                        .eq("id", id)
        );
        return u ? Res.success() : Res.fail(SystemConstant.UPDATE_FAIL_CODE, SystemConstant.UPDATE_FAIL_MSG);
    }


    private SystemFile buildSystemFilePojo(MultipartFile file) {
        SystemFile systemFile = new SystemFile();
        final String fileId = SystemConstant.SYSTEM_ID_PREFIX + IdUitls.generateId();
        systemFile.setId(fileId);
        // 获取文件名
        final String filename = file.getOriginalFilename();

        systemFile.setFilename(filename);
        systemFile.setType(file.getContentType());
        // 获取文件后缀
        assert filename != null;
        final int dotIdx = filename.lastIndexOf(SystemConstant.DOT);
        String ext = "";
        if (dotIdx != -1 && dotIdx < filename.length() - 1) {
            ext = filename.substring(dotIdx + 1);
        }
        systemFile.setExt(ext);
        // 设置文件大小
        systemFile.setSize(file.getSize());
        // 如果是 minio 存储要设置 bucketName
        systemFile.setBucketName(bucketName);

        // 生成系统相关
        systemFile.setSystemFilename(fileId + SystemConstant.DOT + ext);
        systemFile.setPath(SystemConstant.URL_SEPARATOR + fileId + SystemConstant.DOT + ext);
        systemFile.setCreateTime(new Date());
        systemFile.setIsDelete(false);

        return systemFile;
    }

    private void write(InputStream in, OutputStream os) throws IOException {
        try {
            byte[] bytes = new byte[1024 * 16];
            int len = 0;
            while ((len = in.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
        } finally {
            in.close();
            os.flush();
            os.close();
        }
    }
}
