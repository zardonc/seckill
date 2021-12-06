package org.seckill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.util.Date;

@TableName(value = "miaosha_goods")
public class MiaoshaGoods {
    /**
     * 秒杀的商品表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品Id
     */
    @TableField(value = "goods_id")
    private Long goodsId;

    /**
     * 秒杀价
     */
    @TableField(value = "miaosha_price")
    private BigDecimal miaoshaPrice;

    /**
     * 库存数量
     */
    @TableField(value = "stock_count")
    private Integer stockCount;

    /**
     * 秒杀开始时间
     */
    @TableField(value = "start_date")
    private Date startDate;

    /**
     * 秒杀结束时间
     */
    @TableField(value = "end_date")
    private Date endDate;

    /**
     * 获取秒杀的商品表
     *
     * @return id - 秒杀的商品表
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置秒杀的商品表
     *
     * @param id 秒杀的商品表
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取商品Id
     *
     * @return goods_id - 商品Id
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品Id
     *
     * @param goodsId 商品Id
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取秒杀价
     *
     * @return miaosha_price - 秒杀价
     */
    public BigDecimal getMiaoshaPrice() {
        return miaoshaPrice;
    }

    /**
     * 设置秒杀价
     *
     * @param miaoshaPrice 秒杀价
     */
    public void setMiaoshaPrice(BigDecimal miaoshaPrice) {
        this.miaoshaPrice = miaoshaPrice;
    }

    /**
     * 获取库存数量
     *
     * @return stock_count - 库存数量
     */
    public Integer getStockCount() {
        return stockCount;
    }

    /**
     * 设置库存数量
     *
     * @param stockCount 库存数量
     */
    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    /**
     * 获取秒杀开始时间
     *
     * @return start_date - 秒杀开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置秒杀开始时间
     *
     * @param startDate 秒杀开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取秒杀结束时间
     *
     * @return end_date - 秒杀结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置秒杀结束时间
     *
     * @param endDate 秒杀结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}