package com.bjpu.history.services;

import com.bjpu.history.domain.dto.TransactionManagementRequest;
import com.bjpu.history.domain.dto.TransactionManagementResponse;
import com.bjpu.history.domain.entity.HistoryTransaction;
import com.bjpu.history.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class HistoryManagementService {

    @Autowired
    private HistoryRepository historyRepository;

    @Transactional
    public void storeHistory(TransactionManagementRequest transactionManagementRequest) {
        if (transactionManagementRequest.getIdHistoryTransaction() == null) {
            Object[] queryParams = {transactionManagementRequest.getSourceAccount(),
                    transactionManagementRequest.getBankCode(), transactionManagementRequest.getDestAccount(),
                    transactionManagementRequest.getAmount(), transactionManagementRequest.getDestinationAccountName(),
                    transactionManagementRequest.getAdminFee(), transactionManagementRequest.getReferenceNumber(),
                    transactionManagementRequest.getTransactionStage()};
            historyRepository.storeTrxHistory(new ArrayList<>(Arrays.asList(queryParams)));
        } else {
            historyRepository.updateStageTrxHistory(transactionManagementRequest.getTransactionStage(),
                    transactionManagementRequest.getReferenceNumber());
        }

    }


    public TransactionManagementResponse findHistoryByReference(String referenceNumber) {
        HistoryTransaction historyTransaction = historyRepository.findByReference(referenceNumber);
        return TransactionManagementResponse.builder()
                .idHistoryTransaction(historyTransaction.getIdHistoryTransaction())
                .sourceAccount(historyTransaction.getSourceAccount())
                .bankCode(historyTransaction.getBankCode())
                .destAccount(historyTransaction.getDestAccount())
                .amount(historyTransaction.getAmount())
                .destinationAccountName(historyTransaction.getDestinationAccountName())
                .adminFee(historyTransaction.getAdminFee())
                .referenceNumber(historyTransaction.getReferenceNumber())
                .transactionStage(historyTransaction.getTransactionStage())
                .build();
    }
}
