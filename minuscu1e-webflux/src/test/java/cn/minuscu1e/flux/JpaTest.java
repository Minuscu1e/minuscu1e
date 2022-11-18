package cn.minuscu1e.flux;

import cn.minuscu1e.flux.dao.SystemUserDao;
import cn.minuscu1e.flux.pojo.SystemUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class JpaTest {

    @Autowired
    SystemUserDao systemUserDao;


    /**
     * 清空表
     */
    @Test
    public void selectTest() {
        systemUserDao.deleteAll();

        final List<SystemUser> all = systemUserDao.findAll();
        Assert.assertEquals(all.size(), 0);
    }

    @Test
//    @Transactional
    public void addNew() {
        final SystemUser systemUser = new SystemUser();
        final String id = "SS202211171013000001";
        systemUser.setId(id);
        systemUser.setNickname("superAdmin");
        systemUser.setUsername("root");
        systemUser.setPassword("123456");
        systemUser.setSalt("1024");
        systemUserDao.save(systemUser);

        final Optional<SystemUser> one = systemUserDao.findById(id);
        Assert.assertEquals(systemUser, one.orElse(null));
    }


    @Test
    public void findByUsernameTest() {
        final SystemUser root = systemUserDao.findByUsername("root");
        Assert.assertEquals(root.getUsername(), "root");
    }


    @Test
    public void finByNickNameLike() {
        final List<SystemUser> users = systemUserDao.findByNicknameLike("%s%");
        Assert.assertEquals(users.size(), 1);
    }

    @Test
    public void moreCondition() {
        final List<SystemUser> root = systemUserDao.findByNicknameLikeAndUsername("%s%", "root");


        final SystemUser user = systemUserDao.findByUsernameAndPassword("Tt", "tt");
        System.out.println(user);
    }

}
