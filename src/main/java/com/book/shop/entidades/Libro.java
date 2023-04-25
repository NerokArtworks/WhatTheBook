package com.book.shop.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the libros database table.
 * 
 */
@Entity
@Table(name="libros")
@NamedQuery(name="Libro.findAll", query="SELECT l FROM Libro l")
public class Libro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String autor;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_publi")
	private Date fechaPubli;

	private String isbn;

	@Lob
	private String portada;

	private float precio;

	private float rating;

	private String saga;

	@Lob
	private String sinopsis;

	private int stock;

	private String tipo;

	private String titulo;

	//bi-directional many-to-one association to DetallesPedido
	@OneToMany(mappedBy="libro")
	private List<DetallesPedido> detallesPedidos;

	//bi-directional many-to-one association to Editoriale
	@ManyToOne
	@JoinColumn(name="id_editorial")
	private Editoriale editoriale;

	//bi-directional many-to-one association to Genero
	@ManyToOne
	@JoinColumn(name="id_genero")
	private Genero genero;

	//bi-directional many-to-one association to Valoracione
	@OneToMany(mappedBy="libro")
	private List<Valoracione> valoraciones;

	public Libro() {
	}

	public Libro(int id, String titulo, String saga, String autor, String isbn, Date fechaPubli, Genero genero,
			Editoriale editoriale, String descripcion, String sinopsis, float precio, String tipo, String portada,
			float rating, int stock) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.saga = saga;
		this.autor = autor;
		this.isbn = isbn;
		this.fechaPubli = fechaPubli;
		this.genero = genero;
		this.editoriale = editoriale;
		this.descripcion = descripcion;
		this.sinopsis = sinopsis;
		this.precio = precio;
		this.tipo = tipo;
		this.portada = portada;
		this.rating = rating;
		this.stock = stock;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaPubli() {
		return this.fechaPubli;
	}

	public void setFechaPubli(Date fechaPubli) {
		this.fechaPubli = fechaPubli;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPortada() {
		return this.portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getRating() {
		return this.rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getSaga() {
		return this.saga;
	}

	public void setSaga(String saga) {
		this.saga = saga;
	}

	public String getSinopsis() {
		return this.sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Object getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<DetallesPedido> getDetallesPedidos() {
		return this.detallesPedidos;
	}

	public void setDetallesPedidos(List<DetallesPedido> detallesPedidos) {
		this.detallesPedidos = detallesPedidos;
	}

	public DetallesPedido addDetallesPedido(DetallesPedido detallesPedido) {
		getDetallesPedidos().add(detallesPedido);
		detallesPedido.setLibro(this);

		return detallesPedido;
	}

	public DetallesPedido removeDetallesPedido(DetallesPedido detallesPedido) {
		getDetallesPedidos().remove(detallesPedido);
		detallesPedido.setLibro(null);

		return detallesPedido;
	}

	public Editoriale getEditoriale() {
		return this.editoriale;
	}

	public void setEditoriale(Editoriale editoriale) {
		this.editoriale = editoriale;
	}

	public Genero getGenero() {
		return this.genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public List<Valoracione> getValoraciones() {
		return this.valoraciones;
	}

	public void setValoraciones(List<Valoracione> valoraciones) {
		this.valoraciones = valoraciones;
	}

	public Valoracione addValoracione(Valoracione valoracione) {
		getValoraciones().add(valoracione);
		valoracione.setLibro(this);

		return valoracione;
	}

	public Valoracione removeValoracione(Valoracione valoracione) {
		getValoraciones().remove(valoracione);
		valoracione.setLibro(null);

		return valoracione;
	}

}