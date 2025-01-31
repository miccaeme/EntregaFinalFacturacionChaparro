package com.coderhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.coderhouse.Dtos.DateResponseDTO;

@Service

public class FechaService {
	@Autowired
	private RestTemplate rt;
	
	public DateResponseDTO obtenerFechaActual() {
		try {
			final String URL = "https://timeapi.io/api/Time/current/zone?timeZone=America/Argentina/Buenos_Aires";
			return rt.getForObject(URL, DateResponseDTO.class);
		}
		catch (RestClientException e){
			System.err.println("Error no se pudo conectar con la Api externa" + e.getMessage());
			return null;
		}
		
		
	}
	
}
