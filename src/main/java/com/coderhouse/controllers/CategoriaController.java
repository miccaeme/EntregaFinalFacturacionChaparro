package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
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

import com.coderhouse.models.Categoria;
import com.coderhouse.repositories.CategoriaRepository;
import com.coderhouse.service.CategoriaService;
import com.coderhouse.service.ProductoService;


@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
	@Autowired
	//Si no se inyecta no se puede acceder a sus metodos
	private CategoriaRepository categoriaRepository ;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public List<Categoria> getAllCategoria(){
		
		return categoriaRepository.findAll();
	} //Cuca a todos los alumnos
	
	@GetMapping("/{id}")
	// Busca la Categoria, si lo encuentra confirma y guarda y si no muestra error 404.
	//Busca un solo alumno por ID.
	public ResponseEntity <Categoria> getCategoriaById(@PathVariable Long id){
		if (categoriaRepository.existsById(id)) {
			Categoria categoria = categoriaRepository.findById(id).get();
		return ResponseEntity.ok(categoria);
		
		} else {
			return ResponseEntity.notFound().build();
			}
	}
	
	
	@PostMapping
	public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
		try {
			Categoria createdCategoria = categoriaService.saveCategoria(categoria);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoria);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoriaDetails) {
		try {
			Categoria updatedCategoria = categoriaService.updateCategoriaById(id, categoriaDetails);
			return ResponseEntity.ok(updatedCategoria);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategoriaById(@PathVariable Long id) {
		try {
			if (((CrudRepository<Categoria, Long>) categoriaService).existsById(id)) {
				categoriaService.deleteCategoriaById(id);
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
