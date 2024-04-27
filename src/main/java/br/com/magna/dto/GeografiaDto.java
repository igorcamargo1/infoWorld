package br.com.magna.dto;


public class GeografiaDto {
	
	private Long id;
	private Float latitude;
	private Float longitude;
	private Float area;
	private Long populacao;
	private String clima;
	
	public GeografiaDto() {
		// TODO Auto-generated constructor stub
	}
	
	public GeografiaDto(Long id, Float latitude, Float longitude, Float area, Long populacao, String clima) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.area = area;
		this.populacao = populacao;
		this.clima = clima;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Float getLatitude() {
		return latitude;
	}


	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}


	public Float getLongitude() {
		return longitude;
	}


	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}


	public Float getArea() {
		return area;
	}


	public void setArea(Float area) {
		this.area = area;
	}


	public Long getPopulacao() {
		return populacao;
	}


	public void setPopulacao(Long populacao) {
		this.populacao = populacao;
	}


	public String getClima() {
		return clima;
	}


	public void setClima(String clima) {
		this.clima = clima;
	}
	
	
	
	
}
