package com.practica3.inventario;

import java.util.Date;

public class Transaccion {
	
	private String comentario;
	private Date fechaTransaccion;

	public Transaccion(String comentario ,Date fecha) {
		
		this.comentario=comentario;
		this.fechaTransaccion=fecha;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	

}
