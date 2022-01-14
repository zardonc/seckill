import org.junit.jupiter.api.Test;
import org.seckill.SecondKillApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest(classes = SecondKillApplication.class)
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testRedisConnect() {
        // 常用操作在opsFor...方法内
        redisTemplate.opsForValue().set("testKey", "123");
        System.out.println(redisTemplate.opsForValue().get("testKey"));
    }
}
