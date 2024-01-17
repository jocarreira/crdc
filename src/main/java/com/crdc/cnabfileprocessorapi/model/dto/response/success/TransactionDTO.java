package com.crdc.cnabfileprocessorapi.model.dto.response.success;

import java.math.BigDecimal;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
public record TransactionDTO(
        String type,
        BigDecimal value,
        String accountOrigin,
        String accountDestination) {
}

