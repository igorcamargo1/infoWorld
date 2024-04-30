package br.com.magna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magna.dto.GovernoDto;
import br.com.magna.exception.GovernoException;
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
		dto.setId(governo.getId());
		dto.setFormaGoverno(governo.getFormaGoverno());
		dto.setLiderPolitico(governo.getLiderPolitico());
		return dto;
	}
	
	public Governo atualizaGoverno(long idGoverno, Governo governodto) {
		Governo governo = governoRepository.findById(idGoverno).orElseThrow();
		
		governo.setFormaGoverno(governodto.getFormaGoverno());
		governo.setLiderPolitico(governodto.getLiderPolitico());
		
		return governoRepository.save(governo);
	}
	
	public void deletaGoverno(Long id) {
		if(governoRepository.existsById(id)) governoRepository.deleteById(id);
		else throw new GovernoException("Governo com o ID " + id + " não encontrada");
	}
	
	public Page<GovernoDto> listaTodos(Pageable page){
		return governoRepository.findAll(page).map(this::convertToDto);
	}
	
	public GovernoDto buscaPorId(Long id) {
		Governo governo = governoRepository.findById(id).orElse(null);
		if (governo != null)
			return convertToDto(governo);
		else
			return null;
	}

	public List<Governo> buscaPorLiderPolitico(String liderPolitico) {
		return governoRepository.findByLiderPolitico(liderPolitico);
	}
	
}
