package com.crdc.cnabfileprocessorapi.model.dto.response.success;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
public record ApiResponseDTO(
        String status,
        String message,
        DataDTO data
) {
}