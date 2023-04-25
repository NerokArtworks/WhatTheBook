package com.book.shop.repositorios;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.shop.entidades.Genero;
import com.book.shop.entidades.Libro;



@Repository

public interface GeneroLibroRepositorio extends JpaRepository<Genero, Serializable>{
	@Bean
	public abstract List<Genero> findAll();
	public abstract Genero findById(int id);

	
	@Transactional
	public abstract void deleteById(int id);
	
	@Transactional
	public abstract Libro save(Libro gl);
	
	

}
