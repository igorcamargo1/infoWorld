package br.com.magna.dto;

public class EconomiaDto {

    private Long id;
    private String moeda;
    private Double pib;

    public EconomiaDto() {
        // TODO Auto-generated constructor stub
    }

    public EconomiaDto(Long id, String moeda, Double pib) {
        this.id = id;
        this.moeda = moeda;
        this.pib = pib;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public Double getPib() {
        return pib;
    }

    public void setPib(Double pib) {
        this.pib = pib;
    }


}