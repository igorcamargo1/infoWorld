package br.com.magna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magna.dto.EconomiaDto;
import br.com.magna.model.Economia;
import br.com.magna.repository.EconomiaRepository;

@Service
public class EconomiaService {

	@Autowired
	private EconomiaRepository economiaRepository;

	public Economia salvaEconomia(Economia economia) {
		return economiaRepository.save(economia);
	}
	
	public EconomiaDto convertToDto(Economia economia) {
		EconomiaDto dto = new EconomiaDto();
		dto.setId(economia.getId());
		dto.setMoeda(economia.getMoeda());
		dto.setPib(economia.getPib());

		return dto;
	}

	public EconomiaDto criaEconomia(EconomiaDto economiaDto) {
		Economia economia = new Economia();

		economia.setMoeda(economiaDto.getMoeda());
		economia.setPib(economiaDto.getPib());
		economia = salvaEconomia(economia);
		return convertToDto(economia);
	}


	public Economia atualizaEconomia(Long idEconomia, Economia economiaDto) {
		Economia economia = economiaRepository.findById(idEconomia).orElseThrow();
		economia.setMoeda(economiaDto.getMoeda());
		economia.setPib(economiaDto.getPib());

		return economiaRepository.save(economia);
	}

	public void deletaEconomia(Long idEconomia) {
		// Verifica se a economia existe antes de tentar deletar
		if (economiaRepository.existsById(idEconomia))
			economiaRepository.deleteById(idEconomia);
		else
			throw new RuntimeException("Economia com o ID " + idEconomia + " não encontrada");

	}

	public Page<EconomiaDto> listaTodos(Pageable page) {
		return economiaRepository.findAll(page).map(this::convertToDto);
	}

	public EconomiaDto buscaPorId(Long id) {
		Economia economia = economiaRepository.findById(id).orElse(null);
		if (economia != null)
			return convertToDto(economia);
		else
			return null;
	}

	public List<Economia> buscaPorMoeda(String moeda) {
		return economiaRepository.findByMoeda(moeda);
	}

}