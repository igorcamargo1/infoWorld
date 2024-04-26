package br.com.magna.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_demografia")
public class Demografia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "natalidade")
	private Float natalidade;
	
	@Column(name = "mortalidade")
	private Float mortalidade;
	
	public Demografia() {
		// TODO Auto-generated constructor stub
	}

	public Demografia(Float natalidade, Float mortalidade) {
		this.natalidade = natalidade;
		this.mortalidade = mortalidade;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getNatalidade() {
		return natalidade;
	}

	public void setNatalidade(Float natalidade) {
		this.natalidade = natalidade;
	}

	public Float getMortalidade() {
		return mortalidade;
	}

	public void setMortalidade(Float mortalidade) {
		this.mortalidade = mortalidade;
	}
	
}
