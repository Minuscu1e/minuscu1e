package cn.minuscu1e.common.util;

import cn.minuscu1e.common.util.pojo.SystemUser;
import org.junit.Test;
import org.springframework.data.util.Lazy;

public class BuilderTest {
    @Test
    public void testConstructor() {
        SystemUser root = new Builder<>(SystemUser::new)
                .with(SystemUser::setUsername, "root")
                .with(SystemUser::setPassword, "123456")
                .build();
        System.out.println(root);
    }
}

