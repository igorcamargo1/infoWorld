package br.com.magna.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magna.dto.PaisDto;
import br.com.magna.exception.PaisNotFoundException;
import br.com.magna.model.Continente;
import br.com.magna.model.Demografia;
import br.com.magna.model.Economia;
import br.com.magna.model.Geografia;
import br.com.magna.model.Governo;
import br.com.magna.model.Idioma;
import br.com.magna.model.Pais;
import br.com.magna.repository.ContinenteRepository;
import br.com.magna.repository.DemografiaRepository;
import br.com.magna.repository.EconomiaRepository;
import br.com.magna.repository.GeografiaRepository;
import br.com.magna.repository.GovernoRepository;
import br.com.magna.repository.IdiomaRepository;
import br.com.magna.repository.PaisRepository;

@Service
public class PaisService {

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private ContinenteRepository continenteRepository;
	@Autowired
	private EconomiaRepository economiaRepository;

	@Autowired
	private GeografiaRepository geografiaRepository;

	@Autowired
	private DemografiaRepository demografiaRepository;

	@Autowired
	private GovernoRepository governoRepository;

	@Autowired
	private IdiomaRepository idiomaRepository;

	public Pais salvaPais(Pais pais) {
		return paisRepository.save(pais);
	}

	public PaisDto convertToDto(Pais pais) {
		PaisDto dto = new PaisDto();
		dto.setId(pais.getId());
		dto.setNome(pais.getNome());
		dto.setCapital(pais.getCapital());
		dto.setCodigoPais(pais.getCodigoPais());
		dto.setDescricao(pais.getDescricao());
		dto.setIdContinente(pais.getContinente().getId());
		dto.setIdEconomia(pais.getEconomia().getId());
		dto.setIdGeografia(pais.getGeografia().getId());
		dto.setIdDemografia(pais.getDemografia().getId());
		dto.setIdGoverno(pais.getGoverno().getId());
		dto.setIdiomas(pais.getIdiomas().stream().map(Idioma::getId).collect(Collectors.toSet()));
		return dto;
	}

	public PaisDto criaPais(PaisDto paisDto) {
		Pais pais = new Pais();

		pais.setNome(paisDto.getNome());
		pais.setCapital(paisDto.getCapital());
		pais.setCodigoPais(paisDto.getCodigoPais());
		pais.setDescricao(paisDto.getDescricao());

		Continente continente = continenteRepository.findById(paisDto.getIdContinente()).orElseThrow(
				() -> new RuntimeException("Continente não encontrado com o ID: " + paisDto.getIdContinente()));

		pais.setContinente(continente);
		Economia economia = economiaRepository.findById(paisDto.getIdEconomia()).orElseThrow(
				() -> new RuntimeException("Economia não encontrada com o ID: " + paisDto.getIdEconomia()));
		pais.setEconomia(economia);

		Geografia geografia = geografiaRepository.findById(paisDto.getIdGeografia()).orElseThrow(
				() -> new RuntimeException("Geografia não encontrada com o ID: " + paisDto.getIdGeografia()));
		pais.setGeografia(geografia);

		Demografia demografia = demografiaRepository.findById(paisDto.getIdDemografia()).orElseThrow(
				() -> new RuntimeException("Demografia não encontrada com o ID: " + paisDto.getIdDemografia()));
		pais.setDemografia(demografia);

		Governo governo = governoRepository.findById(paisDto.getIdGoverno())
				.orElseThrow(() -> new RuntimeException("Governo não encontrado com o ID: " + paisDto.getIdGoverno()));
		pais.setGoverno(governo);

		Set<Idioma> idiomas = paisDto.getIdiomas().stream()
				.map(idiomaId -> idiomaRepository.findById(idiomaId)
						.orElseThrow(() -> new RuntimeException("Idioma não encontrado com o ID: " + idiomaId)))
				.collect(Collectors.toSet());
		pais.setIdiomas(idiomas);

		pais = salvaPais(pais);

		return convertToDto(pais);
	}

	public Optional<PaisDto> buscaPaisPorId(Long id) {
		Optional<Pais> paisOptional = paisRepository.findById(id);
		return paisOptional.map(this::convertToDto);
	}

	public Page<PaisDto> listaTodos(Pageable page) {
		return paisRepository.findAll(page).map(this::convertToDto);
	}

	public PaisDto atualizaPais(Long id, PaisDto paisDto) {
		Pais paisExistente = paisRepository.findById(id)
				.orElseThrow(() -> new PaisNotFoundException("País não encontrado com o ID: " + id));

		paisExistente.setNome(paisDto.getNome());
		paisExistente.setCapital(paisDto.getCapital());
		paisExistente.setCodigoPais(paisDto.getCodigoPais());
		paisExistente.setDescricao(paisDto.getDescricao());

		Continente continente = continenteRepository.findById(paisDto.getIdContinente()).orElseThrow(
				() -> new RuntimeException("Continente não encontrado com o ID: " + paisDto.getIdContinente()));
		paisExistente.setContinente(continente);

		Economia economia = economiaRepository.findById(paisDto.getIdEconomia()).orElseThrow(
				() -> new RuntimeException("Economia não encontrada com o ID: " + paisDto.getIdEconomia()));
		paisExistente.setEconomia(economia);

		Geografia geografia = geografiaRepository.findById(paisDto.getIdGeografia()).orElseThrow(
				() -> new RuntimeException("Geografia não encontrada com o ID: " + paisDto.getIdGeografia()));
		paisExistente.setGeografia(geografia);

		Demografia demografia = demografiaRepository.findById(paisDto.getIdDemografia()).orElseThrow(
				() -> new RuntimeException("Demografia não encontrada com o ID: " + paisDto.getIdDemografia()));
		paisExistente.setDemografia(demografia);

		Governo governo = governoRepository.findById(paisDto.getIdGoverno())
				.orElseThrow(() -> new RuntimeException("Governo não encontrado com o ID: " + paisDto.getIdGoverno()));
		paisExistente.setGoverno(governo);

		Set<Idioma> idiomas = paisDto.getIdiomas().stream()
				.map(idiomaId -> idiomaRepository.findById(idiomaId)
						.orElseThrow(() -> new RuntimeException("Idioma não encontrado com o ID: " + idiomaId)))
				.collect(Collectors.toSet());
		paisExistente.setIdiomas(idiomas);

		Pais paisAtualizado = salvaPais(paisExistente);

		return convertToDto(paisAtualizado);
	}

	public void deletaPais(Long id) {
		if (!paisRepository.existsById(id)) {
			throw new PaisNotFoundException("País não encontrado com o ID: " + id);
		}

		paisRepository.deleteById(id);
	}

}
