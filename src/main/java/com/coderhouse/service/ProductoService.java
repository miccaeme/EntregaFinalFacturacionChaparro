package com.coderhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderhouse.models.Categoria;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Producto;
import com.coderhouse.repositories.CategoriaRepository;
import com.coderhouse.repositories.ClienteRepository;
import com.coderhouse.repositories.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	//Si no se inyecta no se puede acceder a sus metodos
	private ProductoRepository productoRepository ;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Producto> getAllProducto(){
		return productoRepository.findAll();
	}
	
	public Producto findById(Long id) {
		return productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("producto no encontrado"));
	
	}
	@Transactional
	
	public Producto saveProducto (Producto producto) {
		return productoRepository.save(producto);
		
	}
	
	@Transactional
	public Producto updateProductoById(Long id, Producto productoDetails) {
		Producto producto  = productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
		producto.setNombre(productoDetails.getNombre());
		producto.setDescripcion(productoDetails.getDescripcion());
		
		
		return productoRepository.save(producto);
	}
	
	@Transactional
	public void deleteProductoById(Long id) {
		if(!productoRepository.existsById(id)) {
			throw new IllegalArgumentException("Prodcuto no encontrado");
		}
		productoRepository.deleteById(id);
	}
	@Transactional
	public Producto asignarCategoriaAProducto(  Long productoId ,Long categoriaId) {
		Categoria categoria = categoriaRepository.findById(categoriaId).orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada"));
		Producto producto  = productoRepository.findById(productoId).orElseThrow(() -> new IllegalArgumentException("producto no encontrado"));
	
		producto.setCategoria(categoria);
		return productoRepository.save(producto);
	}
}
