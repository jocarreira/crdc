package com.crdc.cnabfileprocessorapi.repository;

import com.crdc.cnabfileprocessorapi.model.entity.CnabTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
@Repository
public interface CnabTransactionRepository extends JpaRepository<CnabTransaction, Long> {
}
