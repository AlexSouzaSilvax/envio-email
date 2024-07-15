package com.envioemail.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.envioemail.model.Email;
import com.envioemail.model.Propriedade;

import jakarta.mail.MessagingException;

@Service
public class EnviaEmailService {

    private PropertyModifier propertyModifier;

    public EnviaEmailService(PropertyModifier propertyModifier) {
        this.propertyModifier = propertyModifier;
    }

    private JavaMailSenderImpl atualizaPropriedades(String novoUsername, String novaPassword)
            throws MessagingException {
        return propertyModifier.atualizaPropriedades(novoUsername, novaPassword);
    }

    public void enviarEmail(Propriedade propriedade, Email pEmail) throws MessagingException {

        JavaMailSenderImpl javaMailSender = atualizaPropriedades(propriedade.getUsername(), propriedade.getPassword());

        var mensagem = javaMailSender.createMimeMessage();

        var helper = new MimeMessageHelper(mensagem, true);

        helper.setTo(pEmail.getDestinatario());
        helper.setSubject(pEmail.getTitulo());
        helper.setText(pEmail.getConteudo(), true);

        javaMailSender.send(mensagem);
    }

    public void enviarEmailAnexo(Propriedade propriedade, Email pEmail)
            throws MessagingException {
        JavaMailSenderImpl javaMailSender = atualizaPropriedades(propriedade.getUsername(), propriedade.getPassword());

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
