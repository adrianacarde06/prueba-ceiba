package com.btg.sistemafondos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.btg.sistemafondos.enums.TipoTransaccion;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Document(collection = "Transaccion")
public class Transaccion {

    @Id
    private String id;

    @DBRef
    private AperturaFondo aperturaFondo; 

    private TipoTransaccion tipoTransaccion;
}