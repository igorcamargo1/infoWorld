package br.com.magna.dto;

import java.util.Set;

public class IdiomaDto {

    private Long id;
    private String nome;
    private Set<Long> paises; // Lista de IDs dos países associados

    public IdiomaDto() {
        // Construtor padrão necessário para serialização/desserialização
    }

    public IdiomaDto(Long id, String nome, Set<Long> paises) {
        this.id = id;
        this.nome = nome;
        this.paises = paises;
    }

    // Getters e setters

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

    public Set<Long> getPaises() {
        return paises;
    }

    public void setPaises(Set<Long> setPaises) {
        this.paises = setPaises;
    }
}
