package br.com.magna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magna.model.Economia;

@Repository
public interface EconomiaRepository extends JpaRepository<Economia, Long>{

	List<Economia> findByMoeda(String moeda);
}
