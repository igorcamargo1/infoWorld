package br.com.magna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magna.model.Continente;

@Repository
public interface ContinenteRepositroy extends JpaRepository<Continente,Long>{

}
