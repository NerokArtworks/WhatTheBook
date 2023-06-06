package com.book.shop.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.book.shop.entidades.Descuento;
import com.book.shop.repositorios.DescuentoRepositorio;
import com.book.shop.repositorios.LibroRepositorio;
import com.book.shop.repositorios.UsuarioRepositorio;

@RestController
@RequestMapping("/descuentos")
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
public class Descuentocontrolador {

	@Autowired
	UsuarioRepositorio usuRep;
	@Autowired
	LibroRepositorio libroRep;
	@Autowired
	DescuentoRepositorio descuentoRep;

	@GetMapping("/obtener")
	public List<DTO> getDescuentos() {
		List<DTO> listaDescuentosDto = new ArrayList<DTO>();
		List<Descuento> descuentos = descuentoRep.findAll();
		for (Descuento d : descuentos) {
			DTO dtoDescuentos = new DTO();
			dtoDescuentos.put("id", d.getId());		
			dtoDescuentos.put("titulo", d.getTitulo());
			dtoDescuentos.put("importe_minimo", d.getImporteMinimo());
			dtoDescuentos.put("porcentaje", d.getPorcentaje());
			listaDescuentosDto.add(dtoDescuentos);
		}
		return listaDescuentosDto;
	}
}
