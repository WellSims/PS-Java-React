package br.com.banco.model;

import java.time.LocalDate;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Transferencia {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name="data_transferencia")
	private LocalDate dataTransferencia;
	
	@NotNull
	private double valor;
	
	@NotNull
	private String tipo;
	
	@NotNull
	@Column(name="nome_operador_transacao")
	private String operador;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="conta_id")
	private Conta conta;
	
}
