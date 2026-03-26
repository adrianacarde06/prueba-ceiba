package com.btg.sistemafondos.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "Fondo")
public class Fondo implements Serializable {

    @Id
    @NonNull
    private Long id;

    private String nombre;

    private Double montoMinimo;

    private String categoria;
    
}
