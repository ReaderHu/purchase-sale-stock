package com.systop.pss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.systop.pss")
@MapperScan("com.systop.pss.mapper")
public class UserApp {

    public static void main(String[] args) {
        SpringApplication.run(UserApp.class,args);
    }
}
