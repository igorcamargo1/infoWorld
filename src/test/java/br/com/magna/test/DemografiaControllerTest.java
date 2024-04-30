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

import br.com.magna.dto.DemografiaDto;
import br.com.magna.model.Demografia;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemografiaControllerTest {


	@LocalServerPort
	private int port;

	private RestTemplate restTemplate;

	private String getBaseUrl() {
		return "http://localhost:" + port + "/demografia";
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
	public void testCriarDemografia() {
		DemografiaDto demografiaDto = new DemografiaDto();
		demografiaDto.setNatalidade(1000f);
		demografiaDto.setMortalidade(500f);

		ResponseEntity<DemografiaDto> response = restTemplate.postForEntity(getBaseUrl() + "/criar", demografiaDto,
				DemografiaDto.class);

		assertEquals(201, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals(1000f, response.getBody().getNatalidade());
		assertEquals(500f, response.getBody().getMortalidade());
	}
	
	
	@Test
	public void testBuscaPorNatalidade() {
		Float natalidade = 1000f;

		ResponseEntity<Demografia[]> response = restTemplate.getForEntity(getBaseUrl() + "/natalidades/{natalidade}",
				Demografia[].class, natalidade);

		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		List<Demografia> economias = Arrays.asList(response.getBody());
		assertEquals(natalidade, economias.get(0).getNatalidade());
	}
	
}
