package com.bjpu.history.controller;

import com.bjpu.history.domain.dto.TransactionManagementRequest;
import com.bjpu.history.exceptions.GenericException;
import com.bjpu.history.services.HistoryManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class HistoryControllerTest {

    @MockBean
    private HistoryManagementService historyManagementService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    public void testStoreHistory_expectSuccess_store() {
        mockMvc.perform(post("/v1/store")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TransactionManagementRequest.builder()
                        .adminFee(BigDecimal.TEN)
                        .amount(BigDecimal.valueOf(10000))
                        .bankCode("001")
                        .destAccount("00100101")
                        .referenceNumber("1111112311RB")
                        .destinationAccountName("Bjpus")
                        .sourceAccount("7000123111")
                        .transactionStage("INQUIRY")
                        .build())))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test(expected = Exception.class)
    public void testStoreHistory_expectFailed_store() {
        doThrow(GenericException.class).when(historyManagementService).storeHistory(any());
        mockMvc.perform(post("/v1/store")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TransactionManagementRequest.builder()
                        .adminFee(BigDecimal.TEN)
                        .amount(BigDecimal.valueOf(10000))
                        .bankCode("001")
                        .destAccount("00100101")
                        .referenceNumber("1111112311RB")
                        .destinationAccountName("Bjpus")
                        .sourceAccount("7000123111")
                        .transactionStage("INQUIRY")
                        .build())))
                .andExpect(status().isBadRequest());
    }

    @SneakyThrows
    @Test
    public void testFindHistory_expectSuccess_found() {
        mockMvc.perform(post("/v1/find")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TransactionManagementRequest.builder()
                        .referenceNumber("1111112311RB")
                        .transactionStage("INQUIRY")
                        .build())))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test(expected = Exception.class)
    public void testFindHistory_expectFailed_exception() {
        doThrow(GenericException.class).when(historyManagementService).findHistoryByReference(any());
        mockMvc.perform(post("/v1/find")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TransactionManagementRequest.builder()
                        .referenceNumber("1111112311RB")
                        .transactionStage("INQUIRY")
                        .build())))
                .andExpect(status().isOk());
    }
}