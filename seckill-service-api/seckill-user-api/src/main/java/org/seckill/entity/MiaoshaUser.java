package org.seckill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

@TableName(value = "miaosha_user")
public class MiaoshaUser {
    /**
     * 用户ID，手机号码
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "nickname")
    private String nickname;

    /**
     * MD5(MD5(pass明文+固定salt) + salt)
     */
    @TableField(value = "`password`")
    private String password;

    @TableField(value = "salt")
    private String salt;

    /**
     * 头像，云存储的ID
     */
    @TableField(value = "head")
    private String head;

    /**
     * 注册时间
     */
    @TableField(value = "register_date")
    private LocalDateTime registerDate;

    /**
     * 上蔟登录时间
     */
    @TableField(value = "last_login_date")
    private LocalDateTime lastLoginDate;

    /**
     * 登录次数
     */
    @TableField(value = "login_count")
    private Integer loginCount;

    /**
     * 获取用户ID，手机号码
     *
     * @return id - 用户ID，手机号码
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户ID，手机号码
     *
     * @param id 用户ID，手机号码
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取MD5(MD5(pass明文+固定salt) + salt)
     *
     * @return password - MD5(MD5(pass明文+固定salt) + salt)
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置MD5(MD5(pass明文+固定salt) + salt)
     *
     * @param password MD5(MD5(pass明文+固定salt) + salt)
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取头像，云存储的ID
     *
     * @return head - 头像，云存储的ID
     */
    public String getHead() {
        return head;
    }

    /**
     * 设置头像，云存储的ID
     *
     * @param head 头像，云存储的ID
     */
    public void setHead(String head) {
        this.head = head;
    }

    /**
     * 获取注册时间
     *
     * @return register_date - 注册时间
     */
    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    /**
     * 设置注册时间
     *
     * @param registerDate 注册时间
     */
    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * 获取上蔟登录时间
     *
     * @return last_login_date - 上蔟登录时间
     */
    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * 设置上蔟登录时间
     *
     * @param lastLoginDate 上蔟登录时间
     */
    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * 获取登录次数
     *
     * @return login_count - 登录次数
     */
    public Integer getLoginCount() {
        return loginCount;
    }

    /**
     * 设置登录次数
     *
     * @param loginCount 登录次数
     */
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }
}
