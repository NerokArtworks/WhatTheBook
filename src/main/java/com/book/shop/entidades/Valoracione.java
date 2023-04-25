package com.book.shop.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the valoraciones database table.
 * 
 */
@Entity
@Table(name="valoraciones")
@NamedQuery(name="Valoracione.findAll", query="SELECT v FROM Valoracione v")
public class Valoracione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String comentario;

	private int nota;

	//bi-directional many-to-one association to Libro
	@ManyToOne
	@JoinColumn(name="id_libro")
	private Libro libro;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Valoracione() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getNota() {
		return this.nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Libro getLibro() {
		return this.libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}