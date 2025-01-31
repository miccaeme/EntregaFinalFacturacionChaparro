package com.coderhouse.Dtos;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description="Modelo de Venta Realizada por cliente")
public class VentaRealizadaPorClienteDTO {
	@Schema(description = "ID del cliente que realiza la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Long clienteId;
	
	@Schema(description = "Lista de Ventas por IDs", requiredMode = Schema.RequiredMode.REQUIRED, example = "['1']" )
	private List<Long> ventaIds;
	
	private LocalDateTime fechaActual;
}
