package com.crdc.cnabfileprocessorapi.model.dto.error;

import lombok.Data;

import java.text.MessageFormat;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
@Data
public class ValidationError {
    private int line;
    private String error;

    public ValidationError(int line, String error) {
        this.error = MessageFormat.format(error, line);
    }

}
