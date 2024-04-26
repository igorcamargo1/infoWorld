package br.com.magna.service;

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
	
	public EconomiaDto criaEconomia(EconomiaDto economiaDto) {
		Economia economia = new Economia();
		
		economia.setMoeda(economiaDto.getMoeda());
		economia.setPib(economiaDto.getPib());
		economia = salvaEconomia(economia);
		return convertToDto(economia);
	}
	
	public EconomiaDto convertToDto(Economia economia) {
		EconomiaDto dto = new EconomiaDto();
		
		dto.setMoeda(economia.getMoeda());
		dto.setPib(economia.getPib());
		
		return dto;
	}
	
//	public Economia atualizaEconomia(Long idEconomia, EconomiaAtualizaDto economiaDto) {
//		Economia economia = economiaRepository.findById(idEconomia).orElseThrow();
//		economia.setMoeda(economiaDto.moeda());
//		economia.setPib(economiaDto.pib());
//		
//		return economiaRepository.save(economia);
//	}
//	
//	public Page<EconomiaDto> listarTodos(Pageable page){
//		return economiaRepository.findAll(page).map(this::convertToDto);
//	}
	
	public void deletarEconomiaPorId(Long idEconomia) {
	    // Verifica se a economia existe antes de tentar deletar
	    if (economiaRepository.existsById(idEconomia)) {
	        economiaRepository.deleteById(idEconomia);
	    } else {
	        throw new RuntimeException("Economia com o ID " + idEconomia + " não encontrada");
	    }
	}
	
	


}
