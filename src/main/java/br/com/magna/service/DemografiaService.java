package br.com.magna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magna.dto.DemografiaDto;
import br.com.magna.model.Demografia;
import br.com.magna.repository.DemografiaRepository;

@Service
public class DemografiaService {

	@Autowired
	private DemografiaRepository demografiaRepository;

	public Demografia salvaDemografia(Demografia demografia) {
		return demografiaRepository.save(demografia);
	}

	public DemografiaDto convertToDto(Demografia demografia) {
		DemografiaDto dto = new DemografiaDto();
		dto.setId(demografia.getId());
		dto.setMortalidade(demografia.getMortalidade());
		dto.setNatalidade(demografia.getNatalidade());

		return dto;
	}

	public DemografiaDto criaDemografia(DemografiaDto demografiaDto) {
		Demografia demografia = new Demografia();
		demografia.setMortalidade(demografiaDto.getMortalidade());
		demografia.setNatalidade(demografiaDto.getNatalidade());
		demografia = salvaDemografia(demografia);
		return convertToDto(demografia);
	}

	public Demografia atualizaDemografia(Long idDemografia, Demografia demografiaDto) {
		Demografia demografia = demografiaRepository.findById(idDemografia).orElseThrow();
		demografia.setMortalidade(demografiaDto.getMortalidade());
		demografia.setNatalidade(demografiaDto.getNatalidade());
		return demografiaRepository.save(demografia);
	}

	public void deletaDemografia(Long idDemografia) {
		// Verifica se a economia existe antes de tentar deletar
		if (demografiaRepository.existsById(idDemografia))
			demografiaRepository.deleteById(idDemografia);
		else
			throw new RuntimeException("Demografia com o ID " + idDemografia + " não encontrada");

	}
	
	public Page<DemografiaDto> listaTodos(Pageable page) {
		return demografiaRepository.findAll(page).map(this::convertToDto);
	}

	public DemografiaDto buscaPorId(Long id) {
		Demografia demografia = demografiaRepository.findById(id).orElse(null);
		if (demografia != null)
			return convertToDto(demografia);
		else
			return null;
	}
	
	public List<Demografia> buscaPorNatalidade(Float natalidade) {
		return demografiaRepository.findByNatalidade(natalidade);
	}

}
