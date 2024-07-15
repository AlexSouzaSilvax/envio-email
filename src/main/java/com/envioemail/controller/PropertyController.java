package com.envioemail.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.envioemail.service.PropertyModifier;

import jakarta.mail.MessagingException;

@RestController
public class PropertyController {

    private final PropertyModifier propertyModifier;

    public PropertyController(PropertyModifier propertyModifier) {
        this.propertyModifier = propertyModifier;
    }

    @GetMapping("/propriedades")
    public Object findAllPropriedades() {
        return propertyModifier.findAllPropriedades();
    }

    @PostMapping("/atualiza-propriedades")
    public Object atualizaPropriedades(@RequestParam String novoUsername, @RequestParam String novaPassword)
            throws MessagingException {
        propertyModifier.atualizaPropriedades(novoUsername, novaPassword);
        return findAllPropriedades();
    }
}