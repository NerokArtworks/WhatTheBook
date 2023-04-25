package com.book.shop.repositorios;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.shop.entidades.Descuento;






@Repository
public interface DescuentoRepositorio extends JpaRepository<Descuento, Serializable>{
	
	@Bean
	public abstract List<Descuento> findAll();
	public abstract Descuento findById(int id);
	public abstract List<Descuento> findByTitulo(String titulo);
	public abstract Descuento findByPorcentaje(int porcentaje);
	
	@Transactional
	public abstract void deleteById(int id);
	

	@Transactional
	public abstract Descuento save(Descuento l);
	
	

}
