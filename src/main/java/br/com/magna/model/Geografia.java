package br.com.magna.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_geografia")
public class Geografia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "latitude")
	private Float latitude;
	
	@Column(name = "longitude")
	private Float longitude;
	
	@Column(name = "area")
	private Float area;
	
	@Column(name = "populacao")
	private Long populacao;
	
	@Column(name = "clima")
	private String clima;
	
	public Geografia() {
		// TODO Auto-generated constructor stub
	}

	public Geografia(Float latitude, Float longitude, Float area, Long populacao, String clima) {
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
