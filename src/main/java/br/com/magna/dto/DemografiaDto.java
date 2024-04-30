package br.com.magna.dto;

public class DemografiaDto {

	private Long id;
	private Float natalidade;
	private Float mortalidade;
	
	public DemografiaDto() {
		// TODO Auto-generated constructor stub
	}

	public DemografiaDto(Long id, Float natalidade, Float mortalidade) {
		this.id = id;
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
