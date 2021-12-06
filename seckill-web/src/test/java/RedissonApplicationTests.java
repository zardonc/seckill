import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.seckill.SecondKillApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// redis连接测试
@SpringBootTest(classes = SecondKillApplication.class)
public class RedissonApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    void contextLoads() {
        redissonClient.getBucket("hello").set("bug");
        String test = (String) redissonClient.getBucket("hello").get();
        System.out.println(test);
    }
}
