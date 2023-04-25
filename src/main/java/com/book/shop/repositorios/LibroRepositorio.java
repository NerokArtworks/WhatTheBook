package com.book.shop.repositorios;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.shop.entidades.Libro;






@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Serializable>{
	
	@Bean
	public abstract List<Libro> findAll();
	public abstract Libro findById(int id);
	public abstract List<Libro> findByTitulo(String titulo);
	public abstract List<Libro> findByAutor(String autor);
	public abstract Libro findByTituloAndAutor(String titulo, String autor);
	
	@Transactional
	public abstract void deleteById(int id);
	

	@Transactional
	public abstract Libro save(Libro l);
	
	

}
