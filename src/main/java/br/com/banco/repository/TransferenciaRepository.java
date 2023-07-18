package br.com.banco.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.banco.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM transferencia WHERE transferencia.conta_id = :idConta")
	public List<Transferencia> findByConta(Long idConta);
	
	@Query(nativeQuery = true, value = "SELECT * FROM transferencia WHERE transferencia.nome_operador_transacao = :operador")
	public List<Transferencia> findByOperador(String operador);
	
	@Query(nativeQuery = true, value = "SELECT * FROM transferencia WHERE data_transferencia BETWEEN :initial AND :end")
    List<Transferencia> findByDateTime(LocalDateTime initial, LocalDateTime end);

    @Query(nativeQuery = true, value = "SELECT * FROM transferencia WHERE nome_operador_transacao = :operador AND data_transferencia BETWEEN :initial AND :end")
    List<Transferencia> findByOperadorDateTime(String operador, LocalDateTime initial, LocalDateTime end);
}
