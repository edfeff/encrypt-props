package com.wpp.encryptprops;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wpp.encryptprops.db")
public class EncryptPropsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EncryptPropsApplication.class, args);
    }

}
