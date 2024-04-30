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

import br.com.magna.dto.GovernoDto;
import br.com.magna.model.Governo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GovernoControllerTest {

	@LocalServerPort
	private int port;

	private RestTemplate restTemplate;

	private String getBaseUrl() {
		return "http://localhost:" + port + "/governo";
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
	public void testCriarGoverno() {
		GovernoDto governoDto = new GovernoDto();
		governoDto.setFormaGoverno("Presidencialismo");
		governoDto.setLiderPolitico("Luis Inácio");

		ResponseEntity<GovernoDto> response = restTemplate.postForEntity(getBaseUrl() + "/criar", governoDto,
				GovernoDto.class);

		assertEquals(201, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals("Presidencialismo", response.getBody().getFormaGoverno());
		assertEquals("Luis Inácio", response.getBody().getLiderPolitico());
	}
	
	@Test
	public void testBuscaPorLiderPolitico() {
		String liderPolitico = "Luis Inácio";

		ResponseEntity<Governo[]> response = restTemplate.getForEntity(getBaseUrl() + "/lideresPoliticos/{liderPolitico}",
				Governo[].class, liderPolitico);

		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		List<Governo> economias = Arrays.asList(response.getBody());
		assertEquals(liderPolitico, economias.get(0).getLiderPolitico());
	}

}
