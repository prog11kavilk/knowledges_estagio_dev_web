package br.com.kaykvilela.alunos.modelo;

import jdk.jfr.Percentage;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int idade;
	private String curso;
	private double aproveitamento;
	private LocalDate dataCadastro = LocalDate.now();
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public double getAproveitamento() {
		return aproveitamento;
	}

	public void setAproveitamento(double aproveitamento) {
		this.aproveitamento = aproveitamento;
	}
}
