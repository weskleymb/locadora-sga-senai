package br.senai.rn.locadora.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.senai.rn.locadora.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	@Override
	@Query("UPDATE Categoria c SET c.ativo=false WHERE c=:categoria")
	void delete(@Param("categoria") Categoria categoria);
	
	@Override
	@Transactional
	@Modifying
	@Query("UPDATE Categoria c SET c.ativo=false WHERE c.id=:id")
	void deleteById(@Param("id") Long id);
	
	@Override
	@Query("SELECT c FROM Categoria c WHERE c.ativo=true")
	List<Categoria> findAll();
	
	@Override
	@Query("SELECT c FROM Categoria c WHERE c.ativo=true AND c.id=:id")
	Optional<Categoria> findById(@Param("id") Long id);
	
}
