package org.seckill.entity;

import javax.validation.constraints.NotNull;

public class SecKillDTO {
    // 秒杀ID
    @NotNull
    private Integer killId;
    // 用户ID
    @NotNull
    private Integer userId;

    public Integer getKillId() {
        return killId;
    }

    public void setKillId(Integer killId) {
        this.killId = killId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SecKillDTO{" +
                "killId=" + killId +
                ", userId=" + userId +
                '}';
    }
}
