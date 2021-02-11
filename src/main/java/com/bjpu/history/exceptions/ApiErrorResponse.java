package com.bjpu.history.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse implements Serializable {
    private String responseCode;
    private String errorDescEn;
    private String errorDescId;
    private String referenceNumber;
}
