package br.com.magna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.magna.dto.EconomiaDto;
import br.com.magna.model.Economia;
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
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Economia> atualizarEconomia(@PathVariable("id") Long id, @RequestBody Economia economiaDto){
		Economia economiaAtualizada = economiaService.atualizaEconomia(id, economiaDto);
		return ResponseEntity.status(HttpStatus.OK).body(economiaAtualizada);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarEconomiaPorId(@PathVariable("id") Long id) {
	    try {
	        // Lógica para deletar a economia com base no ID fornecido
	        economiaService.deletaEconomia(id);
	        return ResponseEntity.noContent().build();
	    } catch (RuntimeException e) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Economia não encontrada", e);
	    }
	}
	@GetMapping("/listar/todos")
	public ResponseEntity<Page<EconomiaDto>> listarEconomia(@PageableDefault(size = 10) Pageable page){
		return ResponseEntity.status(HttpStatus.OK).body(economiaService.listaTodos(page));
	}	
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<EconomiaDto> buscaPorId(@PathVariable("id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(economiaService.buscaPorId(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	@GetMapping("/moedas/{moeda}")
	public ResponseEntity<List<Economia>> buscaPorMoeda(@PathVariable("moeda") String moeda){
		List<Economia> economias = economiaService.buscaPorMoeda(moeda);
		if(!economias.isEmpty()) {
			return new ResponseEntity<>(economias, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
