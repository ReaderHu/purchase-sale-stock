package com.systop.pss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.systop.pss.database.mapper")
public class TimerApp {

    public static void main(String[] args) {
        SpringApplication.run(TimerApp.class,args);
    }




}
