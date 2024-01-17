package com.crdc.cnabfileprocessorapi.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
@Entity
@Table(name = "cnab_transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CnabTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private BigDecimal amount;
    private String originAccount;
    private String destinationAccount;
}
