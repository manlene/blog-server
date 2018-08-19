package com.mm.pts.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mm.pts.blog")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
