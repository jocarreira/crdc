package com.crdc.cnabfileprocessorapi.validation;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
public class ConstantsMessages {

    public static String ERROR_INVALID_TYPE = "Erro na linha %d: Tipo de transação inválido.";

    public static String ERROR_INVALID_FORMAT_AMOUNT = "Erro na linha %d: Valor da transação está fora do formato válido.";

    public static String ERROR_INVALID_FORMAT_ORIGIN_ACCOUNT = "Erro na linha %d: Conta origem não está no formato correto.";

    public static String ERROR_INVALID_FORMAT_DESTINY_ACCOUNT = " Erro na linha %d: Conta destino não está no formato correto.";

    public static String ERROR_NULL_AMMONT = "Erro na linha %d: Valor da transação não pode ser nulo.";

    public static String ERROR_REQUIRED_ORIGIN_ACCOUNT = "Erro na linha %d: Conta origem é obrigatória.";

    public static String ERROR_REQUIRED_DESTINY_ACCOUNT = "Erro na linha %d: Conta destino é obrigatória.";
}
