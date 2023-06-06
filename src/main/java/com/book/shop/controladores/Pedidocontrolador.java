package com.book.shop.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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

import com.book.shop.entidades.Pedido;
import com.book.shop.controladores.Pedidocontrolador.DatosAltaPedido;
import com.book.shop.entidades.Descuento;
import com.book.shop.entidades.DetallesPedido;
import com.book.shop.entidades.Usuario;
import com.book.shop.repositorios.DetallesRepositorio;
import com.book.shop.repositorios.PedidoRepositorio;
import com.book.shop.repositorios.UsuarioRepositorio;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
public class Pedidocontrolador {

	@Autowired
	UsuarioRepositorio usuRep;
	@Autowired
	PedidoRepositorio pedidoRep;
	@Autowired
	DetallesRepositorio detallesRep;

	@GetMapping("/obtener")
	public List<DTO> getPedidos() {
		List<DTO> listaPedidosDto = new ArrayList<DTO>();
		List<Pedido> pedidos = pedidoRep.findAll();
		for (Pedido p : pedidos) {
			DTO dtoPedidos = new DTO();
			dtoPedidos.put("id", p.getId());
			dtoPedidos.put("fecha", p.getFecha().toString());
			dtoPedidos.put("importe", p.getImporte());
			
			// Crear DTO específico para los detalles de pedido
			List<DetallesPedidoDTO> detallesPedidosDTO = new ArrayList<>();
			for (DetallesPedido detalle : p.getDetallesPedidos()) {
			    DetallesPedidoDTO detalleDTO = new DetallesPedidoDTO();
			    detalleDTO.setId(detalle.getId());
			    detalleDTO.setCantidad(detalle.getCantidad());
			    detalleDTO.setImporte(detalle.getImporte());
			    detalleDTO.setLibro(detalle.getLibro().getId());
			    detalleDTO.setPedido(detalle.getPedido().getId());
			    // Asignar otras propiedades necesarias
			    detallesPedidosDTO.add(detalleDTO);
			}

			dtoPedidos.put("detallesPedidos", detallesPedidosDTO);
			
			if (p.getDescuento() != null) {
				dtoPedidos.put("descuento", p.getDescuento().getId());
			} else {
				dtoPedidos.put("descuento", null);
			}
			dtoPedidos.put("usuario", p.getUsuario());
			listaPedidosDto.add(dtoPedidos);
		}
		return listaPedidosDto;
	}
	
	@PostMapping(path="/getByUser", consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<DTO> getByUser(@RequestBody DTO usuario) {
		Integer userId = (Integer) usuario.get("id");
	    Optional<Usuario> userOptional = usuRep.findById(userId);

	    List<DTO> listaPedidosDto = new ArrayList<>();

	    if (userId == null || !userOptional.isPresent()) {
	        DTO dtoPedidos = new DTO();
	        dtoPedidos.put("result", "fail");
	        listaPedidosDto.add(dtoPedidos);
	    } else {
	        List<Pedido> pedidos = pedidoRep.findByUsuario(userOptional);
	        for (Pedido p : pedidos) {
	            DTO dtoPedidos = new DTO();
	            dtoPedidos.put("id", p.getId());
	            dtoPedidos.put("fecha", p.getFecha().toString());
	            dtoPedidos.put("importe", p.getImporte());

	            List<DetallesPedidoDTO> detallesPedidosDTO = new ArrayList<>();
	            for (DetallesPedido detalle : p.getDetallesPedidos()) {
	                DetallesPedidoDTO detalleDTO = new DetallesPedidoDTO();
	                detalleDTO.setId(detalle.getId());
	                detalleDTO.setCantidad(detalle.getCantidad());
	                detalleDTO.setImporte(detalle.getImporte());
	                detalleDTO.setLibro(detalle.getLibro().getId());
	                detalleDTO.setPedido(detalle.getPedido().getId());
	                // Asignar otras propiedades necesarias
	                detallesPedidosDTO.add(detalleDTO);
	            }

	            dtoPedidos.put("detallesPedidos", detallesPedidosDTO);

	            if (p.getDescuento() != null) {
	                dtoPedidos.put("descuento", p.getDescuento().getId());
	            } else {
	                dtoPedidos.put("descuento", null);
	            }

	            dtoPedidos.put("usuario", p.getUsuario().getId());
	            listaPedidosDto.add(dtoPedidos);
	        }
	        if (listaPedidosDto.isEmpty()) {
	            DTO dtoPedidos = new DTO();
	            dtoPedidos.put("result","not found");
	            listaPedidosDto.add(dtoPedidos);
	        }
	    }

	    return listaPedidosDto;
	}

	
	
	
	//Obtener un registron con findById

		@PostMapping(path="/obtener1",consumes=MediaType.APPLICATION_JSON_VALUE)
		public DTO getPedidos(@RequestBody DTO solouno) {
			//Creo una lista de hashmap para devolver un json
			
			//leo de repositorio todos los registros
			System.out.println("\n**ID DEL LIBRO PETICIÓN OBTENER1: " + solouno.get("id").toString());
			Pedido p = pedidoRep.findById(Integer.parseInt(solouno.get("id").toString()));
			//Los voy cargar en el DTO
					
				DTO dtoPedidos=new DTO();
				if(p!=null) {
					dtoPedidos.put("id", p.getId());
					dtoPedidos.put("fecha", p.getFecha().toString());
					dtoPedidos.put("importe", p.getImporte());
					dtoPedidos.put("detallesPedidos", p.getDetallesPedidos());
					dtoPedidos.put("descuento", p.getDescuento().getId());
					dtoPedidos.put("usuario", p.getUsuario().getId());
				} else dtoPedidos.put("result","fail");
		
			return dtoPedidos;}	
	//fin Obtener un registron con findById
	
	// PUT
		@PutMapping(path="/modificar",consumes=MediaType.APPLICATION_JSON_VALUE)
		public DTO modificarPedido(@RequestBody DTO pedido) {
			Pedido pedidoPasado = pedidoRep.findById(Integer.parseInt(pedido.get("id").toString()));
			Pedido pedidoActualizado = new Pedido(
					pedidoPasado.getId(),
					pedidoPasado.getFecha(),
					pedidoPasado.getImporte(),
					pedidoPasado.getDetallesPedidos(), 
					pedidoPasado.getDescuento(),
					pedidoPasado.getUsuario());
			Pedido update = pedidoRep.save(pedidoActualizado);
			return pedido;
		}
	
	//Borra un registro de la base de datos

		@PostMapping(path="/borrar",consumes=MediaType.APPLICATION_JSON_VALUE)
		public DTO borrarPedido(@RequestBody DTO solouno) {
			//Creo una lista de hashmap para devolver un json
			
			//leo de repositorio todos los registros
			Pedido p = pedidoRep.findById(Integer.parseInt(solouno.get("id").toString()));
			//Los voy cargar en el DTO
			
				DTO dtoPedidos=new DTO();

				if(p!=null) {pedidoRep.delete(p);
				dtoPedidos.put("delete","ok");
				}
				else dtoPedidos.put("delete","fail");
		
			return dtoPedidos;}
		
	// Fin Borra un registro de la base de datos

		/*@PutMapping(path="/modificar1",consumes=MediaType.APPLICATION_JSON_VALUE)
		public void modificarPedido(@RequestBody DatosAltaPedido p, HttpServletRequest request) {
		    Optional<Pedido> optionalPedido = Optional.ofNullable(pedidoRep.findById(p.id));
		    Pedido pedido;
		    if (optionalPedido.isPresent()) {
		        pedido = optionalPedido.get();
		    } else {
		        pedido = new Pedido();
		        pedido.setId(p.id);
		    }
		    pedido.setTitulo(p.titulo);
		    pedido.setAutor(p.usuario);
		    pedido.setEditoriale(p.editorial);
		    pedido.setFechaPublicacion(p.fecha_publicacion);
		    pedido.setPedidoGenero(pedidoGenero.findById(p.pedidoGenero));
		    pedido.setFechaElim(p.fechaElim);
		    pedidoRep.save(pedido);
		}*/

		//Controlador para aÃ±adir un registro nuevo


		@PostMapping(path="/nuevo",consumes=MediaType.APPLICATION_JSON_VALUE)
		public DTO nuevoPedido(@RequestBody
				DatosAltaPedido p,HttpServletRequest request) {
			DTO dtoPedido=new DTO();
			
			try {
				Pedido pedido = new Pedido();
				pedido.setFecha(p.fecha);
				pedido.setImporte(p.importe);
				//pedido.setDetallesPedidos(p.detallesPedidos);
				pedido.setDescuento(p.descuento);
				pedido.setUsuario(p.usuario);
				pedidoRep.save(pedido);
				
				List<DetallesPedido> detallesPedidos = p.detallesPedidos;
				pedidoRep.save(pedido); // Guardar el pedido primero para generar su ID

				for (DetallesPedido detallePedido : detallesPedidos) {
				    detallePedido.setPedido(pedido);
				}

				pedido.setDetallesPedidos(detallesPedidos);
				pedidoRep.save(pedido); // Guardar el pedido con los detalles del pedido actualizados
				dtoPedido.put("result", "pedidoOk");
			} catch (Exception e) {
				dtoPedido.put("result", "fail");
			}
			return dtoPedido;
		}
		
		static class DatosAltaPedido{
			int id;
			Date fecha;
			float importe;
			List<DetallesPedido> detallesPedidos;
			Descuento descuento;
			Usuario usuario;
			
			public DatosAltaPedido(int id, Date fecha,float importe,
					List<DetallesPedido> detallesPedidos,Descuento descuento,
					Usuario usuario) {
				super();
				this.id = id;
				this.fecha = fecha;
				this.importe = importe;
				this.detallesPedidos = detallesPedidos;
				this.descuento = descuento;
				this.usuario = usuario;
			}
		}

	//fin controlador aÃ±adir registro
	
	@DeleteMapping(path="/borrar",consumes=MediaType.APPLICATION_JSON_VALUE)
	public DTO borrarUsuario(@RequestBody DTO solouno) {
		//Creo una lista de hashmap para devolver un json
		
		//leo de repositorio todos los registros
		Pedido p = pedidoRep.findById(Integer.parseInt((String) solouno.get("id")));
		System.out.println(solouno.get("id"));
		System.out.println(p.getId());
		//Los voy cargar en el DTO
		DTO dtoPedido=new DTO();

		if(p!=null) {
			pedidoRep.delete(p);
			dtoPedido.put("delete","ok");
		}
		else dtoPedido.put("delete","fail");
	
		return dtoPedido;}
}
