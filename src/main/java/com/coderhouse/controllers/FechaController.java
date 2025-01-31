package com.coderhouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.Dtos.DateResponseDTO;
import com.coderhouse.service.FechaService;

@RestController
@RequestMapping("/api/fecha")
public class FechaController {
	@Autowired
	private FechaService fechaService;
	
	private int contadorDeInvocaciones = 0;
	
	private String ultimaVentaRealizada ="N/A";
	
	
	@GetMapping
	public ResponseEntity <String> obtenerFecha(){
		contadorDeInvocaciones++;
		DateResponseDTO fechaActual = fechaService.obtenerFechaActual() ;
		
		if(fechaActual == null) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error, servicipo de la Api no disponible");
			
		}
		
		String mensaje = String.format("Fecha actual: %s ",
						fechaActual.getDate()
						);
				
				ultimaVentaRealizada = String.format("%s",
						fechaActual.getDate());
				
				
				
		
		return ResponseEntity.ok(mensaje);
	
	}
	
}
