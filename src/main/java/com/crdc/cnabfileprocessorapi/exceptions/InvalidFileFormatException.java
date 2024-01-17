package com.crdc.cnabfileprocessorapi.exceptions;

import com.crdc.cnabfileprocessorapi.model.dto.response.error.ErrorDetail;

import java.util.List;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
public class InvalidFileFormatException extends RuntimeException {

    private final List<ErrorDetail> errors;

    public InvalidFileFormatException(String message, List<ErrorDetail> errors) {
        super(message);
        this.errors = errors;
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }
}

