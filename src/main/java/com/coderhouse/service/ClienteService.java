package com.coderhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderhouse.Dtos.VentaRealizadaPorClienteDTO;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Venta;
import com.coderhouse.repositories.ClienteRepository;
import com.coderhouse.repositories.VentaRepository;

@Service

public class ClienteService {
	@Autowired
	//Si no se inyecta no se puede acceder a sus metodos
	private ClienteRepository clienteRepository ;
	@Autowired
	private VentaRepository ventaRepository;
	
	public List<Cliente> getAllCliente(){
		return clienteRepository.findAll();
	}
	
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
	
	}
	@Transactional
	
	public Cliente saveCliente (Cliente cliente) {
		return clienteRepository.save(cliente);
		
	}
	
	@Transactional
	public Cliente updateClienteById(Long id, Cliente clienteDetails) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
		cliente.setNombre(clienteDetails.getNombre());
		cliente.setApellido(clienteDetails.getApellido());
		
		if(clienteDetails.getDNI()!=0){
			
			cliente.setDNI(clienteDetails.getDNI());
			}
		if (clienteDetails.getNDeCliente()!=null && !clienteDetails.getNDeCliente().isEmpty()){
			
			cliente.setNDeCliente(clienteDetails.getNDeCliente());
			
			
		}
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void deleteClienteById(Long id) {
		if(!clienteRepository.existsById(id)) {
			throw new IllegalArgumentException("Cliente no encontrado");
		}
		clienteRepository.deleteById(id);
	}
	
	 @Transactional 
	public Cliente guardarVentaParaCliente(VentaRealizadaPorClienteDTO dto) {
		Cliente cliente = clienteRepository.findById(dto.getClienteId()).orElseThrow(() -> new IllegalArgumentException("Cliente No Encontrado"));
		
		for(Long ventaIds: dto.getVentaIds()) {
			Venta venta = ventaRepository.findById(ventaIds).orElseThrow(() -> new IllegalArgumentException("Venta no Encontrada)"));
			cliente.getVentas().add(venta);
			venta.getClientes().add(cliente);
			ventaRepository.save(venta);
		}
		return clienteRepository.save(cliente);
	}
	
}
