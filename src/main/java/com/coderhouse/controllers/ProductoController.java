package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.Dtos.AsignacionCategoriaProducto;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Producto;
import com.coderhouse.repositories.ProductoRepository;
import com.coderhouse.service.ClienteService;
import com.coderhouse.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/producto")
@Tag(name="Gestion de productos", description = "Endpoints para gestionar productos")
public class ProductoController {
	
	@Autowired
	//Si no se inyecta no se puede acceder a sus metodos
	private ProductoService productoService ;
	
	@Operation(summary = "obtener lista de productos")
	@ApiResponses(value= {
			@ApiResponse(responseCode ="200", description ="Lista de productos obtenida correctamente", 
					content = {
							@Content(mediaType="application/json",
									schema = @Schema(implementation = Producto.class))}),
			@ApiResponse(responseCode ="404", description ="error al intentar mostrar Lista de productos ", 
			content = {
					@Content(mediaType="application/json",
							schema = @Schema(implementation = ErrorResponse.class))}),
			@ApiResponse(responseCode ="404", description ="errorinterno del servisor ", 
			content = {
					@Content(mediaType="application/json",
							schema = @Schema(implementation = ErrorResponse.class))})
			})
	@GetMapping("/producto")
	public ResponseEntity<List<Producto>> getAllProducto(){
		try {
			List<Producto> producto = productoService.getAllProducto();
			return ResponseEntity.ok(producto);
			
		}catch (IllegalArgumentException e) {
				return ResponseEntity.notFound().build(); //404
			
		
		}catch (Exception e) {
				
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//error 500
		}
	} 
	
	
	@Operation(summary = "obtener producto por ID")
	@ApiResponses(value= {
			@ApiResponse(responseCode ="200", description ="Producto obtenido correctamente", 
					content = {
							@Content(mediaType="application/json",
									schema = @Schema(implementation = Producto.class))}),
			@ApiResponse(responseCode ="404", description ="error al intentar mostrar Producto ", 
			content = {
					@Content(mediaType="application/json",
							schema = @Schema(implementation = ErrorResponse.class))}),
			@ApiResponse(responseCode ="404", description ="errorinterno del servidor ", 
			content = {
					@Content(mediaType="application/json",
							schema = @Schema(implementation = ErrorResponse.class))})
			})
	
	@GetMapping("/{id}")
	// Busca al producto, si lo encuentra confirma y guarda y si no muestra error 404.
	//Busca un solo productopor ID.
	public ResponseEntity <Producto> getProductoById(@PathVariable Long id){
		try {
				Producto producto = productoService.findById(id);
				return ResponseEntity.ok(producto);// 200
		}
		catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build(); //404
		}
				
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping
	public ResponseEntity <Producto> createProducto(@RequestBody Producto producto) {
		try {
			Producto productoCreado = productoService.saveProducto(producto);
			return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);//200
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}}
		
		@PutMapping("/{id}")
		public ResponseEntity<Producto> updateProgramaById(@PathVariable Long id, @RequestBody Producto productoModificado) {
			try {
				Producto updateProducto = productoService.updateProductoById(id, productoModificado);
				return ResponseEntity.ok(updateProducto);
			} catch (IllegalArgumentException e) {
				return ResponseEntity.notFound().build(); // 404
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
			}}
		@Operation(summary = "Eliminar producto por ID")
		@ApiResponses(value= {
				@ApiResponse(responseCode ="204", description ="Eliminar producto correctamente", 
						content = {
								@Content(mediaType="application/json",
										schema = @Schema(implementation = Producto.class))}),
				@ApiResponse(responseCode ="404", description ="error al intentar eliminar el producto", 
				content = {
						@Content(mediaType="application/json",
								schema = @Schema(implementation = ErrorResponse.class))}),
				@ApiResponse(responseCode ="404", description ="error interno del servidor ", 
				content = {
						@Content(mediaType="application/json",
								schema = @Schema(implementation = ErrorResponse.class))})
				})

		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteProductoById(@PathVariable Long id) {
			try {
				productoService.deleteProductoById(id);
				return ResponseEntity.noContent().build(); // 400

			} catch (IllegalArgumentException e) {
				return ResponseEntity.notFound().build(); // 404

			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
			}
		
		}
		
		@PostMapping("/asignarCategoria")
		public ResponseEntity<Producto> asignarCategoriaAProducto(@RequestBody AsignacionCategoriaProducto dto){
			try {
				Producto productoActualizado = productoService.asignarCategoriaAProducto(
						dto.getProductoId(),
						dto.getCategoriaId()
						);
				return ResponseEntity.ok(productoActualizado);

			} catch (IllegalArgumentException e) {
				return ResponseEntity.notFound().build(); // 404

			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
			}
		}
		

}
