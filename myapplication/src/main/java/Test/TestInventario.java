package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.practica3.inventario.Inventario;


public class TestInventario {
	
		Inventario inv;
		
		@BeforeEach  
		 public void setUp() {   
		    //Inicialización de variables antes de cada Test 
			inv =new Inventario();
			inv.setBeneficio(600);
			
		 }  
		
		@Test
		 @DisplayName("Testeando la funcion add de inventario")
		 public void testAddBeneficio() {
			inv.addBeneficio(200);
			assertEquals(800, inv.getBeneficio());
		
		 }
		@Test
		 @DisplayName("Testeando la funcion del de inventario")
		 public void testDelBeneficio() {
			inv.delBeneficio(200);
			assertEquals(400, inv.getBeneficio());
		
		 }
		


	
	
}
