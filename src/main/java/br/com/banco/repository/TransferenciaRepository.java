package br.com.banco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.banco.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM transferencia where transferencia.conta_id = :idConta")
	public List<Transferencia> findByConta(Long idConta);
	
}
