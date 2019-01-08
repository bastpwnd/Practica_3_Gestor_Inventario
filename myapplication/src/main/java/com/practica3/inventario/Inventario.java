package com.practica3.inventario;

import java.util.ArrayList;

public class Inventario {
	
	private ArrayList<Producto>productos;
	private ArrayList<Transaccion>transaciones;
	public ArrayList<Transaccion> getTransaciones() {
		return transaciones;
	}
	public void setTransaciones(ArrayList<Transaccion> transaciones) {
		this.transaciones = transaciones;
	}
	public int getStockTotal() {
		return stockTotal;
	}
	public void setStockTotal(int stockTotal) {
		this.stockTotal = stockTotal;
	}

	private int stockTotal;
	private double beneficio;
	
	
	public Inventario() {
		this.productos=new ArrayList<Producto>();
		this.transaciones=new ArrayList<Transaccion>();
		this.beneficio=500.0;
		
	}
	public void addBeneficio(double a) {
		this.beneficio=this.beneficio+a;;
	}
	public void addTransaccion(Transaccion a) {
		this.transaciones.add(a);
	}
	public void delBeneficio(double a) {
		this.beneficio=this.beneficio-a;
	}
	
	public double getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(double beneficio) {
		this.beneficio = beneficio;
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
	
	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	public void totalStock() {
		for(Producto p: productos) {
			this.stockTotal=this.stockTotal+p.getStock();
		}
	}

}
