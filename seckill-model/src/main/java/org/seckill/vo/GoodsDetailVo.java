package org.seckill.vo;

import org.seckill.entity.MiaoshaUser;

/**
 * 功能描述: 秒杀详情页商品信息
 * 〈〉
 * @Param:
 * @Return:
 * @Author: lang
 */
public class GoodsDetailVo {
    private Integer miaoshaStatus = 0;
    private Integer remainSeconds = 0;
    private GoodsVo goods;
    private MiaoshaUser user;

    public Integer getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(Integer miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public Integer getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(Integer remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public GoodsVo getGoods() {
        return goods;
    }

    public void setGoods(GoodsVo goods) {
        this.goods = goods;
    }

    public MiaoshaUser getUser() {
        return user;
    }

    public void setUser(MiaoshaUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "GoodsDetailVo{" +
                "miaoshaStatus=" + miaoshaStatus +
                ", remainSeconds=" + remainSeconds +
                ", goods=" + goods +
                ", user=" + user +
                '}';
    }
}
