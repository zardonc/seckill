package org.seckill.entity;

/**
 * 功能描述: 订单扣减结果
 * 〈〉
 * @Param:
 * @Return:
 * @Author: lang
 */
public class OrderRequestResult {
    // 结果
    private Boolean success;
    // 结果描述
    private String msg;

    public OrderRequestResult() {
    }

    public OrderRequestResult(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "OrderRequestResult{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}
