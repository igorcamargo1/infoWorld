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

import br.com.magna.dto.EconomiaDto;
import br.com.magna.model.Economia;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EconomiaControllerTest {

	@LocalServerPort
	private int port;

	private RestTemplate restTemplate;

	private String getBaseUrl() {
		return "http://localhost:" + port + "/economia";
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
	public void testCriarEconomia() {
		EconomiaDto economiaDto = new EconomiaDto();
		economiaDto.setMoeda("Real");
		economiaDto.setPib(1000.00);

		ResponseEntity<EconomiaDto> response = restTemplate.postForEntity(getBaseUrl() + "/criar", economiaDto,
				EconomiaDto.class);

		assertEquals(201, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals("Real", response.getBody().getMoeda());
		assertEquals(1000, response.getBody().getPib());
	}

	@Test
	public void testBuscaPorMoeda() {
		String moeda = "Real";

		ResponseEntity<Economia[]> response = restTemplate.getForEntity(getBaseUrl() + "/moedas/{moeda}",
				Economia[].class, moeda);

		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		List<Economia> economias = Arrays.asList(response.getBody());
		assertEquals(moeda, economias.get(0).getMoeda());
	}

}
