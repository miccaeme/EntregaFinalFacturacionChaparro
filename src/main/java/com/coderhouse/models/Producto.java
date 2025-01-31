package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name= "Productos")

public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremental
	
	@Column(name= "Producto")
	private long id;
	@Column(name = "Nombre_Producto")
	private String nombre;
	@Column
	private String descripcion;
	
	/*// Ver de mapear con venta y no con cliente directo
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "venta_producto", 
			joinColumns = @JoinColumn(name= "venta_id"),
			inverseJoinColumns = @JoinColumn (name="producto_id")
			)
	@JsonIgnore
	private List<Cliente> clientes = new ArrayList<>();
	
	// Ver de mapear con venta y no con cliente directo */
	
	/* 
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "cliente_producto", 
			joinColumns = @JoinColumn(name= "venta_id"),
			inverseJoinColumns = @JoinColumn (name="producto_id")
			)
	@JsonIgnore
	private List<Cliente> clientes = new ArrayList<>(); 
	 
	*/
	@ManyToOne(fetch = FetchType.EAGER)
	private Categoria categoria ;
	
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Producto( String nombre) {
		this();
		this.nombre = nombre;
		// TODO Auto-generated constructor stub
	}

	
	
	
}
