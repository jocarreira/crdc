package com.crdc.cnabfileprocessorapi.model.dto.request;

import org.springframework.web.multipart.MultipartFile;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
public class UploadDTO {

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
