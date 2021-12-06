package org.seckill.base;

/**
 * 普通返回类
 * 1 系统系列错误
 * 2 注册登录系列错误
 * 3 check 系列错误
 * 4 秒杀错误
 * 5 订单错误
 */
public enum StatusCode {
    SUCCESS(0, "成功"),
    FAIL(-1, "失败"),
    EXCEPTION(-1, "系统异常"),
    INVALID_PARAM(10000, "参数错误"),
    SYSTEM_ERROR(10001, "系统错误"),
    SYSTEM_DB_ERROR(10002, "数据库系统错误"),
    DATA_EXISTS(10003, "数据已存在"),
    DATA_NOT_EXISTS(10004, "数据不存在"),

    /**
     * 注册登录
     */
    REGISTER_SUCCESS(20000, "注册成功!"),
    REGISTER_FAILED(200001, "注册失败!"),
    VERIFY_CODE_FAILED(200002, "验证码不一致!"),

    /**
     * check
     */
    BIND_ERROR(30001, "参数校验异常：%s"),
    ACCESS_LIMIT_REACHED(30002, "非法请求!"),
    REQUEST_ILLEGAL(30004, "访问过于频繁!"),
    SESSION_ERROR(30005, "Session不存在或者已经失效!"),
    PASSWORD_EMPTY(30006, "登录密码不能为空!"),
    MOBILE_EMPTY(30007, "手机号不能为空!"),
    MOBILE_ERROR(30008, "手机号格式错误!"),
    MOBILE_NOT_EXIST(30009, "手机号不存在!"),
    PASSWORD_ERROR(30010, "密码错误!"),
    USER_NOT_EXIST(30011, "用户不存在！"),
    USER_NOT_LOGIN(30012, "用户未登录!"),

    /**
     * 订单模块
     */
    ORDER_NOT_EXIST(60001, "订单不存在"),
    GOOD_NOT_EXIST(60002, "商品不存在"),

    /**
     * 秒杀模块
     */
    SECKILL_OVER(40001, "商品已经秒杀完毕"),
    SECKILL_REPEATED(40002, "不能重复秒杀"),
    SECKILL_REDUCE_FAIL(40004, "减库存失败"),
    SECKILL_FAIL(40003, "秒杀失败"),
    SECKILL_RESULT_FAIL(40005, "获取秒杀结果失败"),
    SECKILL_NOT_START(40006, "秒杀还未开始"),
    SECKILL_ING(40007, "秒杀进行中"),
    SECKILL_END(40008, "秒杀已经结束"),
    SECKILL_DEDUCT_FAIL(40009, "扣减库存失败"),
    SECKILL_MQ_SEND_FAIL(40010, "MQ信息发送失败!"),
    SECKILL_QUEUE_ING(40011, "排队中请耐心等待!");

    private Integer code;
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
