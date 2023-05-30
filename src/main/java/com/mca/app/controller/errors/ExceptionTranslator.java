package com.mca.app.controller.errors;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

    //CsvValidationException
    @ExceptionHandler({ CsvValidationException.class })
    public ResponseEntity<Object> handleBadRequest(final CsvValidationException ex, final WebRequest request) {
        final String bodyOfResponse = "CSV Validation Error";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    //IOException
    @ExceptionHandler({ IOException.class })
    public ResponseEntity<Object> handleIOException(IOException ex, final WebRequest request) {
        final String bodyOfResponse = "IO Error";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}