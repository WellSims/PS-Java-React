package br.com.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.service.ContaService;
import br.com.banco.service.TransferenciaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/conta")
@RestController
@CrossOrigin(value = "*")
@Api(tags = {"Conta"}, description = "(Recurso para buscar informações relativas a contas cadastradas.)")
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	@Autowired
	private TransferenciaService transferenciaService;
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Busca uma conta a partir do id passado na requisição.")
	public ResponseEntity<Conta> findById(@PathVariable Long id){
		if(id < 0) {
			return ResponseEntity.badRequest().build();
		}
		
		Conta conta = contaService.findById(id);
		
		if (conta == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(conta);
	}
	
	@GetMapping("/{idConta}/transferencias")
	@ApiOperation(value = "Busca todas as transferências cadastradas no sistema com o id da conta utiliza na requisição.")
	public ResponseEntity<List<Transferencia>> findByConta(@PathVariable Long idConta){
		
		if(idConta < 0) {
			return ResponseEntity.badRequest().build();
		}
		
		Conta conta = contaService.findById(idConta);
		
		if (conta == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(transferenciaService.findByConta(conta.getId()));
	}

}
