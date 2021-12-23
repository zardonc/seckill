package org.seckill.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 功能描述: 秒杀列表商品信息
 * 〈〉
 * @Param:
 * @Return:
 * @Author: lang
 */
public class ProdsVo {

    private BigDecimal seckillPrice;

    private Integer stockCount;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long id;

    private String prodName;

    private String prodTitle;

    private String prodImg;

    private BigDecimal prodPrice;

    private Integer prodStock;

    private String prodDetail;


}
