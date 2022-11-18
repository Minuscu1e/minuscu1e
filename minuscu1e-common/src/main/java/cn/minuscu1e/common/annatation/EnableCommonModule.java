package cn.minuscu1e.common.annatation;

import cn.minuscu1e.common.config.CommonConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author saling
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(CommonConfiguration.class)
public @interface EnableCommonModule {

}
