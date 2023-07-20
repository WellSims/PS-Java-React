package br.com.banco.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.service.TransferenciaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/transferencia")
@RestController
@CrossOrigin(value = "*")
@Api(tags = {"Transferência"}, description = "(Recurso para buscar informações relativas a transferência cadastradas.)")
public class TransferenciaController {
	
	@Autowired
	private TransferenciaService transferenciaService;
	
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Busca uma transferência por id.")
	public ResponseEntity<Transferencia> buscarPorId(@PathVariable Long id){
		
		if(id < 0) {
			return ResponseEntity.badRequest().build();
		}
		
		Transferencia transferencia = transferenciaService.findById(id);
		
		if (transferencia == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(transferencia);
	}
	
	@GetMapping
	@ApiOperation(value = "Busca todas as transferências cadastradas no sistema.")
	public ResponseEntity<List<Transferencia>> listar(){
		return ResponseEntity.ok().body(transferenciaService.findAll());
	}
	
	@GetMapping(value = "/")
	@ApiOperation(value = "Realiza uma consulta por transferências utilizando os filtros utilizados na requisição.")
    public ResponseEntity<List<Transferencia>> filtrarPorCampo(	
            @RequestParam(name = "dataInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(name = "dataFim", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestParam(name = "operador", required = false) String operador
            ) {
		if(dataInicio == null && dataFim == null && operador == null) {
			return ResponseEntity.badRequest().build();
		}
		
		List<Transferencia> transferencias = transferenciaService.findByFiltros(dataInicio, dataFim, operador);
        return ResponseEntity.ok().body(transferencias);
    }
	
}
