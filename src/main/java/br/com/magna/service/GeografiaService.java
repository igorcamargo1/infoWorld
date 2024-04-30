package br.com.magna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magna.dto.GeografiaDto;
import br.com.magna.exception.GeografiaException;
import br.com.magna.model.Geografia;
import br.com.magna.repository.GeografiaRepository;
import br.com.magna.validator.AreaValidator;
import br.com.magna.validator.PopulacaoValidator;

@Service
public class GeografiaService {

	@Autowired
	private GeografiaRepository geografiaRepository;
	
	public Geografia salvaGeografia(Geografia geografia) {
		return geografiaRepository.save(geografia);
	}
	
	public GeografiaDto convertToDto(Geografia geografia) {
		GeografiaDto dto = new GeografiaDto();
		dto.setId(geografia.getId());
		dto.setLatitude(geografia.getLatitude());
		dto.setLongitude(geografia.getLongitude());
		dto.setArea(geografia.getArea());
		dto.setClima(geografia.getClima());
		dto.setPopulacao(geografia.getPopulacao());
		return dto;
	}

	public GeografiaDto criaGeografia(GeografiaDto geografiaDto) {
		AreaValidator.validate(geografiaDto);
		PopulacaoValidator.validate(geografiaDto);
		Geografia geografia = new Geografia();

		geografia.setLatitude(geografiaDto.getLatitude());
		geografia.setLongitude(geografiaDto.getLongitude());
		geografia.setArea(geografiaDto.getArea());
		geografia.setClima(geografiaDto.getClima());
		geografia.setPopulacao(geografiaDto.getPopulacao());
		geografia = salvaGeografia(geografia);
		return convertToDto(geografia);
	}


	public Geografia atualizaGeografia(Long idGeografia, Geografia geografiaDto) {
		Geografia geografia = geografiaRepository.findById(idGeografia).orElseThrow();
		geografia.setLatitude(geografiaDto.getLatitude());
		geografia.setLongitude(geografiaDto.getLongitude());
		geografia.setArea(geografiaDto.getArea());
		geografia.setClima(geografiaDto.getClima());
		geografia.setPopulacao(geografiaDto.getPopulacao());
		return geografiaRepository.save(geografia);
	}

	public void deletaGeografia(Long idGeografia) {
		// Verifica se a economia existe antes de tentar deletar
		if (geografiaRepository.existsById(idGeografia))
			geografiaRepository.deleteById(idGeografia);
		else
			throw new GeografiaException("Geografia com o ID " + idGeografia + " não encontrada");

	}

	public Page<GeografiaDto> listaTodos(Pageable page) {
		return geografiaRepository.findAll(page).map(this::convertToDto);
	}

	public GeografiaDto buscaPorId(Long id) {
		Geografia geografia = geografiaRepository.findById(id).orElse(null);
		if (geografia != null)
			return convertToDto(geografia);
		else
			return null;
	}

	public List<Geografia> buscaPorClima(String clima) {
		return geografiaRepository.findByClima(clima);
	}
}
