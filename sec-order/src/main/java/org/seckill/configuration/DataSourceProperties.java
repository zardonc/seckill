package org.seckill.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// 将 .properties 文件中的属性绑定到 Java 对象中
@Configuration
@ConfigurationProperties(prefix = "db")
public class DataSourceProperties {
    private String host;
    private int port;
    private String name;
    private String username;
    private String password;

    public String getUrl() {
        // jdbc:mysql://${db.HOST:localhost}:3306/secmall?useUnicode=true&characterEncoding=utf-8
        return "jdbc:mysql://" + host + ":" + port + "/" + name + "?useUnicode=true&characterEncoding=utf-8";
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
