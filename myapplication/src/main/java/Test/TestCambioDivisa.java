package Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.practica3.inventario.CambioDivisa;
import com.practica3.inventario.Producto;

public class TestCambioDivisa {
	
	CambioDivisa divisa;
	Producto a;
	
	 @BeforeEach  
	 public void setUp() {   
	    //Inicialización de variables antes de cada Test 
		 a=new Producto("a",8);
		 a.setPrecio(10);
		 divisa =new CambioDivisa();
	 }  
	 
	 @Test
	 @DisplayName("Testeando la funcion getNombre de Producto")
	 public void testDolares() {  
		 
		 assertEquals(a.getPrecio(), 10); 
	 }
	 

}
