package org.seckill.entity;

/**
 * 功能描述: 订单库存扣减请求实体
 * 〈〉
 * @Param:
 * @Return:
 * @Author: lang
 */
public class OrderRequest {
    // 订单号
    private Long orderId;
    // 用户id
    private Long userId;
    // 扣减实体
    private Integer count;

    public OrderRequest() {
    }

    public OrderRequest(Long orderId, Long userId, Integer count) {
        this.orderId = orderId;
        this.userId = userId;
        this.count = count;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", count=" + count +
                '}';
    }
}
