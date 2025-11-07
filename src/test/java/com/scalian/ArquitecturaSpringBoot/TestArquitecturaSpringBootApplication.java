package com.scalian.ArquitecturaSpringBoot;
import org.springframework.boot.SpringApplication;


public class TestArquitecturaSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.from(ArquitecturaSpringBootApplication::main).with(TestcontainersConfiguration.class).run(args);
    }
}
