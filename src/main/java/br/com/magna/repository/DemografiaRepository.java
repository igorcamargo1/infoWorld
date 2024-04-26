package br.com.magna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magna.model.Demografia;

@Repository
public interface DemografiaRepository extends JpaRepository<Demografia, Long>{

}
