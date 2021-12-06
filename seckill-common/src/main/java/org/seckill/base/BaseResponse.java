package org.seckill.base;

/**
 * 功能描述: 通用返回实体
 * 〈〉
 * @Param:
 * @Return:
 * @Author: lang
 */
public class BaseResponse<T> {
    private Integer code;
    private String msg;
    private T data;

    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    public BaseResponse(StatusCode statusCode, T data) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }

    public BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> BaseResponse<T> Success() {
        return new BaseResponse<>(StatusCode.SUCCESS);
    }

    public static <T> BaseResponse<T> SuccessWithData(T data) {
        return new BaseResponse<>(StatusCode.SUCCESS, data);
    }

    /**
     * 通用错误返回，传入指定的错误枚举
     *
     * @param statusCode
     * @return
     */
    public static <T> BaseResponse<T> error(StatusCode statusCode) {
        return new BaseResponse<>(statusCode);
    }

    /**
     * 通用错误返回，传入指定的错误枚举
     *
     * @param statusCode
     * @return
     */
    public static <T> BaseResponse<T> errorWithData(StatusCode statusCode, T data) {
        return new BaseResponse<>(statusCode, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
