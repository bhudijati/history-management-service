package com.bjpu.history.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HistoryTransaction implements Serializable {
    @Id
    @SequenceGenerator(name = "historytrx", sequenceName = "historyTrxSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historytrx")
    private Long idHistoryTransaction;
    private String sourceAccount;
    private String bankCode;
    private String destAccount;
    private BigDecimal amount;
    private String destinationAccountName;
    private BigDecimal adminFee;
    private String referenceNumber;
    private String transactionStage;
}
