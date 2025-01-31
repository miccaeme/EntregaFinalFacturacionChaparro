package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "Modelo de cada Categoria de producto")
@Entity
@Table(name= "Categorias")

public class Categoria {
	
	@Schema(description = "ID de cada categoria", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremental
	
	@Column(name= "ID_Categoria")
	private long id;
	
	@Column(unique =true, nullable = false, name = "Nombre_Categoria")
	private String nombre;
	@Column(name = "Descripcion_Categoria")
	private String descripcion;
	
	@OneToMany(mappedBy="categoria", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Producto> productos = new ArrayList<>();
	
	
	
	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categoria( String nombre, String descripcion) {
		this();
		
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	
	
}
