package com.envioemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PropertyConfig.class)
public class ServicoEmail {

    public static void main(String[] args) {
        SpringApplication.run(ServicoEmail.class, args);
    }

}
