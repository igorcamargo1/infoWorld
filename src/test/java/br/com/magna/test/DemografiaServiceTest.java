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

import br.com.magna.dto.DemografiaDto;
import br.com.magna.exception.DemografiaException;
import br.com.magna.model.Demografia;
import br.com.magna.repository.DemografiaRepository;
import br.com.magna.service.DemografiaService;

@ExtendWith(MockitoExtension.class)
class DemografiaServiceTest {

    @Mock
    private DemografiaRepository demografiaRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private DemografiaService demografiaService;

    private Demografia demografia;

    @BeforeEach
    void setUp() {
        demografia = new Demografia();
        demografia.setId(1L);
        demografia.setNatalidade(1000.0f);
        demografia.setMortalidade(500.0f);
    }

    @Test
    void testCriaDemografia() {
        DemografiaDto demografiaDto = new DemografiaDto();
        demografiaDto.setNatalidade(1000.0f);
        demografiaDto.setMortalidade(500.0f);
        
        when(demografiaRepository.save(any())).thenReturn(demografia);

        DemografiaDto result = demografiaService.criaDemografia(demografiaDto);

        assertEquals(demografiaDto.getNatalidade(), result.getNatalidade());
        assertEquals(demografiaDto.getMortalidade(), result.getMortalidade());
    }

    @Test
    void testAtualizaDemografia() {
        Long idDemografia = 1L;
        Demografia demografia = new Demografia();
        demografia.setNatalidade(1000.0f);
        demografia.setMortalidade(500.0f);

        when(demografiaRepository.findById(idDemografia)).thenReturn(java.util.Optional.of(demografia));
        when(demografiaRepository.save(any())).thenReturn(demografia);

        Demografia result = demografiaService.atualizaDemografia(idDemografia, demografia);

        assertEquals(demografia.getNatalidade(), result.getNatalidade());
        assertEquals(demografia.getMortalidade(), result.getMortalidade());
    }

    @Test
    void testListaTodos() {
        Pageable pageable = mock(Pageable.class);
        List<Demografia> demografias = Collections.singletonList(demografia);
        PageImpl<Demografia> page = new PageImpl<>(demografias);

        when(demografiaRepository.findAll(pageable)).thenReturn(page);

        List<DemografiaDto> result = demografiaService.listaTodos(pageable).getContent();

        assertEquals(1, result.size());
        assertEquals(demografia.getId(), result.get(0).getId());
        assertEquals(demografia.getNatalidade(), result.get(0).getNatalidade());
        assertEquals(demografia.getMortalidade(), result.get(0).getMortalidade());
    }

    @Test
    void testBuscaPorId() {
        Long id = 1L;

        when(demografiaRepository.findById(id)).thenReturn(java.util.Optional.of(demografia));

        DemografiaDto result = demografiaService.buscaPorId(id);

        assertEquals(demografia.getId(), result.getId());
        assertEquals(demografia.getNatalidade(), result.getNatalidade());
        assertEquals(demografia.getMortalidade(), result.getMortalidade());
    }

    @Test
    void testBuscaPorNatalidade() {
        Float natalidade = 1000.0f;

        when(demografiaRepository.findByNatalidade(natalidade)).thenReturn(Collections.singletonList(demografia));

        List<Demografia> result = demografiaService.buscaPorNatalidade(natalidade);

        assertEquals(1, result.size());
    }
        @Test
        void testDeletaDemografiaExists() {
            Long idDemografia = 1L;

            when(demografiaRepository.existsById(idDemografia)).thenReturn(true);

            demografiaService.deletaDemografia(idDemografia);

            // Verifica se o método deleteById foi chamado com o id correto
            verify(demografiaRepository).deleteById(idDemografia);
        }

        @Test
        void testDeletaDemografiaNotExists() {
            Long idEconomia = 1L;

            when(demografiaRepository.existsById(idEconomia)).thenReturn(false);

            // Garante que uma exceção EconomiaException seja lançada
            assertThrows(DemografiaException.class, () -> demografiaService.deletaDemografia(idEconomia));
        }

    }
