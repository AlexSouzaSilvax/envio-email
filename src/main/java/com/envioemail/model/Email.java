package com.envioemail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Email {

    private String destinatario;
    private String titulo;
    private String nomeDestinatario;
    private Object anexo;
    private String conteudo;
}
