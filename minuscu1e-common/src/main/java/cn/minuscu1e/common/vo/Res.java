package cn.minuscu1e.common.vo;

import cn.minuscu1e.common.constant.SystemConstant;

public class Res<T> {

    private Long code;
    private String msg;
    private T data;

    public Res(Long code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Res<T> success(T data) {
        return new Res<T>(SystemConstant.SUCCESS_CODE, SystemConstant.SUCCESS_MSG, data);
    }

    public static <T> Res<T> success() {
        return success(null);
    }

    public static <T> Res<T> fail(Long code, String msg) {
        return new Res<>(code, msg, null);
    }

    public static <T> Res<T> fail() {
        return new Res<>(SystemConstant.FAIL_CODE, SystemConstant.FAIL_MSG, null);
    }


    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
