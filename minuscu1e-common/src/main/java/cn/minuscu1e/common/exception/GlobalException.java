package cn.minuscu1e.common.exception;

import cn.minuscu1e.common.constant.SystemConstant;

/**
 * @author 全局异常处理
 */
public class GlobalException extends Exception {

    private Long code;
    private String msg;

    public GlobalException(String msg) {
        super(msg);
        this.msg = msg;
        this.code = SystemConstant.ERROR_PARAM_CODE;
    }

    public GlobalException(Long code, String msg, Exception e) {
        super(e);
        this.code = code;
        this.msg = msg;
    }

    public GlobalException(Exception e) {
        super(e);
    }

    public Long getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

}
