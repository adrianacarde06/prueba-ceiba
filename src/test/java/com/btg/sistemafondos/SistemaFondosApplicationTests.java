package com.btg.sistemafondos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.btg.sistemafondos.enums.TipoTransaccion;
import com.btg.sistemafondos.model.AperturaFondo;
import com.btg.sistemafondos.model.Cliente;
import com.btg.sistemafondos.model.Fondo;
import com.btg.sistemafondos.model.Transaccion;
import com.btg.sistemafondos.repository.FondoRepository;
import com.btg.sistemafondos.repository.TransaccionRepository;
import com.btg.sistemafondos.service.impl.FondoServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SistemaFondosApplicationTests {

	@Mock
    private FondoRepository fondoRepository;

	@Mock
    private TransaccionRepository transaccionRepository;

    @InjectMocks
    private FondoServiceImpl fondoService;

	


	@Test
	public void testFondos(){

		Fondo fon = new Fondo();
        fon.setId(1l);
        fon.setNombre("FPV_BTG_PACTUAL_RECAUDADORA");
		fon.setMontoMinimo(75000d);
		fon.setCategoria("FPV");
		List<Fondo> list = new ArrayList<>();
		list.add(fon);

        when(fondoRepository.findAll()).thenReturn(list);

        List<Fondo> result = fondoService.getAllItems();

        assertEquals(list, result);
	}

	@Test
	public void testHistorial(){

		List<Transaccion> list = new ArrayList<>();

		Fondo fon = new Fondo();
        fon.setId(1l);
        fon.setNombre("FPV_BTG_PACTUAL_RECAUDADORA");
		fon.setMontoMinimo(75000d);
		fon.setCategoria("FPV");

		Cliente cli = new Cliente();
		cli.setNombre("Pepe");
		cli.setApellido("Perez");
		cli.setSaldo(500000d);

		AperturaFondo ape = new AperturaFondo();
		ape.setCliente(cli);
		ape.setFondo(fon);
		ape.setMonto(100000d);

		Transaccion tra = Transaccion.builder()
          .aperturaFondo(ape)
          .tipoTransaccion(TipoTransaccion.APERTURA)
          .build();

		list.add(tra);
        when(transaccionRepository.findByCliente(cli)).thenReturn(list);

        List<Transaccion> result = fondoService.verHistorial(cli);

        assertEquals(list, result);
	}

	
}
