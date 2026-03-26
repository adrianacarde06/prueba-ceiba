package com.btg.sistemafondos.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {

    private String code;
    private String message;
    
}