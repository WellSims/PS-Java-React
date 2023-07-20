package br.com.banco.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.service.ContaService;
import br.com.banco.service.TransferenciaService;
import io.restassured.http.ContentType;

@WebMvcTest
public class TransferenciaControllerTest {

	@Autowired
	private TransferenciaController transferenciaController;

	@MockBean
	private TransferenciaService transferenciaService;

	@MockBean
	private ContaService contaService;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.transferenciaController);
	}

	@Test
	public void deveRetornarSucesso_QuandoBuscarTransferencia() {

		when(this.transferenciaService.findById(1L))
			.thenReturn(new Transferencia(1L, LocalDateTime.now(), 200D, "TRANSFERÊNCIA", "Bruno", new Conta(1L, "Leonardo")));
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/transferencia/{id}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveRetornarNaoEncontrado_QuandoBuscarTransferencia() {

		when(this.transferenciaService.findById(200L))
		.thenReturn(null);
			
		given()
			.accept(ContentType.JSON)
			.when()
			.get("/transferencia/{id}", 200L)
			.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deveRetornarBadRequest_QuandoBuscarTransferencia() {

		given()
		.accept(ContentType.JSON)
	.when()
		.get("/transferencia/?init=2023-07-18T21:04&end=2023-07-19T00:07&nomeOperador=bruno")
	.then()
		.statusCode(HttpStatus.BAD_REQUEST.value());
	
	verify(this.transferenciaService, never()).findByFiltros(null, null, null);
	}
}
