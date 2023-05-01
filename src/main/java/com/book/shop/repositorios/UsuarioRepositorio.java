package com.book.shop.repositorios;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.shop.entidades.Usuario;







@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Serializable>{
	
	@Bean
	public abstract List<Usuario> findAll();
	public abstract Usuario findById(int id);
	public abstract List<Usuario> findByNombre(String username);
	public abstract Usuario findByUsernameAndPassword(String username, String password);
	public abstract Usuario findByEmailAndPassword(String email, String password);
	
	@Transactional
	public abstract void deleteById(int id);
	

	@Transactional
	public abstract Usuario save(Usuario u);
	
	

}
