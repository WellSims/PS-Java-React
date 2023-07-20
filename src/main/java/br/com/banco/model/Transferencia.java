package br.com.banco.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Transferencia {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name="data_transferencia")
	private LocalDateTime dataTransferencia;
	
	@NotNull
	private double valor;
	
	@NotNull
	private String tipo;
	
	@Column(name="nome_operador_transacao")
	private String operador;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="conta_id")
	private Conta conta;

	public Transferencia(Long id, @NotNull LocalDateTime dataTransferencia, @NotNull double valor, @NotNull String tipo,
			String operador, @NotNull Conta conta) {
		this.id = id;
		this.dataTransferencia = dataTransferencia;
		this.valor = valor;
		this.tipo = tipo;
		this.operador = operador;
		this.conta = conta;
	}

	public Transferencia() {
	}

}
