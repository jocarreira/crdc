package com.crdc.cnabfileprocessorapi.mapper;

import com.crdc.cnabfileprocessorapi.model.dto.request.CnabTransactionDTO;
import com.crdc.cnabfileprocessorapi.model.entity.CnabTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
@Component
public class CnabTransactionMapper {
    private final ModelMapper modelMapper;

    public CnabTransactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CnabTransaction convertToEntity(CnabTransactionDTO dto) {
        return modelMapper.map(dto, CnabTransaction.class);
    }
}
