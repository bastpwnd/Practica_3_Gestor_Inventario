package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.practica3.inventario.Producto;

public class TestProducto {

	Producto productoTester;
	
	 @BeforeEach  
	 public void setUp() {   
	    //Inicialización de variables antes de cada Test 
		 productoTester =new Producto("Pc",123.5);
	 }  
	 
	 @Test
	 @DisplayName("Testeando la funcion getNombre de Producto")
	 public void testObtenerNombre() {  
		 assertEquals(productoTester.getNombre(), "Pc"); 
	 }
	 
	 @Test
	 @DisplayName("Testeando la funcion getPrecio de Producto")
	 public void testObtenerPrecio() { 
		 System.out.println(productoTester.getPrecio());
		 assertEquals(productoTester.getPrecio(), 124.0);
		 }
	 
	 
	 @Test
	 @DisplayName("Testeando la funcion getStock de Producto")
	 public void testObtenerStock() { 
		 productoTester.setStock(10);
		 assertEquals(productoTester.getStock(), 10); 
		 assertNotEquals(productoTester.getStock(), 5); 

	 }
	 
	 @Test
	 @DisplayName("Testeando la funcion getStock de Producto")
	 public void testAñadirStock() { 
		 productoTester.addStock(5);
		 assertEquals(productoTester.getStock(),5); 
		 assertNotEquals(productoTester.getStock(),25); 

	 }
	 
	 @Test
	 @DisplayName("Testeando la funcion getStock de Producto")
	 public void testQuitarStock() {  
		 productoTester.setStock(10);
		 productoTester.removeStock(2);
		 assertEquals(productoTester.getStock(), 8);
		 assertNotEquals(productoTester.getStock(), 10);

	 }
	 
	 
}
