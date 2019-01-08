package com.example.myapplication;

public class Producto implements Cloneable{
	
	private String nombre;
	private int stock;
	private double precio;
	
	
	public Producto(String nombre,double precio) {
		this.nombre=nombre;
		this.precio=precio;
		this.stock=0;
	}
	
	public double getPrecio() {
		return Math.round(precio);
	}

	public void setPrecio(double precio) {
		this.precio = precio;
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
	 public String mostrar() {
		 return this.nombre+ "  "+"--------"+"Unidades : "+this.stock;
	 }
	
	

}
