package com.book.shop.controladores;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DetallesPedidoDTO {
	private int id;
	private int cantidad;
	private float importe;
	private int libro;
	private int pedido;

	public DetallesPedidoDTO() {
		super();
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public void setLibro(int libro) {
		this.libro = libro;
	}

	public void setPedido(int pedido) {
		this.pedido = pedido;
	}
}
