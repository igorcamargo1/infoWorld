package br.com.magna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magna.model.Governo;

@Repository
public interface GovernoRepository extends JpaRepository<Governo, Long>{

	List<Governo> findByLiderPolitico(String liderPolitico);

}
