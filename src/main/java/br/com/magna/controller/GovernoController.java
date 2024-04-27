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

import br.com.magna.dto.GovernoDto;
import br.com.magna.model.Governo;
import br.com.magna.service.GovernoService;

@RestController
@RequestMapping("/governo")
public class GovernoController {

	@Autowired
	private final GovernoService governoService;

	public GovernoController(GovernoService governoService) {
		this.governoService = governoService;
	}

	@PostMapping("/criar")
	public ResponseEntity<GovernoDto> criarGoverno(@RequestBody GovernoDto governoDto) {
		GovernoDto governo = this.governoService.criaGoverno(governoDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(governo);
	}
	
	@PutMapping("/atualizar/{id}")
	ResponseEntity<Governo> atualizarGoverno(@PathVariable("id") long id, @RequestBody Governo governoDto){
		Governo governoAtualizado = governoService.atualizaGoverno(id, governoDto);
		return ResponseEntity.status(HttpStatus.OK).body(governoAtualizado);
	}
	
	@DeleteMapping("/deletar/{id}")
	ResponseEntity<Void> deletarGoverno(@PathVariable("id") Long id){
		governoService.deletaGoverno(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/listar/todos")
	public ResponseEntity<Page<GovernoDto>> listarGoverno(@PageableDefault(size = 10) Pageable page){
		return ResponseEntity.status(HttpStatus.OK).body(governoService.listaTodos(page));
	}	
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<GovernoDto> buscaPorId(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(governoService.buscaPorId(id));
	}
	
	@GetMapping("/lideresPoliticos/{liderPolitico}")
	public ResponseEntity<List<Governo>> buscaPorMoeda(@PathVariable("liderPolitico") String liderPolitico){
		List<Governo> governos = governoService.buscaPorLiderPolitico(liderPolitico);
		if(!governos.isEmpty()) {
			return new ResponseEntity<>(governos, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
