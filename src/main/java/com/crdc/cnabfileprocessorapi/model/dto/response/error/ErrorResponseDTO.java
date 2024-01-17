package com.crdc.cnabfileprocessorapi.model.dto.response.error;

import java.util.List;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
public record ErrorResponseDTO(
        String status,
        String message,
        List<ErrorDetail> errors
) {
}
