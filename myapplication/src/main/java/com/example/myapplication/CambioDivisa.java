package com.example.myapplication;

import java.util.ArrayList;

public class CambioDivisa {
	
	private double divisaConstante;

	public CambioDivisa() {
		// TODO Auto-generated constructor stub
		
		this.divisaConstante=1.2;
	}
	
	
	public ArrayList<Producto> cambioPrecio(ArrayList<Producto> a){
		
		ArrayList<Producto> cambioPrecio=new ArrayList<Producto>();
		for(Producto p: a) {
			p.setPrecio((p.getPrecio()*this.divisaConstante));
			cambioPrecio.add(p);
			}
		
		
		
		return cambioPrecio;
		
	}

}
