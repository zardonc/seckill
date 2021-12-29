package org.seckill.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.seckill.entity.MiaoshaUser;
import org.seckill.redis_manager.rediskeysbean.MiaoshaKey;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class RandomValidateCodeService {
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();
    // 生成只有数字的字符串
    private String verString = "123456789";
    //private String randString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生只有字母的字符串
    //private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生数字与字母组合的字符串
    // 图片宽
    private int width = 95;
    // 图片高
    private int height = 25;
    // 干扰线数量
    private int lineSize = 40;
    // 随机产生字符数量
    private int stringNum = 4;
    @Resource
    private RedissonClient redissonClient;

    /**
     * 获取随机的字符
     */
    public String getRandomString(int num) {
        return String.valueOf(verString.charAt(num));
    }

    public BufferedImage getRanCode(MiaoshaUser user, long goodsId) {
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics g = image.getGraphics();
        //图片大小
        g.fillRect(0, 0, width, height);
        //字体大小
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        //字体颜色
        g.setColor(getRandColor(110, 133));
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drawLine(g);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drawString(g, randomString, i);
        }
        log.info("random verify string {}", randomString);
        g.dispose();
        MiaoshaKey keyObj = MiaoshaKey.getMiaoshaVerifyCodeKey;
        String realKey = keyObj.getPrefix() + user.getNickname() + ":" + goodsId;
        RBucket<String> keyBucket = redissonClient.getBucket(realKey);
        keyBucket.set(randomString, keyObj.getExpTTL(), keyObj.getTimeUnit());
        return image;
    }

    // 字体
    private Font getFont() {
        return new Font("Fixedsys", Font.BOLD, 18);
    }

    /**
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 绘制干扰线
     */
    private void drawLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 绘制字符串
     */
    private String drawString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(verString
                .length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }
}
