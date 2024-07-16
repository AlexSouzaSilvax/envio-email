package com.envioemail.controller;

import org.springframework.web.bind.annotation.RestController;

import com.envioemail.model.Email;
import com.envioemail.service.EnviaEmailService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("api")
@Tag(name = "Envio Email", description = "Envio Email")
public class EnviaEmailController {

    private final EnviaEmailService enviaEmailService;

    public EnviaEmailController(EnviaEmailService enviaEmailService) {
        this.enviaEmailService = enviaEmailService;
    }

    @PostMapping("envia-email")
    public void enviaEmail(@RequestBody Email pEmail) throws MessagingException {
        pEmail.setConteudo(this.corpoEmail(pEmail.getNomeDestinatario(),
                pEmail.getMesExtenso(), pEmail.getAno()));
        enviaEmailService.enviarEmail(pEmail);
    }

    @PostMapping("envia-email-anexo")
    public void enviaEmailAnexo(@RequestBody Email pEmail) throws MessagingException {
        pEmail.setConteudo(this.corpoEmail(pEmail.getNomeDestinatario(),
                pEmail.getMesExtenso(), pEmail.getAno()));
        enviaEmailService.enviarEmailAnexo(pEmail);
    }

    public String corpoEmail(String pNomeDestinario, String pMesExtenso, String pAno) {
        return "<br />\n" + //
                "    <p style=\"font-size: 12px;  font-style: italic\">Enviado de forma automática</p>\n" + //
                "    <br/>\n" + //
                "    <p style=\"font-size: 15px;\">&nbsp;&nbsp;Saudações " + pNomeDestinario + ",</p>\n" + //
                "    <br />\n" + //
                "    <p style=\"font-size: 15px;\">\n" + //
                "        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Segue em anexo um Relatório de todos seus lançamentos financeiros do mês\n"
                + //
                "      de " + pMesExtenso + " de " + pAno + ".\n" + //
                "    </p>\n" + //
                "    <br/>\n" + //
                "    <br/>\n" + //
                "    <br/>\n" + //
                "    <br/>\n" + //
                "    <p>&nbsp;att,</p>\n" + //
                "    <br/><br/>\n" + //
                "    <p>&nbsp;Fincon</p>\n" + //
                "    <p>&nbsp;email.fincon@gmail.com</p>";
    }
}
