package com.book.shop.repositorios;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.shop.entidades.Pedido;
import com.book.shop.entidades.Usuario;






@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Serializable>{
	
	@Bean
	public abstract List<Pedido> findAll();
	public abstract Pedido findById(int id);
	public abstract List<Pedido> findByUsuario(Usuario usuario);
	public abstract Pedido findByUsuarioAndFecha(Usuario usuario, Date fecha);
	
	@Transactional
	public abstract void deleteById(int id);
	

	@Transactional
	public abstract Pedido save(Pedido p);
	public abstract List<Pedido> findByUsuario(Optional<Usuario> userOptional);
	
	

}
