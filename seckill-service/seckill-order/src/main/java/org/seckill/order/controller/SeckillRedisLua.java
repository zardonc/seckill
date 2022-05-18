package org.seckill.order.controller;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class SeckillRedisLua {
    private static final Random random = new Random();
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/seckill_lua")
    public String seckillWithLua() {
        int userId = random.nextInt(1000);
        String script = "if tonumber(redis.call('get', KEYS[1]))>0 then redis.call('decr', KEYS[1]) redis.call('lpush', KEYS[2], ARGV[1]) return 1 else return 0 end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(script);
        redisScript.setResultType(Long.class);

        List<String> keylist = new ArrayList<>();
        keylist.add("{a}goods:1001");
        keylist.add("{a}user:1001");

        Long result = (Long) redisTemplate.execute(redisScript, keylist, String.valueOf(userId));

        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ListOperations<String, Object> listOps = redisTemplate.opsForList();

        if (result == 1) {
            System.out.println(ops.get("{a}goods:1001"));
            System.out.println(listOps.range("{a}user:1001", 0, -1));
            return "success";
        } else {
            return "failed";
        }
    }
}
