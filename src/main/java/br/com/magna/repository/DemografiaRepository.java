package br.com.magna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magna.model.Demografia;

@Repository
public interface DemografiaRepository extends JpaRepository<Demografia, Long>{

	List<Demografia> findByNatalidade(Float natalidade);

}
