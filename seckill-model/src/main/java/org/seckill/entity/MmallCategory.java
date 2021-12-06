package org.seckill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value = "mmall_category")
public class MmallCategory {
    /**
     * 类别Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父类别id当id=0时说明是根节点,一级类别
     */
    @TableField(value = "parent_id")
    private Integer parentId;

    /**
     * 类别名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 类别状态1-正常,2-已废弃
     */
    @TableField(value = "`status`")
    private Boolean status;

    /**
     * 排序编号,同类展示顺序,数值相等则自然排序
     */
    @TableField(value = "sort_order")
    private Integer sortOrder;

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
     * 获取类别Id
     *
     * @return id - 类别Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置类别Id
     *
     * @param id 类别Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取父类别id当id=0时说明是根节点,一级类别
     *
     * @return parent_id - 父类别id当id=0时说明是根节点,一级类别
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父类别id当id=0时说明是根节点,一级类别
     *
     * @param parentId 父类别id当id=0时说明是根节点,一级类别
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取类别名称
     *
     * @return name - 类别名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类别名称
     *
     * @param name 类别名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取类别状态1-正常,2-已废弃
     *
     * @return status - 类别状态1-正常,2-已废弃
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置类别状态1-正常,2-已废弃
     *
     * @param status 类别状态1-正常,2-已废弃
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取排序编号,同类展示顺序,数值相等则自然排序
     *
     * @return sort_order - 排序编号,同类展示顺序,数值相等则自然排序
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * 设置排序编号,同类展示顺序,数值相等则自然排序
     *
     * @param sortOrder 排序编号,同类展示顺序,数值相等则自然排序
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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