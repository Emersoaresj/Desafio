package com.example.desafio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record VendedorDto(

        @NotBlank
        String nome,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // definindo o padrão de data para ISO > Internacional
        LocalDate dataNascimento,

        @NotBlank
        @Pattern(regexp = "\\d{11}|\\d{14}", message = "CPF deve ter 11 dígitos e CNPJ deve ter 14 dígitos.")
        String cpfCnpj,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "Outsourcing|CLT|Pessoa Jurídica", message = "Tipo de contratação inválida. Valores possíveis: Outsourcing, CLT ou Pessoa Jurídica")
        String tipoContratacao,

        @NotNull
        Long filialId
){


}
