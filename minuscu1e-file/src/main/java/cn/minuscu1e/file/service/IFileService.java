package cn.minuscu1e.file.service;

import cn.minuscu1e.common.exception.GlobalException;
import cn.minuscu1e.common.vo.Res;
import cn.minuscu1e.file.pojo.SystemFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IFileService {

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    Res<Object> upload(MultipartFile file);

    /**
     * 列出所有文件
     *
     * @param filename
     * @return
     */
    Res<List<SystemFile>> list(String filename);

    /**
     * 根据文件 id 获取文件的详情
     *
     * @param id
     * @return
     */
    Res<SystemFile> get(String id);

    /**
     * 预览图片
     *
     * @param id
     * @param request
     * @param response
     * @throws GlobalException
     */
    void preview(String id, HttpServletRequest request, HttpServletResponse response) throws GlobalException;

    /**
     * 删除文件
     *
     * @param id
     * @return
     */
    Res<Void> remove(String id);

    /**
     * 下载文件
     *
     * @param id
     * @param request
     * @param response
     * @throws GlobalException
     */
    void download(String id, HttpServletRequest request, HttpServletResponse response) throws GlobalException;
}
