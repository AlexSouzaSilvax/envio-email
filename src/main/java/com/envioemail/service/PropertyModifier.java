package com.envioemail.service;

import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;

import com.envioemail.PropertyConfig;

@Component
public class PropertyModifier {

    private final PropertyConfig propertyConfig;

    public PropertyModifier(PropertyConfig propertyConfig) {
        this.propertyConfig = propertyConfig;
    }

    public JavaMailSenderImpl atualizaPropriedades(String novoUsername, String novaPassword) throws MessagingException {
        propertyConfig.setHost(propertyConfig.getHost());
        propertyConfig.setPort(propertyConfig.getPort());
        propertyConfig.setUsername(novoUsername);
        propertyConfig.setPassword(novaPassword);
        propertyConfig.setSmtpAuth(propertyConfig.isSmtpAuth());
        propertyConfig.setStarttlsEnable(propertyConfig.isStarttlsEnable());

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

    public Object findAllPropriedades() {
        return new PropertyConfig(propertyConfig.getHost(), propertyConfig.getPort(), propertyConfig.getUsername(),
                propertyConfig.getPassword(), propertyConfig.isSmtpAuth(), propertyConfig.isStarttlsEnable());
    }

}