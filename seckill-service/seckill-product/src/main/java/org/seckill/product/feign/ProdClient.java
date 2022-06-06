package org.seckill.product.feign;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProdClient implements IProdClient {
    private static final Logger log = LoggerFactory.getLogger(ProdClient.class);

    @Override
    public String echo2(String name) {
        log.info("provider client: " + name);
        List<String> list = new ArrayList<>();
        list.add("java");
        list.add("springCloud");
        return list.toString();
    }
}
