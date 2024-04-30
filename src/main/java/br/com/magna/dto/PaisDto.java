package br.com.magna.dto;

import java.util.Set;

public class PaisDto {

    private Long id;
    private String nome;
    private String capital;
    private String codigoPais;
    private String descricao;
    private Long idContinente;
    private Long idEconomia;
    private Long idGeografia;
    private Long idDemografia;
    private Long idGoverno;
    private Set<Long> idiomas;

    public PaisDto() {
    }

    public PaisDto(Long id, String nome, String capital, String codigoPais, String descricao, Long idContinente,
            Long idEconomia, Long idGeografia, Long idDemografia, Long idGoverno, Set<Long> idiomas) {
        this.id = id;
        this.nome = nome;
        this.capital = capital;
        this.codigoPais = codigoPais;
        this.descricao = descricao;
        this.idContinente = idContinente;
        this.idEconomia = idEconomia;
        this.idGeografia = idGeografia;
        this.idDemografia = idDemografia;
        this.idGoverno = idGoverno;
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

    public Long getIdContinente() {
        return idContinente;
    }

    public void setIdContinente(Long idContinente) {
        this.idContinente = idContinente;
    }

    public Long getIdEconomia() {
        return idEconomia;
    }

    public void setIdEconomia(Long idEconomia) {
        this.idEconomia = idEconomia;
    }

    public Long getIdGeografia() {
        return idGeografia;
    }

    public void setIdGeografia(Long idGeografia) {
        this.idGeografia = idGeografia;
    }

    public Long getIdDemografia() {
        return idDemografia;
    }

    public void setIdDemografia(Long idDemografia) {
        this.idDemografia = idDemografia;
    }

    public Long getIdGoverno() {
        return idGoverno;
    }

    public void setIdGoverno(Long idGoverno) {
        this.idGoverno = idGoverno;
    }

    public Set<Long> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Set<Long> idiomas) {
        this.idiomas = idiomas;
    }
}

