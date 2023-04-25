package com.book.shop.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pedidos database table.
 * 
 */
@Entity
@Table(name="pedidos")
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private float importe;

	//bi-directional many-to-one association to DetallesPedido
	@OneToMany(mappedBy="pedido")
	private List<DetallesPedido> detallesPedidos;

	//bi-directional many-to-one association to Descuento
	@ManyToOne
	@JoinColumn(name="id_descuento")
	private Descuento descuento;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Pedido() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getImporte() {
		return this.importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public List<DetallesPedido> getDetallesPedidos() {
		return this.detallesPedidos;
	}

	public void setDetallesPedidos(List<DetallesPedido> detallesPedidos) {
		this.detallesPedidos = detallesPedidos;
	}

	public DetallesPedido addDetallesPedido(DetallesPedido detallesPedido) {
		getDetallesPedidos().add(detallesPedido);
		detallesPedido.setPedido(this);

		return detallesPedido;
	}

	public DetallesPedido removeDetallesPedido(DetallesPedido detallesPedido) {
		getDetallesPedidos().remove(detallesPedido);
		detallesPedido.setPedido(null);

		return detallesPedido;
	}

	public Descuento getDescuento() {
		return this.descuento;
	}

	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}