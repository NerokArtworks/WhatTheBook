package com.book.shop.repositorios;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.shop.entidades.Libro;
import com.book.shop.entidades.Usuario;
import com.book.shop.entidades.Valoracione;






@Repository
public interface ValoracionRepositorio extends JpaRepository<Valoracione, Serializable>{
	
	@Bean
	public abstract List<Valoracione> findAll();
	public abstract Valoracione findById(int id);
	public abstract List<Valoracione> findByUsuario(Usuario usuario);
	public abstract Valoracione findByUsuarioAndLibro(Usuario usuario, Libro libro);
	
	@Transactional
	public abstract void deleteById(int id);
	

	@Transactional
	public abstract Valoracione save(Valoracione l);
	
	

}
