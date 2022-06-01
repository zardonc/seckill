package org.seckill.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seckill-product")
public interface IProductClient {
    @GetMapping("/api/goods/echo")
    String echo(@RequestParam("name") String name);
}
