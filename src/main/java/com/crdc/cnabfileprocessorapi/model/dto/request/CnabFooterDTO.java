package com.crdc.cnabfileprocessorapi.model.dto.request;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
public record CnabFooterDTO(
        String tipoRegistro,
        String espacoReservado
) {
    public static CnabFooterDTO parse(String input) {
        String linha = String.format("%-" + 80 + "s", input.replace("\r", ""));
        return new CnabFooterDTO(
                linha.substring(0, 3).trim(),
                linha.substring(3, 80).trim()
        );
    }
}

