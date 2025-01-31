package com.coderhouse.models;

import java.time.LocalDateTime;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "Modelo de Ventas")
@Entity
@Table(name= "Ventas")
public class Venta {
	@Schema(description = "ID de cada ventaa", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremental
	@Column(name= "ID_Venta")
	private long id;
	@Schema(description = "Nombre del cliente que realizo la compra", requiredMode = Schema.RequiredMode.REQUIRED, example = "Micaela Chaparro")
	@Column(name = "Nombre_Cliente")
	private String nombreCliente;
	@Schema(description = "ID del Cliente que compro", example = "1")
	@Column(name = "Id_Cliente")
	private String IdCliente;
	@Schema(description = "ID de cada categoria", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025/1/25")
	@Column(name = "FechaVenta")
	private LocalDateTime createdAt;
	@Schema(description = "Monto total de la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "$500000000")
	@Column(name = "MontoTotal")
	private Integer montoTotal;
	@Schema(description = "Status de la venta", example = "Pagado-Pendiente de Pago")
	@Column(name = "Estado_Venta")
	private String estado;
	@Schema(description = "ID de cada categoria", example = "Fectivo-Tarjeta de Debito-Tarjeta de Credito")
	@Column(name = "Metodo_Pago")
	private String metodoDePago;
	
	
	

	@ManyToMany
    @JoinTable(
        name = "cliente_venta",
        joinColumns = @JoinColumn(name = "venta_id"),
        inverseJoinColumns = @JoinColumn(name = "cliente_id")
    )
	@JsonIgnore
    private List<Cliente> clientes = new ArrayList<>();
	
	public Venta() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Venta(long id, String nombreCliente, LocalDateTime createdAt, Integer montoTotal, String estado,
			String metodoDePago) {
		this();
		this.id = id;
		this.nombreCliente = nombreCliente;
		this.createdAt = createdAt;
		this.montoTotal = montoTotal;
		this.estado = estado;
		this.metodoDePago = metodoDePago;
	}

	
}
