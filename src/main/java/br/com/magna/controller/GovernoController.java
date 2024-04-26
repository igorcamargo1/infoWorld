package br.com.magna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.magna.dto.GovernoDto;
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
}
