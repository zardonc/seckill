package org.seckill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName(value = "order_info")
public class OrderInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 商品ID
     */
    @TableField(value = "goods_id")
    private Long goodsId;

    /**
     * 收获地址ID
     */
    @TableField(value = "delivery_addr_id")
    private Long deliveryAddrId;

    /**
     * 冗余过来的商品名称
     */
    @TableField(value = "goods_name")
    private String goodsName;

    /**
     * 商品数量
     */
    @TableField(value = "goods_count")
    private Integer goodsCount;

    /**
     * 商品单价
     */
    @TableField(value = "goods_price")
    private BigDecimal goodsPrice;

    /**
     * 1pc，2android，3ios
     */
    @TableField(value = "order_channel")
    private Byte orderChannel;

    /**
     * 订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成
     */
    @TableField(value = "`status`")
    private Byte status;

    /**
     * 订单的创建时间
     */
    @TableField(value = "create_date")
    private LocalDateTime createDate;

    /**
     * 支付时间
     */
    @TableField(value = "pay_date")
    private LocalDateTime payDate;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取商品ID
     *
     * @return goods_id - 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品ID
     *
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取收获地址ID
     *
     * @return delivery_addr_id - 收获地址ID
     */
    public Long getDeliveryAddrId() {
        return deliveryAddrId;
    }

    /**
     * 设置收获地址ID
     *
     * @param deliveryAddrId 收获地址ID
     */
    public void setDeliveryAddrId(Long deliveryAddrId) {
        this.deliveryAddrId = deliveryAddrId;
    }

    /**
     * 获取冗余过来的商品名称
     *
     * @return goods_name - 冗余过来的商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置冗余过来的商品名称
     *
     * @param goodsName 冗余过来的商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取商品数量
     *
     * @return goods_count - 商品数量
     */
    public Integer getGoodsCount() {
        return goodsCount;
    }

    /**
     * 设置商品数量
     *
     * @param goodsCount 商品数量
     */
    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    /**
     * 获取商品单价
     *
     * @return goods_price - 商品单价
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设置商品单价
     *
     * @param goodsPrice 商品单价
     */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 获取1pc，2android，3ios
     *
     * @return order_channel - 1pc，2android，3ios
     */
    public Byte getOrderChannel() {
        return orderChannel;
    }

    /**
     * 设置1pc，2android，3ios
     *
     * @param orderChannel 1pc，2android，3ios
     */
    public void setOrderChannel(Byte orderChannel) {
        this.orderChannel = orderChannel;
    }

    /**
     * 获取订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成
     *
     * @return status - 订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成
     *
     * @param status 订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取订单的创建时间
     *
     * @return create_date - 订单的创建时间
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * 设置订单的创建时间
     *
     * @param createDate 订单的创建时间
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取支付时间
     *
     * @return pay_date - 支付时间
     */
    public LocalDateTime getPayDate() {
        return payDate;
    }

    /**
     * 设置支付时间
     *
     * @param payDate 支付时间
     */
    public void setPayDate(LocalDateTime payDate) {
        this.payDate = payDate;
    }
}
