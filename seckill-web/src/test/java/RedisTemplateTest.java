import org.junit.jupiter.api.Test;
import org.seckill.SecondKillApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = SecondKillApplication.class)
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testRedisConnect() {
        // 常用操作在opsFor...方法内
        redisTemplate.opsForValue().set("testKey", "123");
        System.out.println(redisTemplate.opsForValue().get("testKey"));
        redisTemplate.delete("testKey");
        // 简单分布式锁
        Boolean opt = redisTemplate.opsForValue().setIfAbsent("dLock:test-lock", "abc-locker", 5L, TimeUnit.SECONDS);
        System.out.println(opt);
        Boolean opt2 = redisTemplate.opsForValue().setIfAbsent("dLock:test-lock", "abc-locker2", 5L, TimeUnit.SECONDS);
        System.out.println(opt2);
    }
}
