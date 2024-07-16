package com.envioemail.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.envioemail.model.Email;
import com.envioemail.util.JsonToExcel;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.mail.MessagingException;

@Service
public class EnviaEmailService {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Autowired
    JsonToExcel jsonToExcel;

    public void enviarEmail(Email pEmail) throws MessagingException {

        var mensagem = javaMailSender.createMimeMessage();

        var helper = new MimeMessageHelper(mensagem, true);

        helper.setTo(pEmail.getDestinatario());
        helper.setSubject(pEmail.getTitulo());
        helper.setText(pEmail.getConteudo(), true);

        javaMailSender.send(mensagem);
    }

    public void enviarEmailAnexo(Email pEmail)
            throws MessagingException, IOException {

        var mensagem = javaMailSender.createMimeMessage();

        var helper = new MimeMessageHelper(mensagem, true);

        helper.setTo(pEmail.getDestinatario());
        helper.setSubject(pEmail.getTitulo());
        helper.setText(pEmail.getConteudo(), true);

        String nomeAnexo = "relatorio_mensal.xlsx";
        helper.addAttachment(nomeAnexo, new ClassPathResource(
                jsonToExcel.convertJsonToExcel(new ObjectMapper().writeValueAsString(pEmail.getAnexo()))));

        javaMailSender.send(mensagem);

        this.deleteFile("./src/main/resources/arquivos/data.xlsx");
    }

    private void deleteFile(String caminhoFile) {
        File file = new File(caminhoFile);
        if (file.exists()) {
            file.delete();
        }
    }

}
