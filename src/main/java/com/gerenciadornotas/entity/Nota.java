package com.gerenciadornotas.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Nota {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 15)
	@NotEmpty(message="Campo nome está vazio")
	private String nome;
	
	@Column(nullable = false, length = 100)
	@NotEmpty(message="Campo descricao está vazio")
	private String descricao;
	
	@Column(name="data_cadastro", updatable = false)
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataCadastro;
	
	@PrePersist
	public void prepersist() {
		setDataCadastro(LocalDate.now());
	}

}
