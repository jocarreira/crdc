package com.crdc.cnabfileprocessorapi.model.dto.request;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
public record CnabHeaderDTO(
        String tipoRegistro,
        String razaoSocial,
        String identificadorEmpresa,
        String espacoReservado
) {
    public static CnabHeaderDTO parse(String input) {
        String linha = String.format("%-" + 80 + "s", input.replace("\r", ""));
        return new CnabHeaderDTO(
                linha.substring(0, 3).trim(),
                linha.substring(3, 33).trim(),
                linha.substring(33, 47).trim(),
                linha.substring(47, 80).trim()
        );
    }
}
