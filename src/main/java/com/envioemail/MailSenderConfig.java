package com.envioemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailSenderConfig {

    @Autowired
    private PropertyConfig propertyConfig;

    @Bean
    public JavaMailSender javaMailSender() {
        return createJavaMailSender();
    }

    public JavaMailSender createJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(propertyConfig.getHost());
        mailSender.setPort(propertyConfig.getPort());
        mailSender.setUsername(propertyConfig.getUsername());
        mailSender.setPassword(propertyConfig.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public void updateMailSenderConfig() {
        createJavaMailSender();
    }
}