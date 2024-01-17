package com.crdc.cnabfileprocessorapi.model.dto.response.success;

import java.util.List;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
public record DataDTO(
        List<TransactionDTO> transactions
) {
}
