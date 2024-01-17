package com.crdc.cnabfileprocessorapi.model.dto.response.error;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
public record ErrorDetail(
        int line,
        String error
) {
}
