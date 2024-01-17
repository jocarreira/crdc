package com.crdc.cnabfileprocessorapi.validation;

import com.crdc.cnabfileprocessorapi.model.dto.request.CnabTransactionDTO;
import com.crdc.cnabfileprocessorapi.model.dto.response.error.ErrorDetail;
import com.crdc.cnabfileprocessorapi.model.dto.response.error.ErrorResponseDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
public class CnabTransactionValidator {

    public static ErrorResponseDTO validate(List<CnabTransactionDTO> transactions) {
        List<ErrorDetail> errors = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            int linha = i + 1;
            CnabTransactionDTO transaction = transactions.get(i);
            validateType(transaction.transactionType(), linha, errors);
            validateAmount(transaction.amount(), linha, errors);
            validateOriginAccountFormat(transaction.originAccount(), linha, "Conta origem", errors);
            validateDestinyAccountFormat(transaction.destinyAccount(), linha, "Conta destino", errors);
            validateAmmountNotNull(transaction.amount(), linha, "Valor da transação", errors);
            validateRequiredOriginAccount(transaction.originAccount(), linha, "Conta origem", errors);
            validateRequiredDestinyAccount(transaction.destinyAccount(), linha, "Conta destino", errors);
        }
        if (errors.size() == 0) {
            ErrorResponseDTO retorno = new ErrorResponseDTO("", "", errors);
            return retorno;
        } else {
            ErrorResponseDTO retorno = new ErrorResponseDTO("error", "O arquivo CNAB possui formato inválido.", errors);
            return  retorno;
        }
    }

    private static void validateType(String type, int line, List<ErrorDetail> errors) {
        if (!"C".equals(type) && !"D".equals(type) && !"T".equals(type)) {
            errors.add(new ErrorDetail(line, String.format(ConstantsMessages.ERROR_INVALID_TYPE, line)));
        }
    }

    private static void validateAmount(String amount, int line, List<ErrorDetail> errors) {
        if (!amount.matches("[0-9]+(\\.[0-9]+)?")) {
            errors.add(new ErrorDetail(line, String.format(ConstantsMessages.ERROR_INVALID_FORMAT_AMOUNT, line)));
        } else {
            BigDecimal valor = new BigDecimal(amount);
            if (amount == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
                errors.add(new ErrorDetail(line, String.format(ConstantsMessages.ERROR_INVALID_FORMAT_AMOUNT, line)));
            }
        }
    }

    private static void validateOriginAccountFormat(String account, int line, String field, List<ErrorDetail> errors) {
        if (!account.matches("\\d+")) { // Testa se o valor da conta é númerico
            errors.add(new ErrorDetail(line, String.format(ConstantsMessages.ERROR_INVALID_FORMAT_ORIGIN_ACCOUNT, line)));
        }
    }
    private static void validateDestinyAccountFormat(String account, int line, String field, List<ErrorDetail> errors) {
        if (!account.matches("\\d+")) {
            errors.add(new ErrorDetail(line, String.format(ConstantsMessages.ERROR_INVALID_FORMAT_DESTINY_ACCOUNT, line)));
        }
    }

    private static void validateAmmountNotNull(Object value, int line, String field, List<ErrorDetail> errors) {
        if (value == null || value.toString().isEmpty()) {
            errors.add(new ErrorDetail(line, String.format(ConstantsMessages.ERROR_NULL_AMMONT, line)));
        }
    }
    private static void validateRequiredOriginAccount(Object value, int line, String field, List<ErrorDetail> errors) {
        if (value == null || value.toString().isEmpty()) {
            errors.add(new ErrorDetail(line, String.format(ConstantsMessages.ERROR_REQUIRED_ORIGIN_ACCOUNT, line)));
        }
    }
    private static void validateRequiredDestinyAccount(Object value, int line, String field, List<ErrorDetail> errors) {
        if (value == null || value.toString().isEmpty()) {
            errors.add(new ErrorDetail(line, String.format(ConstantsMessages.ERROR_REQUIRED_DESTINY_ACCOUNT, line)));
        }
    }
}
