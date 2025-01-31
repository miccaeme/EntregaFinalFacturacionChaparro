package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.Dtos.VentaRealizadaPorClienteDTO;
import com.coderhouse.models.Cliente;
import com.coderhouse.repositories.ClienteRepository;
import com.coderhouse.service.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	
	@Autowired
	//Si no se inyecta no se puede acceder a sus metodos
	private ClienteService clienteService ;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> getAllCliente(){
		try {
			List<Cliente> cliente = clienteService.getAllCliente();
			return ResponseEntity.ok(cliente);
		
		}catch (Exception e) {
				
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//error 500
		}
	} 
	
	@GetMapping("/{id}")
	// Busca al cliente, si lo encuentra confirma y guarda y si no muestra error 404.
	//Busca un solo alumno por ID.
	public ResponseEntity <Cliente> getClienteById(@PathVariable Long id){
		try {
				Cliente cliente = clienteService.findById(id);
				return ResponseEntity.ok(cliente);// 200
		}
		catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build(); //400
		}
				
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping
	public ResponseEntity <Cliente> createCliente(@RequestBody Cliente cliente) {
		try {
			Cliente clienteCreado = clienteService.saveCliente(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);//200
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}}
		
	@PutMapping("/{id}")
		public ResponseEntity<Cliente> updateClienteById(@PathVariable Long id, @RequestBody Cliente clienteModificado) {
			try {
				Cliente updateCliente = clienteService.updateClienteById(id, clienteModificado);
				return ResponseEntity.ok(updateCliente);
			} catch (IllegalArgumentException e) {
				return ResponseEntity.notFound().build(); // 404
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
			}}
		

	@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteClienteById(@PathVariable Long id) {
			try {
				clienteService.deleteClienteById(id);
				return ResponseEntity.noContent().build(); // 400

			} catch (IllegalArgumentException e) {
				return ResponseEntity.notFound().build(); // 404

			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
			}
			}
// Check
		@PostMapping ("/crearVentaParaCliente")
		public ResponseEntity<Cliente> crearVentaParaCliente(@RequestBody VentaRealizadaPorClienteDTO dto){
			try {
				Cliente cliente = clienteService.guardarVentaParaCliente(dto);
				return ResponseEntity.ok(cliente); //200

			} catch (IllegalArgumentException e) {
				return ResponseEntity.badRequest().build(); 

			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
			}
		}
	
	
	
	
	
	
	
	
	
	}
