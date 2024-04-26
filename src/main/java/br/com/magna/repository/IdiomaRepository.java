package br.com.magna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magna.model.Idioma;

@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Long>{

}
