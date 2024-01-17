package com.crdc.cnabfileprocessorapi.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
public class MultiPartUtil {

    public static String getContent(MultipartFile file) throws IOException {
        String retorno = "";
        try {
            byte[] fileContent = file.getBytes();
            retorno = new String(fileContent);
        } catch (Exception e) {
            throw  e;
        } finally {
            return retorno;
        }
    }
}
