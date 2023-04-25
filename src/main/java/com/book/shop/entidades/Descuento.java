package com.book.shop.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the descuentos database table.
 * 
 */
@Entity
@Table(name="descuentos")
@NamedQuery(name="Descuento.findAll", query="SELECT d FROM Descuento d")
public class Descuento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="`%`")
	private int porcentaje;

	@Column(name="importe_minimo")
	private int importeMinimo;

	private String titulo;

	//bi-directional many-to-one association to Pedido
	@OneToMany(mappedBy="descuento")
	private List<Pedido> pedidos;

	public Descuento() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPorcentaje() {
		return this.porcentaje;
	}

	public void set_(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	public int getImporteMinimo() {
		return this.importeMinimo;
	}

	public void setImporteMinimo(int importeMinimo) {
		this.importeMinimo = importeMinimo;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido addPedido(Pedido pedido) {
		getPedidos().add(pedido);
		pedido.setDescuento(this);

		return pedido;
	}

	public Pedido removePedido(Pedido pedido) {
		getPedidos().remove(pedido);
		pedido.setDescuento(null);

		return pedido;
	}

}