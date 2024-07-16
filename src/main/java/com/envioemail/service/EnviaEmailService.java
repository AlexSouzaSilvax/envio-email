package com.envioemail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.envioemail.model.Email;

import jakarta.mail.MessagingException;

@Service
public class EnviaEmailService {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    public void enviarEmail(Email pEmail) throws MessagingException {

        var mensagem = javaMailSender.createMimeMessage();

        var helper = new MimeMessageHelper(mensagem, true);

        helper.setTo(pEmail.getDestinatario());
        helper.setSubject(pEmail.getTitulo());
        helper.setText(pEmail.getConteudo(), true);

        javaMailSender.send(mensagem);
    }

    public void enviarEmailAnexo(Email pEmail)
            throws MessagingException {

        var mensagem = javaMailSender.createMimeMessage();

        var helper = new MimeMessageHelper(mensagem, true);

        helper.setTo(pEmail.getDestinatario());
        helper.setSubject(pEmail.getTitulo());
        helper.setText(pEmail.getConteudo(), true);

        String nomeAnexo = "relatorio_mensal_" + pEmail.getMesExtenso() + "_" + pEmail.getAno() + ".xlsx";
        helper.addAttachment(nomeAnexo, new ClassPathResource(pEmail.getAnexo()));

        javaMailSender.send(mensagem);
    }

}
