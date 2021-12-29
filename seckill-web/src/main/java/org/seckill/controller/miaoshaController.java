package org.seckill.controller;

import org.seckill.base.BaseResponse;
import org.seckill.base.StatusCode;
import org.seckill.entity.MiaoshaUser;
import org.seckill.service.GoodsService;
import org.seckill.service.RandomValidateCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/api/miaosha")
public class miaoshaController {

    private static final Logger log = LoggerFactory.getLogger(miaoshaController.class);

    @Resource
    private GoodsService goodsService;

    @Resource
    private RandomValidateCodeService validateCodeService;

    @RequestMapping("/verifyCode")
    @ResponseBody
    public BaseResponse<String> getMiaoshaVerifyCode(HttpServletResponse response, MiaoshaUser user,
                                                     @RequestParam("goodsId") long goodsId) {
        BaseResponse<String> result = BaseResponse.Success();
        if (user == null) {
            return BaseResponse.errorWithData(StatusCode.USER_NOT_LOGIN, StatusCode.USER_NOT_LOGIN.getMsg());
        }
        BufferedImage image = validateCodeService.getRanCode(user, goodsId);
        try {
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "JPEG", out);
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error("生成验证码错误-----goodsId:{}", goodsId, e);
            return BaseResponse.errorWithData(StatusCode.SECKILL_FAIL, StatusCode.SECKILL_FAIL.getMsg());
        }
        return result;
    }

}
