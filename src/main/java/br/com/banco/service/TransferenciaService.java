package br.com.banco.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.repository.custom.TransferenciaCustomRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TransferenciaService {
	
	@Autowired
	private TransferenciaRepository transferenciaRepository;
	
	@Autowired
	private TransferenciaCustomRepository customRepository;
	
	@Transactional
	public List<Transferencia> findAll(){
		return transferenciaRepository.findAllByOrderByContaAsc();	
	}
	
	@Transactional
	public List<Transferencia> findByConta(Long idConta){
		return transferenciaRepository.findByConta(idConta);
	}
	
	@Transactional
	public List<Transferencia> findByFiltros(LocalDateTime dataInicio, LocalDateTime dataFim, String operador){
		List<Transferencia> transferencias = new ArrayList<>();
		if(dataInicio != null && dataFim != null && (operador.isEmpty() == false)) {
			return transferencias = transferenciaRepository.findByDataTransferenciaAndOperador(dataInicio, dataFim, operador);
		} 
		return	transferencias = customRepository.findByFiltroPersonalizado(dataInicio, dataFim, operador);
	
	}
}
