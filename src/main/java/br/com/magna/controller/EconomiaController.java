package br.com.magna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.magna.dto.EconomiaDto;
import br.com.magna.service.EconomiaService;

@RestController
@RequestMapping("/economia")
public class EconomiaController {

	@Autowired
	private final EconomiaService economiaService;
	
	public EconomiaController(EconomiaService economiaService) {
		this.economiaService = economiaService;
	}
	
	@PostMapping("/criar")
	public ResponseEntity<EconomiaDto> criarEconomia(@RequestBody EconomiaDto economiaDto){
		EconomiaDto economia = economiaService.criaEconomia(economiaDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(economia);
		
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarEconomiaPorId(@PathVariable Long id) {
	    // Lógica para deletar a economia com base no ID fornecido
	    economiaService.deletarEconomiaPorId(id);
	    return ResponseEntity.noContent().build();
	}
	
}
