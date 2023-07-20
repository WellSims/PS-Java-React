package br.com.banco.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.banco.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
	
	public List<Transferencia> findAllByOrderByContaAsc();
	
	@Query(nativeQuery = true, value = "SELECT * FROM transferencia WHERE transferencia.conta_id = :idConta order by transferencia.conta_id asc")
	public List<Transferencia> findByConta(Long idConta);
	
	@Query(nativeQuery = true, value = "SELECT * FROM transferencia WHERE transferencia.nome_operador_transacao = :operador")
	public List<Transferencia> findByOperador(String operador);
	
	@Query(nativeQuery = true, value = "SELECT * FROM transferencia WHERE data_transferencia BETWEEN :dataInicio AND :dataFim")
	public List<Transferencia> findByDataTransferencia(LocalDateTime dataInicio, LocalDateTime dataFim);

    @Query(nativeQuery = true, value = "SELECT * FROM transferencia WHERE nome_operador_transacao = :operador AND data_transferencia BETWEEN :dataInicio AND :dataFim")
    public List<Transferencia> findByDataTransferenciaAndOperador(LocalDateTime dataInicio, LocalDateTime dataFim, String operador);
}
