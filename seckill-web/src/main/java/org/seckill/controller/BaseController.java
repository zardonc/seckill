package org.seckill.controller;

import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Controller
public class BaseController {
    // 页面缓存
    @Value("#{'${pageCache.enable}'}")
    private boolean pageCacheEnable;

    @Resource
    private ThymeleafViewResolver thymeleafViewResolver;

    @Resource
    private RedissonClient redissonClient;

    public static void out(HttpServletResponse res, String html) {
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");
        try {
            OutputStream out = res.getOutputStream();
            out.write(html.getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String render(HttpServletRequest request, HttpServletResponse response, Model model, String tplName) {
        if (!pageCacheEnable) {
            return tplName;
        }
        //取缓存
//        String html = (String) redisService.get(prefix, key, String.class);
        String html = "";
        if(!StringUtils.isEmpty(html)) {
            out(response, html);
            return null;
        }
        //手动渲染
        WebContext ctx = new WebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process(tplName, ctx);
        if(!StringUtils.isEmpty(html)) {
//            redisService.set(prefix, key, html);
        }
        out(response, html);
        return null;
    }
}
