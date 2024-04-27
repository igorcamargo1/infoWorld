package br.com.magna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magna.model.Geografia;

@Repository
public interface GeografiaRepository extends JpaRepository<Geografia, Long> {

	List<Geografia> findByClima(String clima);

}
