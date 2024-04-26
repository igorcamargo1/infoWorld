package br.com.magna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magna.dto.GovernoDto;
import br.com.magna.model.Governo;
import br.com.magna.repository.GovernoRepository;

@Service
public class GovernoService {
	
	@Autowired
	private GovernoRepository governoRepository;
	
	public Governo salvaGoverno(Governo governo) {
		return governoRepository.save(governo);
	}
	
	public GovernoDto criaGoverno(GovernoDto governoDto) {
		Governo governo = new Governo();
		
		governo.setFormaGoverno(governoDto.getFormaGoverno());
		governo.setLiderPolitico(governoDto.getLiderPolitico());
		governo = salvaGoverno(governo);
		return convertToDto(governo);
	}

	private GovernoDto convertToDto(Governo governo) {
		GovernoDto dto = new GovernoDto();
		
		dto.setFormaGoverno(governo.getFormaGoverno());
		dto.setLiderPolitico(governo.getLiderPolitico());
		return dto;
	}
}
