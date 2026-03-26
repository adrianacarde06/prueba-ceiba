package com.btg.sistemafondos.service;

import java.util.List;

import com.btg.sistemafondos.model.AperturaFondo;
import com.btg.sistemafondos.model.Cliente;
import com.btg.sistemafondos.model.Fondo;
import com.btg.sistemafondos.model.Transaccion;

public interface FondoService {

    public List<Fondo> getAllItems();

    public void crearFondo(Fondo fondo);

    public void crearCliente(Cliente cliente);

    public void aperturaFondo(AperturaFondo aperturaFondo);

    public List<Transaccion> verHistorial(Cliente cliente);
    
}
