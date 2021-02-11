package com.bjpu.history.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionManagementRequest implements Serializable {
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
