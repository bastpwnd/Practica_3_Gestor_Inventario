package com.practica3.inventario;

import java.util.ArrayList;

public class CambioDivisa {
	
	private double divisaConstante;

	public CambioDivisa() {
		// TODO Auto-generated constructor stub
		
		this.divisaConstante=1.2;
	}
	
	
	public ArrayList<Producto> cambioPrecioDolares(ArrayList<Producto> a){
		
		ArrayList<Producto> cambioPrecio=new ArrayList<Producto>();
		for(Producto p: a) {
			p.setPrecio((p.getPrecio()*this.divisaConstante));
			cambioPrecio.add(p);
			}
		
		
		
		return cambioPrecio;
		
	}
	public ArrayList<Producto> cambioPrecioEuros(ArrayList<Producto> a){
		
		ArrayList<Producto> cambioPrecio=new ArrayList<Producto>();
		for(Producto p: a) {
			p.setPrecio((p.getPrecio()/this.divisaConstante));
			cambioPrecio.add(p);
			}
		
		
		
		return cambioPrecio;
		
	}

}
