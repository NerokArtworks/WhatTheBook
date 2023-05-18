package com.book.shop.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.book.shop.entidades.Editoriale;
import com.book.shop.repositorios.EditorialLibroRepositorio;
import com.book.shop.repositorios.LibroRepositorio;
import com.book.shop.repositorios.UsuarioRepositorio;

@RestController
@RequestMapping("/editoriales")
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
public class Editorialcontrolador {

	@Autowired
	UsuarioRepositorio usuRep;
	@Autowired
	LibroRepositorio libroRep;
	@Autowired
	EditorialLibroRepositorio editorialRep;

	@GetMapping("/obtener")
	public List<DTO> getEditoriales() {
		List<DTO> listaEditorialesDto = new ArrayList<DTO>();
		List<Editoriale> editoriales = editorialRep.findAll();
		for (Editoriale e : editoriales) {
			DTO dtoGeneros = new DTO();
			dtoGeneros.put("id", e.getId());		
			dtoGeneros.put("titulo", e.getNombre());
			listaEditorialesDto.add(dtoGeneros);
		}
		return listaEditorialesDto;
	}
}
