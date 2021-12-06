package org.seckill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value = "mmall_cart")
public class MmallCart {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 商品id
     */
    @TableField(value = "product_id")
    private Integer productId;

    /**
     * 数量
     */
    @TableField(value = "quantity")
    private Integer quantity;

    /**
     * 是否选择,1=已勾选,0=未勾选
     */
    @TableField(value = "`checked`")
    private Integer checked;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取商品id
     *
     * @return product_id - 商品id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置商品id
     *
     * @param productId 商品id
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取数量
     *
     * @return quantity - 数量
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置数量
     *
     * @param quantity 数量
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取是否选择,1=已勾选,0=未勾选
     *
     * @return checked - 是否选择,1=已勾选,0=未勾选
     */
    public Integer getChecked() {
        return checked;
    }

    /**
     * 设置是否选择,1=已勾选,0=未勾选
     *
     * @param checked 是否选择,1=已勾选,0=未勾选
     */
    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}