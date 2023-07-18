package br.com.banco.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.model.Conta;
import br.com.banco.repository.ContaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	@Transactional
	public Conta findById(Long id){
		return contaRepository.findById(id).get();
	}
	
}
