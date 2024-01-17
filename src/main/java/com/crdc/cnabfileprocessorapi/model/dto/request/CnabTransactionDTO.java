package com.crdc.cnabfileprocessorapi.model.dto.request;

import com.crdc.cnabfileprocessorapi.parser.CnabParser;

import java.math.BigDecimal;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
public record CnabTransactionDTO(
        String registerType,
        String transactionType,
        String amount,
        String originAccount,
        String destinyAccount
) {
    public static CnabTransactionDTO parse(String input) {
        String linha = String.format("%-" + 80 + "s", input.replace("\r", ""));
        return new CnabTransactionDTO(
                linha.substring(0, 3).trim(),
                linha.substring(3, 4).trim(),
                CnabParser.convertStringToBigDecimal(linha.substring(4, 20).trim()),
                linha.substring(20, 36).trim(),
                linha.substring(36, 52).trim()
        );
    }
}
