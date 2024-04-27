package br.com.magna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magna.dto.ContinenteDto;
import br.com.magna.model.Continente;
import br.com.magna.repository.ContinenteRepository;

@Service
public class ContinenteService {

	@Autowired
	private ContinenteRepository continenteRepository;
	
	public Continente salvaContinente(Continente continente) {
		return continenteRepository.save(continente);
	}
	
	public ContinenteDto convertToDto(Continente continente) {
		ContinenteDto dto = new ContinenteDto();
		dto.setId(continente.getId());
		dto.setNome(continente.getNome());
		return dto;
	}

	public ContinenteDto criaContinente(ContinenteDto continenteDto) {
		Continente continente = new Continente();

		continente.setNome(continenteDto.getNome());
		continente = salvaContinente(continente);
		return convertToDto(continente);
	}
	
	public Continente atualizaContinente(Long idContinente, Continente continenteDto) {
		Continente continente = continenteRepository.findById(idContinente).orElseThrow();
		continente.setNome(continenteDto.getNome());
		return continenteRepository.save(continente);
	}
	
	public void deletaContinente(Long idContinente) {
		// Verifica se a economia existe antes de tentar deletar
		if (continenteRepository.existsById(idContinente))
			continenteRepository.deleteById(idContinente);
		else
			throw new RuntimeException("Continente com o ID " + idContinente + " não encontrada");

	}
	
	public Page<ContinenteDto> listaTodos(Pageable page) {
		return continenteRepository.findAll(page).map(this::convertToDto);
	}

	public ContinenteDto buscaPorId(Long id) {
		Continente continente = continenteRepository.findById(id).orElse(null);
		if (continente != null)
			return convertToDto(continente);
		else
			return null;
	}

	public List<Continente> buscaPorNome(String nome) {
		return continenteRepository.findByNome(nome);
	}
	
	
}
