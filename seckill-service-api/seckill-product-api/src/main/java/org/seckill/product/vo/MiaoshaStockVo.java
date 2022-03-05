package org.seckill.product.vo;

public class MiaoshaStockVo {
    // 秒杀库存总数
    private Integer totalCount;
    // 秒杀开始标记
    private Boolean initStatus;
    // 已秒杀的商品数量
    private Integer seckillCount;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getInitStatus() {
        return initStatus;
    }

    public void setInitStatus(Boolean initStatus) {
        this.initStatus = initStatus;
    }

    public Integer getSeckillCount() {
        return seckillCount;
    }

    public void setSeckillCount(Integer seckillCount) {
        this.seckillCount = seckillCount;
    }

    @Override
    public String toString() {
        return "MiaoshaStockVo{" +
                "totalCount=" + totalCount +
                ", initStatus=" + initStatus +
                ", seckillCount=" + seckillCount +
                '}';
    }
}
