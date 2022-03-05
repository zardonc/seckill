package org.seckill.product.vo;

import lombok.Data;

/**
 * 功能描述: 秒杀详情页商品信息
 * 〈〉
 * @Param:
 * @Return:
 * @Author: lang
 */
@Data
public class GoodsDetailVo {
    private Integer miaoshaStatus = 0;
    private Integer remainSeconds = 0;
    private GoodsVo goods;
}
