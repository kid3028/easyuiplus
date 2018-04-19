package com.easyui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.easyui.dao")
public class EasyuiplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyuiplusApplication.class, args);
    }
}
