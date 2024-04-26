package br.com.magna.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_idioma")
public class Idioma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@ManyToMany(mappedBy = "idiomas")
	private Set<Pais> paises = new HashSet<>();

	public Idioma() {
		// TODO Auto-generated constructor stub
	}

	public Idioma(String nome, Set<Pais> paises) {
		super();
		this.nome = nome;
		this.paises = paises;
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

	public Set<Pais> getPaises() {
		return paises;
	}

	public void setPaises(Set<Pais> paises) {
		this.paises = paises;
	}

}
