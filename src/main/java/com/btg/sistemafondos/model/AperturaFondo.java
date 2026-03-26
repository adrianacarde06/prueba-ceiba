package com.btg.sistemafondos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "AperturaFondo")
public class AperturaFondo {

    @Id
    private String id;

    @DBRef
    private Cliente cliente; 

    @DBRef
    private Fondo fondo; 

    private Double monto;

    
}
