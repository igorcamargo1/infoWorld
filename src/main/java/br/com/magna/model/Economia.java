package br.com.magna.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_economia")
public class Economia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "moeda")
	private String moeda;
	
	@Column(name = "pib")
	private Double pib;
	
	public Economia() {
		// TODO Auto-generated constructor stub
	}
	
	public Economia(String moeda, Double pib) {
		this.moeda = moeda;
		this.pib = pib;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public Double getPib() {
		return pib;
	}

	public void setPib(Double pib) {
		this.pib = pib;
	}
	
}
