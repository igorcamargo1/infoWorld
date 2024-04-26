package br.com.magna.dto;

public class GovernoDto {
	
	private Long id;

	private String formaGoverno;

	private String liderPolitico;

	public GovernoDto(String formaGoverno, String liderPolitico) {
		this.formaGoverno = formaGoverno;
		this.liderPolitico = liderPolitico;
	}
	
	public GovernoDto() {
		// TODO Auto-generated constructor stub
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

