package com.crdc.cnabfileprocessorapi.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnabFileDTO {

    private CnabHeaderDTO header;
    private List<CnabTransactionDTO> transactions;
    private CnabFooterDTO footer;

}
