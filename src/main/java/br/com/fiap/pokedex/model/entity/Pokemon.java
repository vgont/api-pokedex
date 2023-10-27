package br.com.fiap.pokedex.model.entity;

import java.time.LocalDate;

public class Pokemon {
	private Long numero;
	private String nome;
	private Double altura;
	private Double peso;
	private String categoria;
	private LocalDate dataDaCaptura;
	
	public Pokemon(Long numero, String nome, Double altura, Double peso, String categoria, LocalDate dataDaCaptura) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.altura = altura;
		this.peso = peso;
		this.categoria = categoria;
		this.dataDaCaptura = dataDaCaptura;
	}
	
	public Pokemon() {
		
	}

	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public LocalDate getDataDaCaptura() {
		return dataDaCaptura;
	}
	public void setDataDaCaptura(LocalDate dataDaCaptura) {
		this.dataDaCaptura = dataDaCaptura;
	}
	
}
