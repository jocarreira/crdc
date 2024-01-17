package com.crdc.cnabfileprocessorapi.validation;

import com.crdc.cnabfileprocessorapi.model.entity.CnabTransaction;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
@Component
public class TransactionValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CnabTransaction.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CnabTransaction transaction = (CnabTransaction) target;
        validateType(transaction.getType(), transaction.getId(), errors);
        validateAmount(transaction.getAmount(), transaction.getId(), errors);
        validateOriginAccountFormat(transaction.getOriginAccount(), transaction.getId(), "Conta origem", errors);
        validateDestinyAccountFormat(transaction.getDestinationAccount(), transaction.getId(), "Conta destino", errors);
        validateAmmountNotNull(transaction.getAmount(), transaction.getId(), "Valor da transação", errors);
        validateRequiredOriginAccount(transaction.getOriginAccount(), transaction.getId(), "Conta origem", errors);
        validateRequiredDestinyAccount(transaction.getDestinationAccount(), transaction.getId(), "Conta destino", errors);
    }
    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }

    private void validateType(String type, Long line, Errors errors) {
        if (!"C".equals(type) && !"D".equals(type) && !"T".equals(type)) {
            errors.rejectValue("type", "error.validation.invalidType", new Object[]{line}, "Default Message");
        }
    }

    private void validateAmount(BigDecimal amount, Long line, Errors errors) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            errors.rejectValue("type", "error.validation.invalidFormatAmount", new Object[]{line}, "Default Message");
        }
    }

    private void validateOriginAccountFormat(String account, Long line, String field, Errors errors) {
        if (false) {
            errors.rejectValue("type", "error.validation.invalidFormatOriginAccount", new Object[]{line}, "Default Message");
        }
    }
    private void validateDestinyAccountFormat(String account, Long line, String field, Errors errors) {
        if (false) {
            errors.rejectValue("type", "error.validation.invalidFormatDestinytionAccount", new Object[]{line}, "Default Message");
        }
    }

    private void validateAmmountNotNull(Object value, Long line, String field, Errors errors) {
        if (value == null) {
            errors.rejectValue(field, "error.validation.nullAmount", "Erro na transação com ID " + line + ": " + field + " é obrigatório.");
        }
    }
    private void validateRequiredOriginAccount(Object value, Long line, String field, Errors errors) {
        if (value == null) {
            errors.rejectValue(field, "error.validation.requiredOriginAccount", "Erro na transação com ID " + line + ": " + field + " é obrigatório.");
        }
    }
    private void validateRequiredDestinyAccount(Object value, Long line, String field, Errors errors) {
        if (value == null) {
            errors.rejectValue(field, "error.validation.requiredDestinationAccount", "Erro na transação com ID " + line + ": " + field + " é obrigatório.");
        }
    }
}
