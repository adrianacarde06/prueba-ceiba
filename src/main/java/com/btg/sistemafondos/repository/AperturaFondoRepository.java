package com.btg.sistemafondos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.btg.sistemafondos.model.AperturaFondo;

@Repository
public interface AperturaFondoRepository extends MongoRepository<AperturaFondo, String>{
    
}
