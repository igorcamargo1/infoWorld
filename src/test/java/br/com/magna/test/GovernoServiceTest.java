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

import br.com.magna.dto.GovernoDto;
import br.com.magna.exception.GovernoException;
import br.com.magna.model.Governo;
import br.com.magna.repository.GovernoRepository;
import br.com.magna.service.GovernoService;

@ExtendWith(MockitoExtension.class)
class GovernoServiceTest {

    @Mock
    private GovernoRepository governoRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GovernoService governoService;

    private Governo governo;

    @BeforeEach
    void setUp() {
        governo = new Governo();
        governo.setId(1L);
        governo.setLiderPolitico("Luis Inácio");
        governo.setFormaGoverno("Precidencialismo");
    }

    @Test
    void testCriaGoverno() {
        GovernoDto governoDto = new GovernoDto();
        governoDto.setLiderPolitico("Luis Inácio");
        governoDto.setFormaGoverno("Precidencialismo");

        when(governoRepository.save(any())).thenReturn(governo);

        GovernoDto result = governoService.criaGoverno(governoDto);

        assertEquals(governoDto.getLiderPolitico(), result.getLiderPolitico());
        assertEquals(governoDto.getFormaGoverno(), result.getFormaGoverno());
    }

    @Test
    void testAtualizaGoverno() {
        Long idGoverno = 1L;
        Governo governoDto = new Governo();
        governoDto.setLiderPolitico("Luis Inácio");
        governoDto.setFormaGoverno("Precidencialismo");

        when(governoRepository.findById(idGoverno)).thenReturn(java.util.Optional.of(governoDto));
        when(governoRepository.save(any())).thenReturn(governoDto);

        Governo result = governoService.atualizaGoverno(idGoverno, governoDto);

        assertEquals(governoDto.getLiderPolitico(), result.getLiderPolitico());
        assertEquals(governoDto.getFormaGoverno(), result.getFormaGoverno());
    }

    @Test
    void testListaTodos() {
        Pageable pageable = mock(Pageable.class);
        List<Governo> governos = Collections.singletonList(governo);
        PageImpl<Governo> page = new PageImpl<>(governos);

        when(governoRepository.findAll(pageable)).thenReturn(page);

        List<GovernoDto> result = governoService.listaTodos(pageable).getContent();

        assertEquals(1, result.size());
        assertEquals(governo.getId(), result.get(0).getId());
        assertEquals(governo.getLiderPolitico(), result.get(0).getLiderPolitico());
        assertEquals(governo.getFormaGoverno(), result.get(0).getFormaGoverno());
    }

    @Test
    void testBuscaPorId() {
        Long id = 1L;

        when(governoRepository.findById(id)).thenReturn(java.util.Optional.of(governo));

        GovernoDto result = governoService.buscaPorId(id);

        assertEquals(governo.getId(), result.getId());
        assertEquals(governo.getLiderPolitico(), result.getLiderPolitico());
        assertEquals(governo.getFormaGoverno(), result.getFormaGoverno());
    }

    @Test
    void testBuscaLiderPolitico() {
        String liderPolitico = "Luis Inácio";

        when(governoRepository.findByLiderPolitico(liderPolitico)).thenReturn(Collections.singletonList(governo));

        List<Governo> result = governoService.buscaPorLiderPolitico(liderPolitico);

        assertEquals(1, result.size());
    }
        @Test
        void testDeletaGovernoExists() {
            Long idGoverno = 1L;

            when(governoRepository.existsById(idGoverno)).thenReturn(true);

            governoService.deletaGoverno(idGoverno);

            // Verifica se o método deleteById foi chamado com o id correto
            verify(governoRepository).deleteById(idGoverno);
        }

        @Test
        void testDeletaGovernoNotExists() {
            Long idEconomia = 1L;

            when(governoRepository.existsById(idEconomia)).thenReturn(false);

            // Garante que uma exceção EconomiaException seja lançada
            assertThrows(GovernoException.class, () -> governoService.deletaGoverno(idEconomia));
        }

    }
