package br.com.banco.repository.custom;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.banco.model.Transferencia;

@Repository
public class TransferenciaCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Transferencia> findByFiltroPersonalizado(LocalDateTime dataInicio, LocalDateTime dataFim, String operador) {
		String sql = "SELECT * FROM transferencia ";
		if (!operador.isEmpty()) {
			sql += " WHERE nome_operador_transacao = '" + operador + "'";
		}
		if (dataInicio != null && dataFim != null) {
			if (sql.contains("nome_operador_transacao")) {
				sql += " AND ";
			}
			if (!sql.contains("WHERE")) {
				sql += " WHERE ";
			}
			sql += "data_transferencia BETWEEN '" + dataInicio + "' AND '" + dataFim + "'";
		}
		if (dataInicio != null && dataFim == null) {
			if (sql.contains("nome_operador_transacao")) {
				sql += " AND ";
			}
			if (!sql.contains("WHERE")) {
				sql += " WHERE ";
			}
			sql += "data_transferencia >= '" + dataInicio + "'";
		}
		if (dataInicio == null && dataFim != null) {
			if (sql.contains("nome_operador_transacao")) {
				sql += " AND ";
			}
			if (!sql.contains("WHERE")) {
				sql += " WHERE ";
			}
			sql += "data_transferencia <= '" + dataFim + "'";
		}
		List<Transferencia> transferencias = (List<Transferencia>) entityManager.createNativeQuery(sql, Transferencia.class).getResultList();
		return transferencias;
	}
}
