package br.com.magna.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magna.dto.IdiomaDto;
import br.com.magna.exception.IdiomaException;
import br.com.magna.exception.PaisNotFoundException;
import br.com.magna.model.Idioma;
import br.com.magna.model.Pais;
import br.com.magna.repository.IdiomaRepository;
import br.com.magna.repository.PaisRepository;


import java.util.Set;


@Service
public class IdiomaService {

    @Autowired
    private IdiomaRepository idiomaRepository;

    @Autowired
    private PaisRepository paisRepository; // Repositório de país (supondo que exista)

    public Idioma salvaIdioma(Idioma idioma) {
        return idiomaRepository.save(idioma);
    }

    public IdiomaDto convertToDto(Idioma idioma) {
    	IdiomaDto dto = new IdiomaDto();
    	dto.setId(idioma.getId());
    	dto.setNome(idioma.getNome());
    	dto.setPaises(idioma.getPaises().stream().map(Pais::getId).collect(Collectors.toSet()));
        return dto;
    }

    public IdiomaDto criaIdioma(IdiomaDto idiomaDto) {
        Idioma idioma = new Idioma();
        idioma.setNome(idiomaDto.getNome());
        Set<Pais> paises = idiomaDto.getPaises().stream()
				.map(paisId -> paisRepository.findById(paisId)
						.orElseThrow(() -> new RuntimeException("Pais não encontrado com o ID: " + paisId)))
				.collect(Collectors.toSet());
        idioma.setPaises(paises);
        idioma = salvaIdioma(idioma);
        return convertToDto(idioma);
    }

    public IdiomaDto atualizaIdioma(Long id, IdiomaDto idiomaDto) {
        Idioma idiomaExistente = idiomaRepository.findById(id)
                .orElseThrow(() -> new IdiomaException("Idioma com o ID " + id + " não encontrado"));

        idiomaExistente.setNome(idiomaDto.getNome());
        
        // Atualize a lista de países associados ao idioma
        Set<Pais> paises = idiomaDto.getPaises().stream()
                .map(paisId -> paisRepository.findById(paisId)
                        .orElseThrow(() -> new PaisNotFoundException("País não encontrado com o ID: " + paisId)))
                .collect(Collectors.toSet());
        idiomaExistente.setPaises(paises);
        
        Idioma idiomaAtualizado = salvaIdioma(idiomaExistente);

        return convertToDto(idiomaAtualizado);
    }

    public void deletaIdioma(Long idIdioma) {
        if (!idiomaRepository.existsById(idIdioma)) {
            throw new IdiomaException("Idioma com o ID " + idIdioma + " não encontrado");
        }
        idiomaRepository.deleteById(idIdioma);
    }

    public Page<IdiomaDto> listaTodos(Pageable page) {
        return idiomaRepository.findAll(page).map(this::convertToDto);
    }

    public IdiomaDto buscaPorId(Long id) {
        Idioma idioma = idiomaRepository.findById(id).orElse(null);
        if (idioma != null) {
            return convertToDto(idioma);
        } else {
            return null;
        }
    }
}

