package com.crdc.cnabfileprocessorapi.parser;

import com.crdc.cnabfileprocessorapi.model.dto.request.CnabFileDTO;
import com.crdc.cnabfileprocessorapi.model.dto.request.CnabFooterDTO;
import com.crdc.cnabfileprocessorapi.model.dto.request.CnabHeaderDTO;
import com.crdc.cnabfileprocessorapi.model.dto.request.CnabTransactionDTO;
import com.crdc.cnabfileprocessorapi.model.entity.CnabTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
@Service
@Slf4j
public class CnabParser {

    public static CnabFileDTO parse(String content)  {
        CnabFileDTO retorno = new CnabFileDTO();
        retorno.setTransactions(new ArrayList<>());

        String[] linhas = content.split("\n");

        for (String linha : linhas) {
            switch (linha.substring(0, 3)) {
                case "001":
                    CnabHeaderDTO cabecalhoDTO = CnabHeaderDTO.parse(linha);
                    log.info("Header lida : " + cabecalhoDTO);
                    retorno.setHeader(cabecalhoDTO);
                    break;
                case "002":
                    CnabTransactionDTO transacaoDTO = CnabTransactionDTO.parse(linha);
                    log.info("Transaction lida : " + transacaoDTO);
                    retorno.getTransactions().add(transacaoDTO);
                    break;
                case "003":
                    CnabFooterDTO rodapeDTO = CnabFooterDTO.parse(linha);
                    log.info("Footer lida : " + rodapeDTO);
                    retorno.setFooter(rodapeDTO);
                    break;
                default:
                    log.info("Tipo de registro desconhecido: " + linha.substring(0, 3));
            }
        }
        return retorno;
    }

    public static CnabTransaction dtoTransactionToEntity(CnabTransactionDTO dto) {
        CnabTransaction entity = new CnabTransaction();
        entity.setType(dto.transactionType());
        entity.setAmount(new BigDecimal(dto.amount()));
        entity.setOriginAccount(dto.originAccount());
        entity.setDestinationAccount(dto.destinyAccount());
        return entity;
    }

    public static String convertStringToBigDecimal(String valorString) {
        String parteInteira = valorString.substring(0, valorString.length() - 7);
        String parteFracionaria = valorString.substring(valorString.length() - 7);
        return parteInteira.trim() + "." + parteFracionaria.trim();
    }

}
