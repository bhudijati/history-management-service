package com.bjpu.history.controller;

import com.bjpu.history.domain.dto.TransactionManagementRequest;
import com.bjpu.history.domain.dto.TransactionManagementResponse;
import com.bjpu.history.services.HistoryManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v1")
@Slf4j
public class HistoryController {

    @Autowired
    private HistoryManagementService historyManagementService;

    @PostMapping(value = "/store")
    @ResponseBody
    public void storeHistory(@RequestBody TransactionManagementRequest transactionManagementRequest) {
        try {
            historyManagementService.storeHistory(transactionManagementRequest);
        } catch (Exception ex) {
            log.error("Exception happened when Storing History message [{}]", ex.getMessage());
            throw ex;
        }
    }

    @PostMapping(value = "/find")
    @ResponseBody
    public TransactionManagementResponse findHistory(@RequestBody TransactionManagementRequest transactionManagementRequest) {
        TransactionManagementResponse transactionManagementResponse;
        try {
            transactionManagementResponse = historyManagementService
                    .findHistoryByReference(transactionManagementRequest.getReferenceNumber());
        } catch (Exception ex) {
            log.error("Exception happened when getting History message [{}]", ex.getMessage());
            throw ex;
        }
        return transactionManagementResponse;
    }
}
