package br.com.magna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magna.model.Continente;

@Repository
public interface ContinenteRepository extends JpaRepository<Continente,Long>{

	List<Continente> findByNome(String moeda);

}
