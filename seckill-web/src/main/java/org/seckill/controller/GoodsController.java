package org.seckill.controller;

import org.seckill.base.BaseResponse;
import org.seckill.entity.MiaoshaUser;
import org.seckill.service.GoodsService;
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
public class GoodsController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Resource
    private GoodsService goodsService;

    @RequestMapping("/to_list")
    @ResponseBody
    public String goodsList(HttpServletRequest request, HttpServletResponse response,
                            MiaoshaUser user, Model model) {
        model.addAttribute("user", user);
        BaseResponse<List<GoodsVo>> goodsListResult = goodsService.goodsVoList();
        log.info("result list {}", goodsListResult.getData());
        model.addAttribute("goodsList", goodsListResult.getData());
        return render(request, response, model, "goods_list");
    }
}
