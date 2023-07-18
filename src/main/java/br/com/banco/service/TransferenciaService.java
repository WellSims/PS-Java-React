package br.com.banco.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TransferenciaService {
	
	@Autowired
	private TransferenciaRepository transferenciaRepository;
	
	@Transactional
	public List<Transferencia> findByConta(Long idConta){
		return transferenciaRepository.findByConta(idConta);
	}
	
}
