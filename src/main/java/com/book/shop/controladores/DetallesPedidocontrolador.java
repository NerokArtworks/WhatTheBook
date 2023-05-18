package com.book.shop.controladores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.book.shop.entidades.DetallesPedido;
import com.book.shop.entidades.Descuento;
import com.book.shop.entidades.Usuario;
import com.book.shop.jwtSecurity.AutentificatorJWT;
import com.book.shop.entidades.Genero;
import com.book.shop.entidades.Pedido;
import com.book.shop.entidades.Editoriale;
import com.book.shop.repositorios.DetallesRepositorio;
import com.book.shop.repositorios.PedidoRepositorio;
import com.book.shop.repositorios.UsuarioRepositorio;

@RestController
@RequestMapping("/detallesPedido")
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
public class DetallesPedidocontrolador {

	@Autowired
	UsuarioRepositorio usuRep;
	@Autowired
	PedidoRepositorio pedidoRep;
	@Autowired
	DetallesRepositorio detallesRep;

	@GetMapping("/obtener")
	public List<DTO> getDetallesPedidos() {
		List<DTO> listaDetallesPedidosDto = new ArrayList<DTO>();
		List<DetallesPedido> pedidos = detallesRep.findAll();
		for (DetallesPedido p : pedidos) {
			DTO dtoDetallesPedidos = new DTO();
			dtoDetallesPedidos.put("id", p.getId());
			dtoDetallesPedidos.put("cantidad", p.getCantidad());
			dtoDetallesPedidos.put("importe", p.getImporte());
			dtoDetallesPedidos.put("libro", p.getLibro());
			dtoDetallesPedidos.put("pedido", p.getPedido());
			listaDetallesPedidosDto.add(dtoDetallesPedidos);
		}
		return listaDetallesPedidosDto;
	}
	
	@PostMapping(path="/getByOrder",consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<DTO> getByUser(@RequestBody DTO id_pedido) {
		
		Pedido pedido = pedidoRep.findById((int) id_pedido.get("id"));
		
		List<DetallesPedido> detallesPedidos = detallesRep.findByPedido(pedido);
		//Los voy cargar en el DTO
		List<DTO> listaDetallesPedidosDto = new ArrayList<DTO>();
		if(pedido!=null && detallesPedidos != null) {
			for (DetallesPedido p : detallesPedidos) {
				DTO dtoDetallesPedidos = new DTO();
				dtoDetallesPedidos.put("id", p.getId());
				dtoDetallesPedidos.put("cantidad", p.getCantidad());
				dtoDetallesPedidos.put("importe", p.getImporte());
				dtoDetallesPedidos.put("libro", p.getLibro().getId());
				dtoDetallesPedidos.put("pedido", p.getPedido().getId());
				listaDetallesPedidosDto.add(dtoDetallesPedidos);
			}
			if (listaDetallesPedidosDto.isEmpty()) {
				DTO dtoDetallesPedidos = new DTO();
				dtoDetallesPedidos.put("result","not found");
				listaDetallesPedidosDto.add(dtoDetallesPedidos);
			}
		} else {
			DTO dtoDetallesPedidos = new DTO();
			dtoDetallesPedidos.put("result","fail");
			listaDetallesPedidosDto.add(dtoDetallesPedidos);
		}
		
		return listaDetallesPedidosDto;
	}

}
