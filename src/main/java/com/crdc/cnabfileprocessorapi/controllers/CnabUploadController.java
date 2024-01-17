package com.crdc.cnabfileprocessorapi.controllers;

import com.crdc.cnabfileprocessorapi.exceptions.InvalidFileFormatException;
import com.crdc.cnabfileprocessorapi.model.dto.response.error.ErrorResponseDTO;
import com.crdc.cnabfileprocessorapi.model.dto.response.invalid.GenericErrorResponseDTO;
import com.crdc.cnabfileprocessorapi.model.dto.response.success.ApiResponseDTO;
import com.crdc.cnabfileprocessorapi.services.CnabUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
@RestController
@RequestMapping("/api/cnab")
@Slf4j
@Tag(name = "Cnab Upload Controller")
public class CnabUploadController {

    @Autowired
    private CnabUploadService service;

    @Operation(
            summary = "Upload de Arquivo CNAB",
            description = "Envia um arquivo CNAB para processamento."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arquivo CNAB enviado e processado com sucesso.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ApiResponseDTO.class)))
            ),
            @ApiResponse(responseCode = "400", description = "O arquivo CNAB possui formato inválido.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponseDTO.class)))
            ),
            @ApiResponse(responseCode = "500", description = "Erro durante o processamento do arquivo CNAB.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponseDTO.class)))
            )
    })
    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @Parameter(description = "Arquivo CNAB a ser importado",
                    required = true,
                    content = @Content(mediaType = "multipart/form-data")
            )
            @RequestParam("file") MultipartFile file
    ) {
        try {
            ApiResponseDTO retorno = service.processFile(file);
            log.info("Arquivo " + file.getName() + " importado com sucesso.");
            return ResponseEntity.ok(retorno);
        } catch (InvalidFileFormatException e) {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO("error", "O arquivo CNAB possui formato inválido.", e.getErrors());
            log.error("Arquivo " + file.getName() + " com erros : " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            log.error("Erro durante o processamento do arquivo CNAB", e);
            GenericErrorResponseDTO genericErrorResponse = new GenericErrorResponseDTO("error", "Erro durante o processamento do arquivo CNAB. " +
                    "Certifique-se de que o arquivo esteja no formato posicional correto.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericErrorResponse);
        }
    }

}
