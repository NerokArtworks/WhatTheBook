package com.book.shop.repositorios;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.shop.entidades.DetallesPedido;
import com.book.shop.entidades.Libro;
import com.book.shop.entidades.Pedido;






@Repository
public interface DetallesRepositorio extends JpaRepository<DetallesPedido, Serializable>{
	
	@Bean
	public abstract List<DetallesPedido> findAll();
	public abstract DetallesPedido findById(int id);
	public abstract List<DetallesPedido> findByPedido(Pedido pedido);
	public abstract DetallesPedido findByPedidoAndLibro(Pedido pedido, Libro libro);
	
	@Transactional
	public abstract void deleteById(int id);
	

	@Transactional
	public abstract DetallesPedido save(DetallesPedido p);
	
	

}
