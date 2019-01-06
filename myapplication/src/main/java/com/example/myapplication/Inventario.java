package com.example.myapplication;

import java.util.ArrayList;

public class Inventario {
	
	private ArrayList<Producto>productos;
	private int stockTotal;
	
	
	public Inventario() {
		this.productos=new ArrayList<Producto>();
		
	}
	
	public void addProducto(Producto a ) {
		this.productos.add(a);
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}
	public boolean removeProducto(String nombre) {
		for(Producto p: productos) {
			if(p.getNombre().equals(nombre)) {
				this.productos.remove(p);
				return true;
			}
		}
		return false;
	}
	
	public void totalStock() {
		for(Producto p: productos) {
			this.stockTotal=this.stockTotal+p.getStock();
		}
	}

}
