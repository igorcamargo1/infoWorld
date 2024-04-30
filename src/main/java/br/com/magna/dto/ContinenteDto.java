package br.com.magna.dto;

public class ContinenteDto {
	private Long id;
	private String nome;
	
	public ContinenteDto() {
		// TODO Auto-generated constructor stub
	}

	public ContinenteDto(Long id, String nome) {
		this.id = id;
		this.nome = nome;
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
	
	
}
