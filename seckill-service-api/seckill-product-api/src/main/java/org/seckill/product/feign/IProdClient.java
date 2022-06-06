package org.seckill.product.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// feign继承式调用
public interface IProdClient {
    @GetMapping("/api/goods/echo2")
    String echo2(@RequestParam("name") String name);
}
