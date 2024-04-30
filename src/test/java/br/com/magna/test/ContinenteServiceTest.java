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

import br.com.magna.dto.ContinenteDto;
import br.com.magna.exception.ContinenteException;
import br.com.magna.model.Continente;
import br.com.magna.repository.ContinenteRepository;
import br.com.magna.service.ContinenteService;

@ExtendWith(MockitoExtension.class)
class ContinenteServiceTest {

    @Mock
    private ContinenteRepository continenteRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ContinenteService continenteService;

    private Continente continente;

    @BeforeEach
    void setUp() {
        continente = new Continente();
        continente.setId(1L);
        continente.setNome("Europa");
    }

    @Test
    void testCriaContinente() {
        ContinenteDto continenteDto = new ContinenteDto();
        continenteDto.setNome("Europa");
        when(continenteRepository.save(any())).thenReturn(continente);

        ContinenteDto result = continenteService.criaContinente(continenteDto);

        assertEquals(continenteDto.getNome(), result.getNome());
    }

    @Test
    void testAtualizaEconomia() {
        Long idContinente = 1L;
        Continente continente = new Continente();
        continente.setNome("Europa");

        when(continenteRepository.findById(idContinente)).thenReturn(java.util.Optional.of(continente));
        when(continenteRepository.save(any())).thenReturn(continente);

        Continente result = continenteService.atualizaContinente(idContinente, continente);

        assertEquals(continente.getNome(), result.getNome());
    }

    @Test
    void testListaTodos() {
        Pageable pageable = mock(Pageable.class);
        List<Continente> continentes = Collections.singletonList(continente);
        PageImpl<Continente> page = new PageImpl<>(continentes);

        when(continenteRepository.findAll(pageable)).thenReturn(page);

        List<ContinenteDto> result = continenteService.listaTodos(pageable).getContent();

        assertEquals(1, result.size());
        assertEquals(continente.getId(), result.get(0).getId());
        assertEquals(continente.getNome(), result.get(0).getNome());
    }

    @Test
    void testBuscaPorId() {
        Long id = 1L;

        when(continenteRepository.findById(id)).thenReturn(java.util.Optional.of(continente));

        ContinenteDto result = continenteService.buscaPorId(id);

        assertEquals(continente.getId(), result.getId());
        assertEquals(continente.getNome(), result.getNome());
    }

    @Test
    void testBuscaPorMoeda() {
        String nome = "Europa";

        when(continenteRepository.findByNome(nome)).thenReturn(Collections.singletonList(continente));

        List<Continente> result = continenteService.buscaPorNome(nome);

        assertEquals(1, result.size());
    }
        @Test
        void testDeletaContinenteExists() {
            Long idEconomia = 1L;

            when(continenteRepository.existsById(idEconomia)).thenReturn(true);

            continenteService.deletaContinente(idEconomia);

            // Verifica se o método deleteById foi chamado com o id correto
            verify(continenteRepository).deleteById(idEconomia);
        }

        @Test
        void testDeletaContinenteNotExists() {
            Long idEconomia = 1L;

            when(continenteRepository.existsById(idEconomia)).thenReturn(false);

            // Garante que uma exceção EconomiaException seja lançada
            assertThrows(ContinenteException.class, () -> continenteService.deletaContinente(idEconomia));
        }

    }
