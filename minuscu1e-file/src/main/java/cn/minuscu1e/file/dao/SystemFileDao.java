package cn.minuscu1e.file.dao;

import cn.minuscu1e.file.pojo.SystemFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 * @description 针对表【system_file】的数据库操作Mapper
 * @createDate 2022-11-07 21:11:57
 * @Entity cn.minuscu1e.file.pojo.SystemFile
 */
@Mapper
public interface SystemFileDao extends BaseMapper<SystemFile> {


}
