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

import br.com.magna.dto.ContinenteDto;
import br.com.magna.model.Continente;
import br.com.magna.service.ContinenteService;

@RestController
@RequestMapping("/continente")
public class ContinenteController {
	
	@Autowired
	private final ContinenteService continenteService;
	
	public ContinenteController(ContinenteService continenteService) {
		// TODO Auto-generated constructor stub
		this.continenteService = continenteService;
	}
	
	@PostMapping("/criar")
	public ResponseEntity<ContinenteDto> criarContinente(@RequestBody ContinenteDto continenteDto){
		ContinenteDto continente = continenteService.criaContinente(continenteDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(continente);
		
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Continente> atualizarEconomia(@PathVariable("id") Long id, @RequestBody Continente continenteDto){
		Continente continenteAtualizado = continenteService.atualizaContinente(id, continenteDto);
		return ResponseEntity.status(HttpStatus.OK).body(continenteAtualizado);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarContinente(@PathVariable("id") Long id) {
	    // Lógica para deletar a economia com base no ID fornecido
	    continenteService.deletaContinente(id);
	    return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/listar/todos")
	public ResponseEntity<Page<ContinenteDto>> listarContinente(@PageableDefault(size = 10) Pageable page){
		return ResponseEntity.status(HttpStatus.OK).body(continenteService.listaTodos(page));
	}	
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<ContinenteDto> buscaPorId(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(continenteService.buscaPorId(id));
	}
	
	@GetMapping("/nomes/{nome}")
	public ResponseEntity<List<Continente>> buscaPorMoeda(@PathVariable("nome") String nome){
		List<Continente> continentes = continenteService.buscaPorNome(nome);
		if(!continentes.isEmpty()) {
			return new ResponseEntity<>(continentes, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
