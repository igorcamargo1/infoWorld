package br.com.magna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.com.magna.dto.PaisDto;
import br.com.magna.exception.PaisNotFoundException;
import br.com.magna.service.PaisService;

@RestController
@RequestMapping("/pais")
public class PaisController {

    private final PaisService paisService;

    @Autowired
    public PaisController(PaisService paisService) {
        this.paisService = paisService;
    }

    @PostMapping("/criar")
    public ResponseEntity<PaisDto> criarPais(@RequestBody PaisDto paisDto) {
        PaisDto novoPais = paisService.criaPais(paisDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPais);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PaisDto> atualizarPais(@PathVariable("id") Long id, @RequestBody PaisDto paisDto) {
        PaisDto paisAtualizado = paisService.atualizaPais(id, paisDto);
        return ResponseEntity.status(HttpStatus.OK).body(paisAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarPaisPorId(@PathVariable("id") Long id) {
        try {
            paisService.deletaPais(id);
            return ResponseEntity.noContent().build();
        } catch (PaisNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar/todos")
    public ResponseEntity<Page<PaisDto>> listarPaises(Pageable page) {
        return ResponseEntity.ok(paisService.listaTodos(page));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<PaisDto> buscaPorId(@PathVariable("id") Long id) {
        return paisService.buscaPaisPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
