package com.envioemail.controller;

import org.springframework.web.bind.annotation.RestController;

import com.envioemail.model.Email;
import com.envioemail.service.EnviaEmailService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;

import java.io.IOException;

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
        enviaEmailService.enviarEmail(pEmail);
    }

    @PostMapping("envia-email-anexo")
    public void enviaEmailAnexo(@RequestBody Email pEmail) throws MessagingException, IOException {
        enviaEmailService.enviarEmailAnexo(pEmail);
    }

}
