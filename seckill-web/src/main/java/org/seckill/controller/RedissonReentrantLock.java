package org.seckill.controller;

import org.redisson.api.*;
import org.seckill.entity.SecKillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/testLock")
public class RedissonReentrantLock {
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private final Integer threadNum = Runtime.getRuntime().availableProcessors() + 1;
    private Integer stock = 100;
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/getStock")
    public void testLock() {
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(1000L);
                    // 调用锁扣减
                    reduceStockByRedissonLock();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }

    /**
     * 功能描述: 加锁扣减库存
     * 〈〉
     *
     * @Param: []
     * @Return: void
     * @Author: lang
     */
    private void reduceStockByRedissonLock() {
        // 获取锁(可重入锁)
        RLock lock = redissonClient.getLock("Main_Lock");
        lock.lock();
        //业务操作
        if (stock > 0) {
            stock--;
            System.out.println("当前库存剩余：" + stock);
        }
        //释放锁
        lock.unlock();
    }

    /**
     * 不加锁情况
     */
    private void reduceStock() throws InterruptedException {
        //业务操作
        if (stock > 0) {
            stock--;
            System.out.println("当前库存剩余：" + stock);
        }
    }

    //    ========================== String =======================
    @GetMapping("/set/{key}")
    public String s1(@PathVariable String key) {
        RBucket<String> keyObj = redissonClient.getBucket(key);
        keyObj.set(key + "1-v1");
        return key;
    }

    @GetMapping("/get/{key}")
    public String g1(@PathVariable String key) {
        // 设置字符串
        RBucket<String> keyObj = redissonClient.getBucket(key);
        return keyObj.get();
    }

    //    ========================== hash =========================
    @GetMapping("/hset")
    public String h1() {
        SecKillDTO secKillDTO = new SecKillDTO();
        secKillDTO.setUserId(RANDOM.nextInt(1000));
        secKillDTO.setKillId(RANDOM.nextInt(1000));
        RMap<String, SecKillDTO> secMap = redissonClient.getMap("secMap");
        secMap.put(secKillDTO.getUserId().toString(), secKillDTO);
        return secKillDTO.toString();
    }

    @GetMapping("/hget/{id}")
    public String h2(@PathVariable String id) {
        // hash 查询
        RMap<String, SecKillDTO> ss = redissonClient.getMap("secMap");
        SecKillDTO secKillDTO = ss.get(id);
        return secKillDTO.toString();
    }

    // 查询所有keys
    @GetMapping("/all")
    public String all() {
        RKeys keys = redissonClient.getKeys();
        Iterable<String> keys1 = keys.getKeys();
        keys1.forEach(System.out::println);
        return keys.toString();
    }

    // ===============================读写锁测试 =============================
    @GetMapping("/rw/set/{key}")
    public void rw_set() {
        RBucket<String> ls_count = redissonClient.getBucket("LS_COUNT");
        ls_count.set("300", 30000L, TimeUnit.SECONDS);
    }

    // 减法运算
    @GetMapping("/des")
    public void des() {
        String key = "S_COUNT";
        int i = 20;
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        if (!atomicLong.isExists()) {
            atomicLong.set(300L);
        }
        while (i > 0) {
            if (atomicLong.get() > 0) {
                long l = atomicLong.getAndDecrement();
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                i--;
                System.out.println(Thread.currentThread().getName() + "->" + i + "->" + l);
            }
        }
    }
}
