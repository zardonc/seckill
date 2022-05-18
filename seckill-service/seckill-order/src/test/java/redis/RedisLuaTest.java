package redis;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.seckill.entity.MiaoshaUser;
import org.seckill.order.OrderApplication;
import org.seckill.utils.JSONUtil;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

@SpringBootTest(classes = OrderApplication.class)
@ActiveProfiles("dev")
public class RedisLuaTest {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedissonClient redissonClient;

    @Test
    public void test() {
        String userKey = "CCC";
        MiaoshaUser miaoshaUser = new MiaoshaUser();
        miaoshaUser.setNickname(userKey);
        miaoshaUser.setId(1L);
        redisTemplate.opsForValue().set(miaoshaUser.getNickname(), miaoshaUser);
        System.out.println(JSONUtil.obj2json(redisTemplate.opsForValue().get(userKey)));

        System.out.println(JSONUtil.obj2json(redisTemplate.opsForValue().get("{a}goods:1001")));
    }
}
