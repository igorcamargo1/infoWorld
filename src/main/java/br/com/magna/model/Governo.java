package br.com.magna.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_governo")
public class Governo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "forma_governo")
	private String formaGoverno;
	
	@Column(name = "lider_politico")
	private String liderPolitico;
	
	public Governo() {
		// TODO Auto-generated constructor stub
	}

	public Governo(String formaGoverno, String liderPolitico) {
		this.formaGoverno = formaGoverno;
		this.liderPolitico = liderPolitico;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFormaGoverno() {
		return formaGoverno;
	}

	public void setFormaGoverno(String formaGoverno) {
		this.formaGoverno = formaGoverno;
	}

	public String getLiderPolitico() {
		return liderPolitico;
	}

	public void setLiderPolitico(String liderPolitico) {
		this.liderPolitico = liderPolitico;
	}
}
