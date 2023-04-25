package com.book.shop.repositorios;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.shop.entidades.Editoriale;
import com.book.shop.entidades.Libro;



@Repository

public interface EditorialLibroRepositorio extends JpaRepository<Editoriale, Serializable>{
	@Bean
	public abstract List<Editoriale> findAll();
	public abstract Editoriale findById(int id);

	
	@Transactional
	public abstract void deleteById(int id);
	
	@Transactional
	public abstract Libro save(Libro gl);
	
	

}
