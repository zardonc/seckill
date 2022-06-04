package org.seckill.entity;

/**
 * 功能描述: 控制并发情况下，单个用户请求与响应，线程间通讯
 * 〈〉
 * @Param:
 * @Return:
 * @Author: lang
 */
public class OrderRequestOps {
    private OrderRequest orderRequest;
    private OrderRequestResult orderRequestResult;

    public OrderRequestOps() {
    }

    public OrderRequestOps(OrderRequest orderRequest, OrderRequestResult orderRequestResult) {
        this.orderRequest = orderRequest;
        this.orderRequestResult = orderRequestResult;
    }

    public OrderRequestOps(OrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }

    public OrderRequest getOrderRequest() {
        return orderRequest;
    }

    public void setOrderRequest(OrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }

    public OrderRequestResult getOrderRequestResult() {
        return orderRequestResult;
    }

    public void setOrderRequestResult(OrderRequestResult orderRequestResult) {
        this.orderRequestResult = orderRequestResult;
    }
}
