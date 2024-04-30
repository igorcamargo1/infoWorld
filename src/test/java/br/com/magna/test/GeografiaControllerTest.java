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

import br.com.magna.dto.GeografiaDto;
import br.com.magna.model.Geografia;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GeografiaControllerTest {

	@LocalServerPort
	private int port;

	private RestTemplate restTemplate;

	private String getBaseUrl() {
		return "http://localhost:" + port + "/geografia";
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
	public void testCriarGeografia() {
		GeografiaDto geografiaDto = new GeografiaDto();
		geografiaDto.setArea(1000f);
		geografiaDto.setLatitude(500f);
		geografiaDto.setLongitude(500f);
		geografiaDto.setPopulacao(20000000L);
		geografiaDto.setClima("Tropical");

		ResponseEntity<GeografiaDto> response = restTemplate.postForEntity(getBaseUrl() + "/criar", geografiaDto,
				GeografiaDto.class);

		assertEquals(201, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals(1000f, response.getBody().getArea());
		assertEquals(500f, response.getBody().getLatitude());
		assertEquals(500f, response.getBody().getLongitude());
		assertEquals(20000000L, response.getBody().getPopulacao());
		assertEquals("Tropical", response.getBody().getClima());
	}
	
	
	@Test
	public void testBuscaPorGeografia() {
		String clima = "Tropical";

		ResponseEntity<Geografia[]> response = restTemplate.getForEntity(getBaseUrl() + "/climas/{clima}",
				Geografia[].class, clima);

		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		List<Geografia> geografias = Arrays.asList(response.getBody());
		assertEquals(clima, geografias.get(0).getClima());
	}

}
