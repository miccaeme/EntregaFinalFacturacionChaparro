package com.coderhouse.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration

public class OpenApiConfig {
	@Bean
	 OpenAPI customOpenAPI() {
		 
		return new OpenAPI()
				.info(new Info()
						.title("API REST Full | Java | CoderHouse")
						.version("3.0.0")
						.description("La API REST proporciona endpoints para administrar ventas"
                        		+ " de cursos en una plataforma educativa. Permite realizar operaciones "
                        		+ "CRUD (Crear, Leer, Actualizar, Eliminar) tanto para ventas como "
                        		+ "para clientes y productos. Los endpoints permiten listar, agregar, mostrar, "
                        		+ "editar y eliminar clientes y productos. La API está documentada utilizando "
                        		+ "Swagger, lo que facilita la comprensión de los endpoints y su uso.")
						.contact(new Contact()
								.name("Milagros Micaela Chaparro")
								.email("micaChaparro@mimail.com")
								.url("https://coderhouse.com.ar"))
						.license(new License()
								.name("Licencia")
								.url("https://github.com/miccaeme"))
						)
						.servers(List.of(new Server()
								.url("http://localhost:8080")
								.description("Servidor Local")));
		 
	 }
}
