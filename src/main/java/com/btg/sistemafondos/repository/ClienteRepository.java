package com.btg.sistemafondos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.btg.sistemafondos.model.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
    
}
