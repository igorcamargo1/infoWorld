package br.com.magna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magna.model.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long>{

}
