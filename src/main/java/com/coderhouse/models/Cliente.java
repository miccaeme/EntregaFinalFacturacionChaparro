package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Schema(description = "Modelo de Clientes")
@Table(name= "Clientes")

public class Cliente {
	
	@Schema(description = "ID de cada cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id //PrimaryKey
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremental
	@Column(name= "Cliente_ID")
	private long id;
	@Schema(description = "Nombre de cada cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Micaela")
	@Column(name= "Nombre")
	private String nombre;
	@Schema(description = "Apellido de cada cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Chaparro")
	@Column(name="apellido")
	private String apellido;
	@Schema(description = "ID de cada cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "12357896")
	@Column (unique = true, nullable = false) // va a ser unico y no nulo
	private int DNI;
	
	@Schema(description = "ID de cada cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "CL1")
	@Column(name="Numero De Cliente")
	private String nDeCliente;
	@Schema(description = "Listado de Ventas que realizo el cliente")
 	@ManyToMany(mappedBy = "clientes", fetch = FetchType.EAGER)
    private List<Venta> ventas = new ArrayList<>();
 	
	
	/* Ver de mapear con venta y no con producto 
	@ManyToMany(mappedBy = "Producto", fetch = FetchType.EAGER)
	private List<Producto> productos = new ArrayList<>();
	 */
	
	@Schema(description = "Fecha en la que se creo el Cliente", example = "1")
	private LocalDateTime createdAt;
	
	

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Cliente(String nombre, String apellido, int dNI, String nDeCliente) {
		this();
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.nDeCliente = nDeCliente;
		
	}


	
	

}
