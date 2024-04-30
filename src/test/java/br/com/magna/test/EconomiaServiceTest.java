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

import br.com.magna.dto.EconomiaDto;
import br.com.magna.exception.EconomiaException;
import br.com.magna.model.Economia;
import br.com.magna.repository.EconomiaRepository;
import br.com.magna.service.EconomiaService;

@ExtendWith(MockitoExtension.class)
class EconomiaServiceTest {

    @Mock
    private EconomiaRepository economiaRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private EconomiaService economiaService;

    private Economia economia;

    @BeforeEach
    void setUp() {
        economia = new Economia();
        economia.setId(1L);
        economia.setMoeda("Real");
        economia.setPib(1000.0);
    }

    @Test
    void testCriaEconomia() {
        EconomiaDto economiaDto = new EconomiaDto();
        economiaDto.setMoeda("Real");
        economiaDto.setPib(1000.0);

        when(economiaRepository.save(any())).thenReturn(economia);

        EconomiaDto result = economiaService.criaEconomia(economiaDto);

        assertEquals(economiaDto.getMoeda(), result.getMoeda());
        assertEquals(economiaDto.getPib(), result.getPib());
    }

    @Test
    void testAtualizaEconomia() {
        Long idEconomia = 1L;
        Economia economiaDto = new Economia();
        economiaDto.setMoeda("Dólar");
        economiaDto.setPib(2000.0);

        when(economiaRepository.findById(idEconomia)).thenReturn(java.util.Optional.of(economia));
        when(economiaRepository.save(any())).thenReturn(economia);

        Economia result = economiaService.atualizaEconomia(idEconomia, economiaDto);

        assertEquals(economiaDto.getMoeda(), result.getMoeda());
        assertEquals(economiaDto.getPib(), result.getPib());
    }

    @Test
    void testListaTodos() {
        Pageable pageable = mock(Pageable.class);
        List<Economia> economias = Collections.singletonList(economia);
        PageImpl<Economia> page = new PageImpl<>(economias);

        when(economiaRepository.findAll(pageable)).thenReturn(page);

        List<EconomiaDto> result = economiaService.listaTodos(pageable).getContent();

        assertEquals(1, result.size());
        assertEquals(economia.getId(), result.get(0).getId());
        assertEquals(economia.getMoeda(), result.get(0).getMoeda());
        assertEquals(economia.getPib(), result.get(0).getPib());
    }

    @Test
    void testBuscaPorId() {
        Long id = 1L;

        when(economiaRepository.findById(id)).thenReturn(java.util.Optional.of(economia));

        EconomiaDto result = economiaService.buscaPorId(id);

        assertEquals(economia.getId(), result.getId());
        assertEquals(economia.getMoeda(), result.getMoeda());
        assertEquals(economia.getPib(), result.getPib());
    }

    @Test
    void testBuscaPorMoeda() {
        String moeda = "Real";

        when(economiaRepository.findByMoeda(moeda)).thenReturn(Collections.singletonList(economia));

        List<Economia> result = economiaService.buscaPorMoeda(moeda);

        assertEquals(1, result.size());
    }
        @Test
        void testDeletaEconomiaExists() {
            Long idEconomia = 1L;

            when(economiaRepository.existsById(idEconomia)).thenReturn(true);

            economiaService.deletaEconomia(idEconomia);

            // Verifica se o método deleteById foi chamado com o id correto
            verify(economiaRepository).deleteById(idEconomia);
        }

        @Test
        void testDeletaEconomiaNotExists() {
            Long idEconomia = 1L;

            when(economiaRepository.existsById(idEconomia)).thenReturn(false);

            // Garante que uma exceção EconomiaException seja lançada
            assertThrows(EconomiaException.class, () -> economiaService.deletaEconomia(idEconomia));
        }

    }
