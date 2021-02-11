package com.bjpu.history.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({GenericException.class})
    public ResponseEntity<ApiErrorResponse> genericException(GenericException genericException) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .responseCode(genericException.getSourceSystem().concat(":").concat(genericException.getErrorCode()))
                .errorDescEn(genericException.getErrorDesc())
                .errorDescId(genericException.getErrorDesc())
                .referenceNumber(genericException.getReferenceNumber())
                .build();
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
