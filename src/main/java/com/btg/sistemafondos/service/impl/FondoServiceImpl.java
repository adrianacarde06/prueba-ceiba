package com.btg.sistemafondos.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.sistemafondos.constants.MensajesConstants;
import com.btg.sistemafondos.enums.TipoTransaccion;
import com.btg.sistemafondos.exception.RequestException;
import com.btg.sistemafondos.exception.ValidacionesException;
import com.btg.sistemafondos.model.AperturaFondo;
import com.btg.sistemafondos.model.Cliente;
import com.btg.sistemafondos.model.Fondo;
import com.btg.sistemafondos.model.Transaccion;
import com.btg.sistemafondos.repository.AperturaFondoRepository;
import com.btg.sistemafondos.repository.ClienteRepository;
import com.btg.sistemafondos.repository.FondoRepository;
import com.btg.sistemafondos.repository.TransaccionRepository;
import com.btg.sistemafondos.service.FondoService;

@Service
public class FondoServiceImpl implements FondoService {

    @Autowired
    FondoRepository fondoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    AperturaFondoRepository aperturaFondoRepository;

    @Autowired
    TransaccionRepository transaccionRepository;

    public static final Double MONTO_INICIAL = 500000d;

    @Override
    public List<Fondo> getAllItems() {
        return fondoRepository.findAll();
    }

    @Override
    public void crearFondo(Fondo fondo) {
        fondoRepository.save(fondo);
    }

    @Override
    public void crearCliente(Cliente cliente) {
        cliente.setSaldo(MONTO_INICIAL);
        clienteRepository.save(cliente);
    }

    @Override
    public void aperturaFondo(AperturaFondo aperturaFondo){
        if(aperturaFondo.getCliente() == null){
            throw new RequestException("E-401", MensajesConstants.CLIENTE_REQUERIDO);
        }
        if(aperturaFondo.getFondo() == null){
            throw new RequestException("E-402", MensajesConstants.FONDO_REQUERIDO);
        }

        Cliente cliente = clienteRepository.findById(aperturaFondo.getCliente().getId()).orElseThrow(() -> new NoSuchElementException(MensajesConstants.CLIENTE_REQUERIDO));
        Fondo fondo = fondoRepository.findById(aperturaFondo.getFondo().getId()).orElseThrow(() -> new NoSuchElementException(MensajesConstants.FONDO_REQUERIDO));

        if(fondo.getMontoMinimo() > aperturaFondo.getMonto()){
            throw new ValidacionesException("E-300", MensajesConstants.MONTO_MINIMO_FONDO);
        }
           
        if(cliente.getSaldo() < aperturaFondo.getMonto()){
            throw new ValidacionesException("E-301", MensajesConstants.ERROR_SALDO_CLIENTE);
        }
        
        actualizarSaldo(cliente, aperturaFondo.getMonto());
        aperturaFondoRepository.save(aperturaFondo);
        crearTransaccion(aperturaFondo, TipoTransaccion.APERTURA);
    }

    private void actualizarSaldo(Cliente cliente, Double monto){
        cliente.setSaldo(cliente.getSaldo()-monto);
        clienteRepository.save(cliente);
    }

    private void crearTransaccion(AperturaFondo aperturaFondo, TipoTransaccion tipoTransaccion){
        Transaccion transaccion = Transaccion.builder()
          .aperturaFondo(aperturaFondo)
          .tipoTransaccion(tipoTransaccion)
          .build();
        transaccionRepository.save(transaccion);
    }

    @Override
    public List<Transaccion> verHistorial(Cliente cliente) {
        return transaccionRepository.findByCliente(cliente);
    }
    
}
