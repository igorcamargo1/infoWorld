package br.com.magna.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestTemplate;

import br.com.magna.dto.GeografiaDto;
import br.com.magna.exception.GeografiaException;
import br.com.magna.model.Geografia;
import br.com.magna.repository.GeografiaRepository;
import br.com.magna.service.GeografiaService;

@ExtendWith(MockitoExtension.class)
class GeografiaServiceTest {

    @Mock
    private GeografiaRepository geografiaRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GeografiaService geografiaService;

    private Geografia geografia;

    @BeforeEach
    void setUp() {
        geografia = new Geografia();
        geografia.setId(1L);
        geografia.setClima("Tropical");
        geografia.setLatitude(1000.0f);
        geografia.setLongitude(500.0f);
        geografia.setPopulacao(2220000L);
        geografia.setArea(500000f);
    }

    @Test
    void testCriaGeografia() {
        GeografiaDto geografiaDto = new GeografiaDto();
        geografiaDto.setClima("Tropical");
        geografiaDto.setLatitude(1000.0f);
        geografiaDto.setLongitude(500.0f);
        geografiaDto.setPopulacao(2220000L);
        geografiaDto.setArea(500000f);

        when(geografiaRepository.save(any())).thenReturn(geografia);

        GeografiaDto result = geografiaService.criaGeografia(geografiaDto);

        assertEquals(geografiaDto.getClima(), result.getClima());
        assertEquals(geografiaDto.getLatitude(), result.getLatitude());
        assertEquals(geografiaDto.getLongitude(), result.getLongitude());
        assertEquals(geografiaDto.getPopulacao(), result.getPopulacao());
        assertEquals(geografiaDto.getArea(), result.getArea());
    }

    @Test
    void testAtualizaGeografia() {
        Long idGeografia = 1L;
        Geografia geografiaDto = new Geografia();
        geografiaDto.setClima("Tropical");
        geografiaDto.setLatitude(1000.0f);
        geografiaDto.setLongitude(500.0f);
        geografiaDto.setPopulacao(2220000L);
        geografiaDto.setArea(500000f);

        when(geografiaRepository.findById(idGeografia)).thenReturn(java.util.Optional.of(geografia));
        when(geografiaRepository.save(any())).thenReturn(geografia);

        Geografia result = geografiaService.atualizaGeografia(idGeografia, geografiaDto);

        assertEquals(geografiaDto.getClima(), result.getClima());
        assertEquals(geografiaDto.getLatitude(), result.getLatitude());
        assertEquals(geografiaDto.getLongitude(), result.getLongitude());
        assertEquals(geografiaDto.getPopulacao(), result.getPopulacao());
        assertEquals(geografiaDto.getArea(), result.getArea());
    }

    @Test
    void testListaTodos() {
        Pageable pageable = mock(Pageable.class);
        List<Geografia> geografias = Collections.singletonList(geografia);
        PageImpl<Geografia> page = new PageImpl<>(geografias);

        when(geografiaRepository.findAll(pageable)).thenReturn(page);

        List<GeografiaDto> result = geografiaService.listaTodos(pageable).getContent();

        assertEquals(1, result.size());
        assertEquals(geografia.getId(), result.get(0).getId());
        assertEquals(geografia.getClima(), result.get(0).getClima());
        assertEquals(geografia.getLatitude(), result.get(0).getLatitude());
        assertEquals(geografia.getLongitude(), result.get(0).getLongitude());
        assertEquals(geografia.getPopulacao(), result.get(0).getPopulacao());
    }

    @Test
    void testBuscaPorId() {
        Long id = 1L;

        when(geografiaRepository.findById(id)).thenReturn(java.util.Optional.of(geografia));

        GeografiaDto result = geografiaService.buscaPorId(id);

        assertEquals(geografia.getId(), result.getId());
        assertEquals(geografia.getClima(), result.getClima());
        assertEquals(geografia.getLatitude(), result.getLatitude());
        assertEquals(geografia.getLongitude(), result.getLongitude());
        assertEquals(geografia.getPopulacao(), result.getPopulacao());
        assertEquals(geografia.getArea(), result.getArea());
    }

    @Test
    void testBuscaPorMoeda() {
        String clima = "Europa";

        when(geografiaRepository.findByClima(clima)).thenReturn(Collections.singletonList(geografia));

        List<Geografia> result = geografiaService.buscaPorClima(clima);

        assertEquals(1, result.size());
    }
        @Test
        void testDeletaGeografiaExists() {
            Long idGeografia = 1L;

            when(geografiaRepository.existsById(idGeografia)).thenReturn(true);

            geografiaService.deletaGeografia(idGeografia);

            // Verifica se o método deleteById foi chamado com o id correto
            verify(geografiaRepository).deleteById(idGeografia);
        }

        @Test
        void testDeletaEconomiaNotExists() {
            Long idEconomia = 1L;

            when(geografiaRepository.existsById(idEconomia)).thenReturn(false);

            // Garante que uma exceção EconomiaException seja lançada
            assertThrows(GeografiaException.class, () -> geografiaService.deletaGeografia(idEconomia));
        }

    }
