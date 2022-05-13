# Nacos

## Data ID

> 规则${prefix}-${spring.profile.active}.${file-extension}

prefix 默认为 `spring.application.name` 的值，也可以通过配置项 spring.cloud.nacos.config.prefix 来配置。
spring.profile.active 即为当前环境对应的 profile，可以通过配置项 spring.profile.active 来配置。
file-exetension 为配置内容的数据格式，可以通过配置项 spring.cloud.nacos.config.file-extension 来配置。目前只支持 properties 和 yaml 类型。

## 优先级

Spring Cloud Alibaba Nacos Config 目前提供了三种配置能力从 Nacos 拉取相关的配置。

A: 通过 spring.cloud.nacos.config.shared-configs[n].data-id 支持多个共享 Data Id 的配置
B: 通过 spring.cloud.nacos.config.extension-configs[n].data-id 的方式支持多个扩展 Data Id 的配置
C: 通过内部相关规则(spring.cloud.nacos.config.prefix、spring.cloud.nacos.config.file-extension、spring.cloud.nacos.config.group)自动生成相关的 Data Id 配置

当三种方式共同使用时，他们的一个优先级关系是:A < B < C
