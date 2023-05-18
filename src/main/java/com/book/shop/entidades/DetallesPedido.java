package com.book.shop.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detalles_pedido database table.
 * 
 */
@Entity
@Table(name="detalles_pedido")
@NamedQuery(name="DetallesPedido.findAll", query="SELECT d FROM DetallesPedido d")
public class DetallesPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int cantidad;

	private float importe;

	//bi-directional many-to-one association to Libro
	@ManyToOne
	@JoinColumn(name="id_libro")
	private Libro libro;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;

	public DetallesPedido() {
	}
	
	public DetallesPedido(int id, int cantidad, float importe, Libro libro, Pedido pedido) {
		this.id = id;
		this.cantidad = cantidad;
		this.importe = importe;
		this.libro = libro;
		this.pedido = pedido;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getImporte() {
		return this.importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public Libro getLibro() {
		return this.libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}