package br.com.magna.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_pais")
public class Pais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;

	@Column(name = "capital")
	private String capital;

	@Column(name = "codigo_pais")
	private String codigoPais;
	
	@Column(name = "descricao")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_continente")
	private Continente continente;
	
	@OneToOne
	@JoinColumn(name = "id_economia")
	private Economia economia;

	@OneToOne
	@JoinColumn(name = "id_geografia")
	private Geografia geografia;
	
	@OneToOne
	@JoinColumn(name = "id_demografia")
	private Demografia demografia;
	
	@OneToOne
	@JoinColumn(name = "id_governo")
	private Governo governo;
	
	@ManyToMany
	@JoinTable(
			name = "tbl_pais_idioma",
			joinColumns = @JoinColumn(name = "id_pais"),
			inverseJoinColumns = @JoinColumn(name = "id_idioma")
			)
	private Set<Idioma> idiomas = new HashSet<>();
	
	public Pais() {
		// TODO Auto-generated constructor stub
	}

	public Pais(String nome, String capital, String codigoPais, String descricao, Continente continente,
			Economia economia, Geografia geografia, Demografia demografia, Governo governo, Set<Idioma> idiomas) {
		this.nome = nome;
		this.capital = capital;
		this.codigoPais = codigoPais;
		this.descricao = descricao;
		this.continente = continente;
		this.economia = economia;
		this.geografia = geografia;
		this.demografia = demografia;
		this.governo = governo;
		this.idiomas = idiomas;
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

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Continente getContinente() {
		return continente;
	}

	public void setContinente(Continente continente) {
		this.continente = continente;
	}

	public Economia getEconomia() {
		return economia;
	}

	public void setEconomia(Economia economia) {
		this.economia = economia;
	}

	public Geografia getGeografia() {
		return geografia;
	}

	public void setGeografia(Geografia geografia) {
		this.geografia = geografia;
	}

	public Demografia getDemografia() {
		return demografia;
	}

	public void setDemografia(Demografia demografia) {
		this.demografia = demografia;
	}

	public Governo getGoverno() {
		return governo;
	}

	public void setGoverno(Governo governo) {
		this.governo = governo;
	}

	public Set<Idioma> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(Set<Idioma> idiomas) {
		this.idiomas = idiomas;
	}
	
	
}
