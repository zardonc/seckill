package org.seckill.product.controller;

import org.seckill.base.BaseResponse;
import org.seckill.base.StatusCode;
import org.seckill.entity.MiaoshaUser;
import org.seckill.product.service.GoodsService;
import org.seckill.vo.GoodsDetailVo;
import org.seckill.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/api/goods")
public class GoodsController {
    private static final Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Resource
    private GoodsService goodsService;

    // 秒杀商品列表
    @RequestMapping("/to_list")
    @ResponseBody
    public String goodsList(HttpServletRequest request, HttpServletResponse response,
                            MiaoshaUser user, Model model) {
        model.addAttribute("user", user);
        BaseResponse<List<GoodsVo>> goodsListResult = goodsService.goodsVoList();
        log.info("result list {}", goodsListResult.getData());
        model.addAttribute("goodsList", goodsListResult.getData());
        return "goods_list";
    }

    // 秒杀详情
    @RequestMapping("/to_detail")
    @ResponseBody
    public BaseResponse<GoodsDetailVo> goodsDetail(MiaoshaUser user, String goodsId) {
        BaseResponse<GoodsDetailVo> result = goodsService.goodsVoById(Long.valueOf(goodsId));
        log.info("return detail {}", result.getData());
        if (!result.getCode().equals(StatusCode.SUCCESS.getCode())) {
            return result;
        }
        GoodsDetailVo detailVo = result.getData();
        detailVo.setUser(user);
        result.setData(detailVo);
        return result;
    }
}
