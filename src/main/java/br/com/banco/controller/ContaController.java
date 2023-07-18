package br.com.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.service.ContaService;
import br.com.banco.service.TransferenciaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/conta")
@RestController
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	@Autowired
	private TransferenciaService transferenciaService;
	
	
	@GetMapping("/{id}")
	public Conta findById(@PathVariable Long id){
		Conta t = contaService.findById(id);
		return t;
	}
	
	@GetMapping("/{idConta}/transferencias")
	public List<Transferencia> findByConta(@PathVariable Long idConta){
		List<Transferencia> t = transferenciaService.findByConta(idConta);
		return t;
	}

}
