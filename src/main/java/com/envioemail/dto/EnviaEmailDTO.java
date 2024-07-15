package com.envioemail.dto;

import com.envioemail.model.Email;
import com.envioemail.model.Propriedade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EnviaEmailDTO {

    private Propriedade propriedade;
    private Email email;
}