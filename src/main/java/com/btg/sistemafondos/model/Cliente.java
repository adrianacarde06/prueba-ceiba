package com.btg.sistemafondos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "Cliente")
public class Cliente {

    @Id
    private String id;

    private String nombre;

    private String apellido;

    private Double saldo;
    
}
