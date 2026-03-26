package com.btg.sistemafondos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.btg.sistemafondos.dto.ErrorDTO;
import com.btg.sistemafondos.exception.RequestException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDTO> runtimeExceptionHandler(RuntimeException ex){
        ErrorDTO errorDTO = ErrorDTO.builder()
        .code("E-500")
        .message(ex.getMessage())
        .build();
        return new ResponseEntity(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorDTO> requestExceptionHandler(RequestException ex){
        ErrorDTO errorDTO = ErrorDTO.builder()
        .code(ex.getCode())
        .message(ex.getMessage())
        .build();
        return new ResponseEntity(errorDTO, HttpStatus.BAD_REQUEST);
    }
    
}
