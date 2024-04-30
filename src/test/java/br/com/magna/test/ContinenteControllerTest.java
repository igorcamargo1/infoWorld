package br.com.magna.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.magna.dto.ContinenteDto;
import br.com.magna.model.Continente;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ContinenteControllerTest {

	@LocalServerPort
	private int port;

	private RestTemplate restTemplate;

	private String getBaseUrl() {
		return "http://localhost:" + port + "/continente";
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@BeforeEach
	public void setUp() {
		restTemplate = restTemplate();
	}
	
	
	@Test
	public void testCriarContinente() {
		ContinenteDto continenteDto = new ContinenteDto();
		continenteDto.setNome("América do Norte");

		ResponseEntity<ContinenteDto> response = restTemplate.postForEntity(getBaseUrl() + "/criar", continenteDto,
				ContinenteDto.class);

		assertEquals(201, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals("América do Norte", response.getBody().getNome());
	}
	
	@Test
	public void testBuscaNome() {
		String nome = "América do Norte";

		ResponseEntity<Continente[]> response = restTemplate.getForEntity(getBaseUrl() + "/nomes/{nome}",
				Continente[].class, nome);

		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		List<Continente> continentes = Arrays.asList(response.getBody());
		assertEquals(nome, continentes.get(0).getNome());
	}



}
