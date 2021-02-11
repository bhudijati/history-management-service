package com.bjpu.history.services;

import com.bjpu.history.domain.dto.TransactionManagementRequest;
import com.bjpu.history.domain.dto.TransactionManagementResponse;
import com.bjpu.history.domain.entity.HistoryTransaction;
import com.bjpu.history.repository.HistoryRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HistoryManagementServiceTest {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryManagementService historyManagementService;

    @Test
    public void testStoreHistory_expectSuccess_storeNew() {
        String refnum = "uid1231112";
        historyManagementService.storeHistory(TransactionManagementRequest.builder()
                .adminFee(BigDecimal.TEN)
                .amount(BigDecimal.valueOf(10000))
                .bankCode("001")
                .destAccount("00100101")
                .referenceNumber(refnum)
                .destinationAccountName("Bjpus")
                .sourceAccount("7000123111")
                .transactionStage("INQUIRY")
                .build());
        HistoryTransaction historyTransaction = historyRepository.findByReference(refnum);
        assertEquals(refnum, historyTransaction.getReferenceNumber());
    }

    @Test
    public void testStoreHistory_expectSuccess_storeUpdate() {
        String refnum = "uid1231113";

        HistoryTransaction getId = historyRepository.save(HistoryTransaction.builder()
                .adminFee(BigDecimal.TEN)
                .amount(BigDecimal.valueOf(10000))
                .bankCode("001")
                .destAccount("00100101")
                .referenceNumber(refnum)
                .destinationAccountName("Bjpus")
                .sourceAccount("7000123111")
                .transactionStage("INQUIRY")
                .build());

        historyManagementService.storeHistory(TransactionManagementRequest.builder()
                .idHistoryTransaction(getId.getIdHistoryTransaction())
                .referenceNumber(refnum)
                .transactionStage("EXECUTED")
                .build());
        HistoryTransaction historyTransaction = historyRepository.findByReference(refnum);
        assertEquals("EXECUTED", historyTransaction.getTransactionStage());
    }

    @Test
    public void testFindHistoryByReference_expectSuccess_found() {
        String refnum = "uid1231114";
        historyManagementService.storeHistory(TransactionManagementRequest.builder()
                .adminFee(BigDecimal.TEN)
                .amount(BigDecimal.valueOf(10000))
                .bankCode("001")
                .destAccount("00100101")
                .referenceNumber(refnum)
                .destinationAccountName("Bjpus")
                .sourceAccount("7000123111")
                .transactionStage("INQUIRY")
                .build());
        TransactionManagementResponse history = historyManagementService.findHistoryByReference(refnum);
        assertEquals(refnum, history.getReferenceNumber());
    }

    @After
    public void release() {
        historyRepository.deleteAllInBatch();
    }
}