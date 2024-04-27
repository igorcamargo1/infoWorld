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

import br.com.magna.dto.DemografiaDto;
import br.com.magna.model.Demografia;
import br.com.magna.service.DemografiaService;

@RestController
@RequestMapping("/demografia")
public class DemografiaController {

	@Autowired
	private final DemografiaService demografiaService;
	
	public DemografiaController(DemografiaService demografiaService) {
		// TODO Auto-generated constructor stub
		this.demografiaService = demografiaService;
	}
	
	@PostMapping("/criar")
	public ResponseEntity<DemografiaDto> criarEconomia(@RequestBody DemografiaDto demografiaDto){
		DemografiaDto demografia = demografiaService.criaDemografia(demografiaDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(demografia);
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Demografia> atualizarEconomia(@PathVariable("id") Long id, @RequestBody Demografia demografiaDto){
		Demografia demografiaAtualizada = demografiaService.atualizaDemografia(id, demografiaDto);
		return ResponseEntity.status(HttpStatus.OK).body(demografiaAtualizada);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarDemografia(@PathVariable("id") Long id) {
	    // Lógica para deletar a economia com base no ID fornecido
		demografiaService.deletaDemografia(id);
	    return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/listar/todos")
	public ResponseEntity<Page<DemografiaDto>> listarDemografia(@PageableDefault(size = 10) Pageable page){
		return ResponseEntity.status(HttpStatus.OK).body(demografiaService.listaTodos(page));
	}	
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<DemografiaDto> buscaPorId(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(demografiaService.buscaPorId(id));
	}
	
	@GetMapping("/natalidades/{natalidade}")
	public ResponseEntity<List<Demografia>> buscaPorDemografia(@PathVariable("natalidade") Float natalidade){
		List<Demografia> demografias = demografiaService.buscaPorNatalidade(natalidade);
		if(!demografias.isEmpty()) {
			return new ResponseEntity<>(demografias, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}
