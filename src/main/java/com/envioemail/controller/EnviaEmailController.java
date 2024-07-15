package com.envioemail.controller;

import org.springframework.web.bind.annotation.RestController;

import com.envioemail.dto.EnviaEmailDTO;
import com.envioemail.service.EnviaEmailService;

import jakarta.mail.MessagingException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EnviaEmailController {

    private final EnviaEmailService enviaEmailService;

    public EnviaEmailController(EnviaEmailService enviaEmailService) {
        this.enviaEmailService = enviaEmailService;
    }

    @PostMapping("envia-email")
    public void enviaEmail(@RequestBody EnviaEmailDTO pEnviaEmailDTO) throws MessagingException {
        pEnviaEmailDTO.getEmail().setConteudo(this.corpoEmail(pEnviaEmailDTO.getEmail().getNomeDestinatario(),
                pEnviaEmailDTO.getEmail().getMesExtenso(), pEnviaEmailDTO.getEmail().getAno()));
        enviaEmailService.enviarEmail(pEnviaEmailDTO.getPropriedade(), pEnviaEmailDTO.getEmail());
    }

    @PostMapping("envia-email-anexo")
    public void enviaEmailAnexo(@RequestBody EnviaEmailDTO pEnviaEmailDTO) throws MessagingException {
        pEnviaEmailDTO.getEmail().setConteudo(this.corpoEmail(pEnviaEmailDTO.getEmail().getNomeDestinatario(),
                pEnviaEmailDTO.getEmail().getMesExtenso(), pEnviaEmailDTO.getEmail().getAno()));
        enviaEmailService.enviarEmailAnexo(pEnviaEmailDTO.getPropriedade(), pEnviaEmailDTO.getEmail());
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
