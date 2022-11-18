package cn.minuscu1e.file.controller;

import cn.minuscu1e.common.constant.SystemConstant;
import cn.minuscu1e.common.exception.GlobalException;
import cn.minuscu1e.common.vo.Res;
import cn.minuscu1e.file.pojo.SystemFile;
import cn.minuscu1e.file.service.IFileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author saling
 */
@RestController
@RequestMapping(SystemConstant.SYSTEM_BASE_URL + "/file")
public class FileController {

    private IFileService fileService;

    public FileController(IFileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @PostMapping
    public Res<Object> upload(MultipartFile file) {
        // TODO 检查文件是否允许上传
        // 上传文件
        return fileService.upload(file);
    }

    /**
     * 根据文件编号获取文件详细信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Res<SystemFile> get(@PathVariable String id) {
        return fileService.get(id);
    }

    /**
     * 文件预览，目前仅支持图片
     *
     * @param id
     * @param request
     * @param response
     */
    @GetMapping("/preview/{id}")
    public void preview(@PathVariable String id,
                        HttpServletRequest request,
                        HttpServletResponse response) throws GlobalException {
        fileService.preview(id, request, response);
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable String id,
                         HttpServletRequest request,
                         HttpServletResponse response) throws GlobalException {
        fileService.download(id, request, response);
    }

    /**
     * 获取所有已上传的文件
     *
     * @param filename
     * @return
     */
    @GetMapping(value = {"/list/{filename}", "/list"})

    public Res<List<SystemFile>> list(
            @PathVariable(required = false, name = "filename") String filename) {
        return fileService.list(filename);
    }

    // TODO
    // 1. Remove file api true
    // 2. page
    // 3. Global Exception process

    /**
     * 删除文件，假删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Res<Void> remove(@PathVariable("id") String id) {
        return fileService.remove(id);
    }


}
