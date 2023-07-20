package br.com.banco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Conta {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_conta")
	private Long id;
	
	@Column(name="nome_responsavel")
	private String responsavel;

	public Conta(Long id) {
		this.id = id;
	}

	public Conta(Long id, String responsavel) {
		this.id = id;
	}
	
	public Conta() {
	}
	
	
	
}
