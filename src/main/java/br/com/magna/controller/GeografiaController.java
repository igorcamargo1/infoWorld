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

import br.com.magna.dto.GeografiaDto;
import br.com.magna.model.Geografia;
import br.com.magna.service.GeografiaService;

@RestController
@RequestMapping("/geografia")
public class GeografiaController {

	@Autowired
	private final GeografiaService geografiaService;
	
	public GeografiaController(GeografiaService economiaService) {
		this.geografiaService = economiaService;
	}
	
	@PostMapping("/criar")
	public ResponseEntity<GeografiaDto> criarGeografia(@RequestBody GeografiaDto geografiaDto){
		GeografiaDto geografia = geografiaService.criaGeografia(geografiaDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(geografia);
		
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Geografia> atualizarGeografia(@PathVariable("id") Long id, @RequestBody Geografia geografiaDto){
		Geografia geografiaAtualizada = geografiaService.atualizaGeografia(id, geografiaDto);
		return ResponseEntity.status(HttpStatus.OK).body(geografiaAtualizada);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarGeografia(@PathVariable("id") Long id) {
	    // Lógica para deletar a economia com base no ID fornecido
	    geografiaService.deletaGeografia(id);
	    return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/listar/todos")
	public ResponseEntity<Page<GeografiaDto>> listarGeografia(@PageableDefault(size = 10) Pageable page){
		return ResponseEntity.status(HttpStatus.OK).body(geografiaService.listaTodos(page));
	}	
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<GeografiaDto> buscaPorId(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(geografiaService.buscaPorId(id));
	}
	
	@GetMapping("/climas/{clima}")
	public ResponseEntity<List<Geografia>> buscaPorMoeda(@PathVariable("clima") String clima){
		List<Geografia> geografias = geografiaService.buscaPorClima(clima);
		if(!geografias.isEmpty()) {
			return new ResponseEntity<>(geografias, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
