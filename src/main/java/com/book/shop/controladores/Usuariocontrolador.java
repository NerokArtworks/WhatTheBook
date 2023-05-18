package com.book.shop.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.book.shop.entidades.Usuario;
import com.book.shop.jwtSecurity.AutentificatorJWT;
import com.book.shop.repositorios.UsuarioRepositorio;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
public class Usuariocontrolador {

	@Autowired
	UsuarioRepositorio usuRep;

	@GetMapping("/obtener")
	public List<DTO> getUsuarios() {
		// Creo una lista de hashmap para devolver un json
		List<DTO> listaUsuariosDto = new ArrayList<DTO>();
		// leo de repositorio todos los registros
		List<Usuario> usuarios = usuRep.findAll();
		// Los voy cargar en el DTO

		for (Usuario u : usuarios) {
			DTO dtoUsuario = new DTO();
			dtoUsuario.put("id",  u.getId());
			dtoUsuario.put("nombre", u.getNombre());
			dtoUsuario.put("apellidos", u.getApellidos());
			dtoUsuario.put("dni", u.getDni());
			dtoUsuario.put("email", u.getEmail());
			dtoUsuario.put("fecha_nac", u.getFechaNac().toString());
			dtoUsuario.put("fecha_creacion", u.getFecha_creacion().toString());
			dtoUsuario.put("pais", u.getPais());
			dtoUsuario.put("ciudad", u.getCiudad());
			dtoUsuario.put("direccion", u.getDireccion());
			dtoUsuario.put("password", u.getPassword());
			dtoUsuario.put("rol", u.getRol());
			dtoUsuario.put("socio", u.getSocio());
			dtoUsuario.put("telefono", u.getTelefono());
			dtoUsuario.put("username", u.getUsername());
			listaUsuariosDto.add(dtoUsuario);
		}

		return listaUsuariosDto;
	}
	
	//Obtener un registron con findById

		@PostMapping(path="/obtener1",consumes=MediaType.APPLICATION_JSON_VALUE)
		public DTO getUsuarios(@RequestBody DTO solouno) {
			//Creo una lista de hashmap para devolver un json
			
			//leo de repositorio todos los registros
			Usuario u = usuRep.findById(Integer.parseInt(solouno.get("id").toString()));
			//Los voy cargar en el DTO
			
						
				DTO dtoUsuario=new DTO();
				if(u!=null) {
					dtoUsuario.put("id",  u.getId());
					dtoUsuario.put("nombre", u.getNombre());
					dtoUsuario.put("apellidos", u.getApellidos());
					dtoUsuario.put("dni", u.getDni());
					dtoUsuario.put("email", u.getEmail());
					dtoUsuario.put("fecha_nac", u.getFechaNac().toString());
					dtoUsuario.put("fecha_creacion", u.getFecha_creacion().toString());
					dtoUsuario.put("pais", u.getPais());
					dtoUsuario.put("ciudad", u.getCiudad());
					dtoUsuario.put("direccion", u.getDireccion());
					dtoUsuario.put("password", u.getPassword());
					dtoUsuario.put("rol", u.getRol());
					dtoUsuario.put("socio", u.getSocio());
					dtoUsuario.put("telefono", u.getTelefono());
					dtoUsuario.put("username", u.getUsername());
				}else dtoUsuario.put("result","fail");
		
			return dtoUsuario;}	
	//fin Obtener un registron con findById
	
	//Borra un registro de la base de datos

		@PostMapping(path="/borrar",consumes=MediaType.APPLICATION_JSON_VALUE)
		public DTO borrarUsuario(@RequestBody DTO solouno) {
			//Creo una lista de hashmap para devolver un json
			
			//leo de repositorio todos los registros
			Usuario u = usuRep.findById(Integer.parseInt(solouno.get("id").toString()));
			//Los voy cargar en el DTO
			
						
				DTO dtoUsuario=new DTO();

				if(u!=null) {usuRep.delete(u);
				dtoUsuario.put("delete","ok");
				}
				else dtoUsuario.put("delete","fail");
		
			return dtoUsuario;}
		
	// Fin Borra un registro de la base de datos

		//Controlador para aÃ±adir un registro nuevo


		@PostMapping(path="/register",consumes=MediaType.APPLICATION_JSON_VALUE)
		public void autenticaUsuario(@RequestBody
		DatosAltaUsuario u,HttpServletRequest request) {
			usuRep.save(new Usuario(
			u.id, u.username, u.password, u.email, u.dni, u.nombre,
			u.apellidos, u.fecha_nac, u.fecha_creacion, u.pais, u.ciudad, u.direccion, u.telefono,
			u.socio, u.rol));
		  }	

		  	static class DatosAltaUsuario{
			int id;
			String username;
			String password;
			String email;
			String dni;
			String nombre;
			String apellidos;
			Date fecha_nac;
			Date fecha_creacion;
			String pais;
			String ciudad;
			String direccion;
			String telefono;
			byte socio;
			String rol;
			
		public DatosAltaUsuario(int id, String username, String password, String dni, String nombre, String apellidos, Date fecha_nac,
				Date fecha_creacion, String pais, String ciudad, String direccion, String email, String telefono, byte socio, String rol) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.dni = dni;
			this.nombre = nombre;
			this.apellidos = apellidos;
			this.fecha_nac = fecha_nac;
			this.fecha_creacion = fecha_creacion;
			this.pais = (pais != null)? pais : null;
			this.ciudad = (ciudad != null)? ciudad : null;
			this.direccion = (direccion != null)? direccion : null;
			this.email = email;
			this.telefono = (telefono != null)? telefono : null;
			this.socio = socio;
			this.rol = rol;
		}}

	//fin controlador aÃ±adir registro
		  	
	 // Controlador que autentica un usuario
	
		@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
		public DTO autentitcaUsuario(@RequestBody DatosAutenticacionUsuario datos, HttpServletRequest request,
				HttpServletResponse response) {
	
			DTO dto = new DTO();
			dto.put("result", "fail");
	
			Usuario usuAutenticado = usuRep.findByEmailAndPassword(datos.email, datos.password);
	
			// si existe el usuario y los datos son correctos, devolveremos un success y
			// todos los datos del usuario
			if (usuAutenticado != null) {
				dto.put("result", "success");
	
				// devolvemos jwt que usaremos de ahora en adelante en las cabeceras para
				// identificar al usuario
				dto.put("jwt", AutentificatorJWT.codificaJWT(usuAutenticado));
				dto.put("user_id", usuAutenticado.getId());
	
				// Prueba cookie. Quita el request y el response
				Cookie cook = new Cookie("jwt", AutentificatorJWT.codificaJWT(usuAutenticado));
				cook.setMaxAge(-1);
				response.addCookie(cook);
				//
			}
	
			return dto;
		}
		
		static class DatosAutenticacionUsuario {
			String email;
			String password;
	
			public DatosAutenticacionUsuario(String email, String password) {
				super();
				this.email = email;
				this.password = password;
			}
	
		}
		
		//fin autenticador
		
		//Controlador devuevle el usuario autenticado
	
		@PostMapping("/getUser")
		public DTO getAutenticado(@RequestBody DTO token, HttpServletRequest request) {
			DTO dtoUsuario = new DTO();
			
			dtoUsuario.put("result", "fail");
			int idUsuarioAutenticado = -1;
			
			if ((String) token.get("token") != null) {
				// dtoUsuario.put("id",
				idUsuarioAutenticado = AutentificatorJWT.getIdUsuarioDesdeJWT((String) token.get("token"));
			}
				
			// identificamos usuario por jwt de cabecera recibida
	
			// int idUsuarioAutenticado =
			// AutenticadorJWT.getIdUsuarioDesdeJwtIncrustadoEnRequest(request);
	
			Usuario u = usuRep.findById(idUsuarioAutenticado);
			// dtoUsuario.put("idfound", idUsuarioAutenticado);
			// si existe el usuario y los datos son correctos, devolveremos un success y
			// todos los datos del usuario
			if (u != null) {
				dtoUsuario.put("id",  u.getId());
				dtoUsuario.put("nombre", u.getNombre());
				dtoUsuario.put("apellidos", u.getApellidos());
				dtoUsuario.put("dni", u.getDni());
				dtoUsuario.put("email", u.getEmail());
				dtoUsuario.put("fecha_nac", u.getFechaNac().toString());
				dtoUsuario.put("fecha_creacion", u.getFecha_creacion().toString());
				dtoUsuario.put("pais", u.getPais());
				dtoUsuario.put("ciudad", u.getCiudad());
				dtoUsuario.put("direccion", u.getDireccion());
				dtoUsuario.put("password", u.getPassword());
				dtoUsuario.put("rol", u.getRol());
				dtoUsuario.put("socio", u.getSocio());
				dtoUsuario.put("telefono", u.getTelefono());
				dtoUsuario.put("username", u.getUsername());
			}
	
			return dtoUsuario;
		}
		
		//fin quiereres


}
