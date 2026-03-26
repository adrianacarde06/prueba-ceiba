package com.btg.sistemafondos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btg.sistemafondos.constants.MensajesConstants;
import com.btg.sistemafondos.exception.RequestException;
import com.btg.sistemafondos.model.AperturaFondo;
import com.btg.sistemafondos.model.Cliente;
import com.btg.sistemafondos.model.Fondo;
import com.btg.sistemafondos.model.Transaccion;
import com.btg.sistemafondos.service.FondoService;

@RestController
@RequestMapping("/api/fondo")
public class FondoController {


    @Autowired 
    private FondoService fondoService;

    @GetMapping(value="/fondos",produces = "application/json")
    public List<Fondo> getFondos() {
        return  fondoService.getAllItems();
    }

    @PostMapping(value = "/crearFondo")
    public ResponseEntity<String>  crearFondo(@RequestBody Fondo fondo){

        fondoService.crearFondo(fondo);
        return new ResponseEntity<>(MensajesConstants.FONDO_CREADO, HttpStatus.OK);

    }

    @PostMapping(value = "/crearCliente")
    public ResponseEntity<String>  crearCliente(@RequestBody Cliente cliente){

        fondoService.crearCliente(cliente);
        return new ResponseEntity<>(MensajesConstants.CLIENTE_CREADO, HttpStatus.OK);

    }

    @PostMapping(value = "/aperturaFondo")
    public ResponseEntity  aperturaFondo(@RequestBody AperturaFondo aperturaFondo){
        fondoService.aperturaFondo(aperturaFondo);
        return new ResponseEntity<>(MensajesConstants.APERTURA_FONDO_CREADO, HttpStatus.OK);
    }

    @PostMapping(value = "/cancelarFondo")
    public ResponseEntity  cancelarFondo(@RequestBody AperturaFondo aperturaFondo){
        fondoService.aperturaFondo(aperturaFondo);
        return new ResponseEntity<>(MensajesConstants.APERTURA_FONDO_CREADO, HttpStatus.OK);
    }

    @GetMapping(value="/verHistorial",produces = "application/json")
    public List<Transaccion> verHistorial(@RequestBody Cliente cliente) {
        return  fondoService.verHistorial(cliente);
    }
    
}
