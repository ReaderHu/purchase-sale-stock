package com.systop.pss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootApplication(scanBasePackages = {"com.systop.pss","com.systop.pss.common"})
@ComponentScan({"com.systop.pss","com.systop.pss.common"})
@MapperScan("com.systop.pss.mapper")
public class UserApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(UserApp.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(UserApp.class);
    }
}
