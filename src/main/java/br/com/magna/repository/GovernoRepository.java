package br.com.magna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magna.model.Governo;

@Repository
public interface GovernoRepository extends JpaRepository<Governo, Long>{

}
