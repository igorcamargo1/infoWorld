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

import br.com.magna.dto.IdiomaDto;
import br.com.magna.exception.IdiomaException;
import br.com.magna.service.IdiomaService;

@RestController
@RequestMapping("/idioma")
public class IdiomaController {

    private final IdiomaService idiomaService;

    @Autowired
    public IdiomaController(IdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }

    @PostMapping("/criar")
    public ResponseEntity<IdiomaDto> criarIdioma(@RequestBody IdiomaDto idiomaDto) {
        IdiomaDto novoIdioma = idiomaService.criaIdioma(idiomaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoIdioma);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<IdiomaDto> atualizarIdioma(@PathVariable("id") Long id, @RequestBody IdiomaDto idiomaDto) {
        IdiomaDto idiomaAtualizado = idiomaService.atualizaIdioma(id, idiomaDto);
        return ResponseEntity.status(HttpStatus.OK).body(idiomaAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarIdiomaPorId(@PathVariable("id") Long id) {
        try {
            idiomaService.deletaIdioma(id);
            return ResponseEntity.noContent().build();
        } catch (IdiomaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar/todos")
    public ResponseEntity<Page<IdiomaDto>> listarIdiomas(Pageable page) {
        return ResponseEntity.ok(idiomaService.listaTodos(page));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<IdiomaDto> buscaPorId(@PathVariable("id") Long id) {
        IdiomaDto idiomaDto = idiomaService.buscaPorId(id);
        if (idiomaDto != null) {
            return ResponseEntity.ok(idiomaDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
