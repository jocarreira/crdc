package com.crdc.cnabfileprocessorapi.model.dto.response.invalid;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
public record GenericErrorResponseDTO(
        String status,
        String message
) {
}
