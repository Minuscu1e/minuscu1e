package cn.minuscu1e.flux.dao;

import cn.minuscu1e.flux.pojo.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemUserDao extends JpaRepository<SystemUser, String> {


    SystemUser findByUsername(String username);

    List<SystemUser> findByNicknameLike(String nickName);

    List<SystemUser> findByNicknameLikeAndUsername(String nickName, String username);

    SystemUser findByUsernameAndPassword(String username, String password);

}
