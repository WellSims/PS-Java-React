package br.com.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.Transferencia;
import br.com.banco.service.TransferenciaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/transferencia")
@RestController
@CrossOrigin(value = "*")
public class TransferenciaController {

	@Autowired
	private TransferenciaService transferenciaService;
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Transferencia>> listar(){
		return ResponseEntity.ok().body(transferenciaService.findAll());
	}
	
	
	
}
