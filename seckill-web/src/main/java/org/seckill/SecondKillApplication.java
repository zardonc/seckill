package org.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableCaching
@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
public class SecondKillApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecondKillApplication.class, args);
    }
}
