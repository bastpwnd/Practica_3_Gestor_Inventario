package com.example.myapplication;

public class Producto {
	
	private String nombre;
	private int stock;
	
	
	public Producto(String nombre) {
		this.nombre=nombre;
	}
	
	public void addStock(int numero) {
		this.stock=this.stock+numero;
	}
	
	public void removeStock(int numero) {
		this.stock=this.stock-numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	

}
