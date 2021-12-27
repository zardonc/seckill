package org.seckill.vo;

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
    private UserVo user;

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

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }
}
