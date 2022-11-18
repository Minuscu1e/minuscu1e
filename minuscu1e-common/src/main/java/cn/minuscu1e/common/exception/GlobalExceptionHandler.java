package cn.minuscu1e.common.exception;

import cn.minuscu1e.common.constant.SystemConstant;
import cn.minuscu1e.common.vo.Res;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一的异常处理
 *
 * @author saling
 */
@RestControllerAdvice(basePackages = "cn.minuscu1e")
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = GlobalException.class)
    public Res<String> globalExceptionHandler(GlobalException e) {
        logger.error(e.getMsg());
        e.printStackTrace();
        return Res.fail(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public Res<String> exceptionHandler(Exception e) {
        logger.error(e.getMessage());
        e.printStackTrace();
        return Res.fail(SystemConstant.SERVER_ERROR_CODE, SystemConstant.SERVER_ERROR_MSG);
    }
}
