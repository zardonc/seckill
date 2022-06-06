package org.seckill.feign;

import org.seckill.product.feign.IProdClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// feign申明式调用，provider创建接口，consumer端定义feign client
// 继承式调用无需定义，耦合性高
@FeignClient(value = "seckill-product")
public interface IProductClient extends IProdClient {
    @GetMapping("/api/goods/echo")
    String echo(@RequestParam("name") String name);
}
