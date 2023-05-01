package com.book.shop.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.book.shop.entidades.Genero;
import com.book.shop.repositorios.GeneroLibroRepositorio;
import com.book.shop.repositorios.LibroRepositorio;
import com.book.shop.repositorios.UsuarioRepositorio;

@RestController
@RequestMapping("/generos")
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
public class Generocontrolador {

	@Autowired
	UsuarioRepositorio usuRep;
	@Autowired
	LibroRepositorio libroRep;
	@Autowired
	GeneroLibroRepositorio generoRep;
	@Autowired
	GeneroLibroRepositorio libroGenero;

	@GetMapping("/obtener")
	public List<DTO> getGeneros() {
		List<DTO> listaGenerosDto = new ArrayList<DTO>();
		List<Genero> generos = generoRep.findAll();
		for (Genero g : generos) {
			DTO dtoGeneros = new DTO();
			dtoGeneros.put("id", g.getId());		
			dtoGeneros.put("titulo", g.getNombre());
			listaGenerosDto.add(dtoGeneros);
		}
		return listaGenerosDto;
	}
}
