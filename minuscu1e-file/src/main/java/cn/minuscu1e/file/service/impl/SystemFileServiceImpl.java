package cn.minuscu1e.file.service.impl;

import cn.minuscu1e.file.dao.SystemFileDao;
import cn.minuscu1e.file.pojo.SystemFile;
import cn.minuscu1e.file.service.ISystemFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【system_file】的数据库操作Service实现
 * @createDate 2022-11-07 21:11:57
 */
@Service
public class SystemFileServiceImpl extends ServiceImpl<SystemFileDao, SystemFile>
        implements ISystemFileService {

}
