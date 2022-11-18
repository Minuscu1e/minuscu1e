package cn.minuscu1e.file;

import cn.minuscu1e.common.annatation.EnableCommonModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCommonModule
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
