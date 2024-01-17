package com.crdc.cnabfileprocessorapi.services;

import com.crdc.cnabfileprocessorapi.exceptions.InvalidFileFormatException;
import com.crdc.cnabfileprocessorapi.model.dto.request.CnabFileDTO;
import com.crdc.cnabfileprocessorapi.model.dto.response.error.ErrorResponseDTO;
import com.crdc.cnabfileprocessorapi.model.dto.response.success.ApiResponseDTO;
import com.crdc.cnabfileprocessorapi.model.dto.response.success.DataDTO;
import com.crdc.cnabfileprocessorapi.model.dto.response.success.TransactionDTO;
import com.crdc.cnabfileprocessorapi.model.entity.CnabTransaction;
import com.crdc.cnabfileprocessorapi.parser.CnabParser;
import com.crdc.cnabfileprocessorapi.repository.CnabTransactionRepository;
import com.crdc.cnabfileprocessorapi.util.MultiPartUtil;
import com.crdc.cnabfileprocessorapi.validation.CnabTransactionValidator;
import com.crdc.cnabfileprocessorapi.validation.TransactionValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
@Service
@Slf4j
public class CnabUploadService {

    @Autowired
    private CnabTransactionRepository repository;

    @Autowired
    private TransactionValidator transactionValidator;

    @Transactional
    public ApiResponseDTO processFile(MultipartFile file) throws InvalidFileFormatException {
        List<TransactionDTO> transactions = new ArrayList<>();
        String fileContent = null;
        try {
            fileContent = MultiPartUtil.getContent(file);
            log.info("Arquivo " + file.getName() + " contexto " + fileContent + " sendo processado.");
        } catch (IOException e) {
            log.error("Erro ao obter conteúdo do arquivo " + file.getName());
            throw new RuntimeException(e);
        }

        CnabFileDTO cnabFileDTO = CnabParser.parse(fileContent);
        ErrorResponseDTO errorResponse = CnabTransactionValidator.validate(cnabFileDTO.getTransactions());

        if (errorResponse.errors() == null || errorResponse.errors().size() == 0) {
            for(var transaction : cnabFileDTO.getTransactions()) {
                CnabTransaction entity = CnabParser.dtoTransactionToEntity(transaction);
                transactions.add(new TransactionDTO(entity.getType(), entity.getAmount(), entity.getOriginAccount(), entity.getDestinationAccount()));
                repository.save(entity);
                log.info("Transação salva " + entity.getId() + " salva com sucesso na base de dados.");
            }
        } else {
            log.error("O arquivo CNAB possui formato inválido." + errorResponse.errors());
            throw new InvalidFileFormatException("O arquivo CNAB possui formato inválido.", errorResponse.errors());
        }

        DataDTO dataDTO = new DataDTO(transactions);
        ApiResponseDTO retorno = new ApiResponseDTO("success", "Arquivo CNAB enviado e processado com sucesso.", dataDTO);
        log.info("Arquivo " + file.getName()  + " processado com sucesso.");
        return retorno;
    }
}
