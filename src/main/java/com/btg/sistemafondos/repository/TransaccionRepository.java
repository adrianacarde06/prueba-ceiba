package com.btg.sistemafondos.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.btg.sistemafondos.model.Cliente;
import com.btg.sistemafondos.model.Transaccion;

@Repository
public interface TransaccionRepository extends MongoRepository<Transaccion, String> {

    @Query("SELECT t FROM Transaccion t WHERE t.aperturaFondo.cliente = :cliente")
    List<Transaccion> findByCliente(Cliente cliente);
    
}
