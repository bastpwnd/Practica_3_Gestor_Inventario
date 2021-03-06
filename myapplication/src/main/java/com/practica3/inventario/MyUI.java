package com.practica3.inventario;


import java.util.Calendar;

import javax.servlet.annotation.WebServlet;

import com.practica3.inventario.Inventario;
import com.practica3.inventario.MyUI;
import com.practica3.inventario.Producto;
import com.practica3.inventario.Transaccion;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add sdcomponent to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {	
	
	private boolean divisaActual=true;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	Inventario inv=new Inventario();
    	/*datos por defecto aplicacion*/
    	Calendar ca = Calendar.getInstance();
        Producto a=new Producto("Play Station 4",245.5);
        Producto b=new Producto("Ordenador",1234.50);
        Producto c=new Producto("Silla",34.5);
        Transaccion t=new Transaccion("Transaccion por defecto",ca.getTime());
        inv.addTransaccion(t);		
        		
     
        inv.addProducto(a);
        inv.addProducto(b);
        inv.addProducto(c);
        final VerticalLayout layout = new VerticalLayout();
        final GridLayout gridLayout = new GridLayout(5, 25);
        gridLayout.setSpacing(true);
        
        Grid<Producto> gridd = new Grid<>("Listado de productos");
    	gridd.setItems(inv.getProductos());
        gridd.addColumn(Producto::getNombre).setCaption("Nombre Producto");
        gridd.addColumn(Producto::getStock).setCaption("Stock");
        gridd.addColumn(Producto::getPrecio).setCaption("Precio por Unidad");
        gridd.setEnabled(true);
        
        
        final TextField crear = new TextField();
        crear.setValue("nombre");
        //crear.setCaption("Nombre del producto");
        
        
        final TextField precio = new TextField();
        precio.setValue("precio");
        //precio.setCaption("Precio del producto");
        
        
        Button crearButton = new Button("Crear Producto");
        crearButton.addClickListener(e -> {
            inv.addProducto(new Producto(crear.getValue(),Double.parseDouble(precio.getValue())));
            Notification notif = new Notification(
            	    "Producto creado",
            	    Notification.TYPE_WARNING_MESSAGE);

            	// Customize it
            	notif.setDelayMsec(20000);
            	notif.setPosition(Position.BOTTOM_RIGHT);
            	notif.setStyleName("mystyle");
            	
            	// Show it in the page
            	notif.show(Page.getCurrent());
            crear.setValue("");
            precio.setValue("");
            gridd.getDataProvider().refreshAll();
            
        });
        
        
        
        
        
        final TextField borrar = new TextField();
        //borrar.setCaption("Nombre del producto a Borrar");
        borrar.setValue("nombre");
        
        Button buttonBorrar = new Button("Borrar Producto");
        buttonBorrar.addClickListener(e -> {
        	if(inv.removeProducto(borrar.getValue())) {
        		//layout.addComponent(new Label("Producto borrado " ));
        		Notification notif = new Notification(
                	    "Producto Borrado",
                	    Notification.TYPE_WARNING_MESSAGE);

                	// Customize it
                	notif.setDelayMsec(2000);
                	notif.setPosition(Position.BOTTOM_RIGHT);
                	notif.setStyleName("mystyle");
               	
                	// Show it in the page
                	notif.show(Page.getCurrent());
                	gridd.getDataProvider().refreshAll();
        		borrar.setValue("");
        	}else {
        		//layout.addComponent(new Label("Producto no borrado"));
        		Notification notif = new Notification(
                	    "Producto no borrado",
                	    Notification.TYPE_ERROR_MESSAGE);

                	// Customize it
                	notif.setDelayMsec(2000);
                	notif.setPosition(Position.BOTTOM_RIGHT);
                	notif.setStyleName("mystyle");
                	

                	// Show it in the page
                	notif.show(Page.getCurrent());
        		borrar.setValue("");
        	}
            
        });
        
        
        Button numeroProductos = new Button("Numero productos");
        numeroProductos.addClickListener(e -> {
            //layout.addComponent(new Label("Numero de Productos :" + inv.getProductos().size()));
        	Notification notif = new Notification(
        			"Numero de productos : ",
        			String.valueOf(inv.getProductos().size()),
            	    Notification.TYPE_ERROR_MESSAGE);

            	// Customize it
            	notif.setDelayMsec(2000);
            	notif.setPosition(Position.BOTTOM_RIGHT);
            	notif.setStyleName("mystyle");
            	

            	// Show it in the page
            	notif.show(Page.getCurrent());
        	
        });
        
        
        
        //////////////////////////
        
        Grid<Transaccion> tablaTransaccion = new Grid<>("Listado de transacciones");
        tablaTransaccion.setItems(inv.getTransaciones());
        tablaTransaccion.addColumn(Transaccion::getComentario).setCaption("Comentario Transaccion");
        tablaTransaccion.addColumn(Transaccion::getFechaTransaccion).setCaption("Fecha");
        tablaTransaccion.setEnabled(true);
        
 
        
        //////////////////////////
        Button listaProductos = new Button("Actualizar productos");
        listaProductos.addClickListener(e -> {
        	        	
            gridd.getDataProvider().refreshAll();
            tablaTransaccion.getDataProvider().refreshAll();
        	        	
        });

        Button cambioDivisa = new Button("Cambiar Divisa");
        cambioDivisa.addClickListener(e -> {
    		CambioDivisa divisa=new CambioDivisa();

        	if(divisaActual) {
            	inv.setProductos(divisa.cambioPrecioDolares(inv.getProductos()));
            	divisaActual=false;
            	Notification notif = new Notification(
            			"Precio cambiado a Dolares",
                	    Notification.TYPE_ERROR_MESSAGE);

                	// Customize it
                	notif.setDelayMsec(500);
                	notif.setPosition(Position.BOTTOM_RIGHT);
                	notif.setStyleName("mystyle");
                	

                	// Show it in the page
                	notif.show(Page.getCurrent());
        	}else {
        		inv.setProductos(divisa.cambioPrecioEuros(inv.getProductos()));
            	divisaActual=true;
            	Notification notif = new Notification(
            			"Precio cambiado a Euros",
                	    Notification.TYPE_ERROR_MESSAGE);

                	// Customize it
                	notif.setDelayMsec(500);
                	notif.setPosition(Position.BOTTOM_RIGHT);
                	notif.setStyleName("mystyle");
                	// Show it in the page
                	notif.show(Page.getCurrent());
        	}	        	
            gridd.getDataProvider().refreshAll();
        });
        

        final TextField nombrePro = new TextField();
        nombrePro.setValue("nombre");
        //crear.setCaption("Nombre del producto");
        
        
        final TextField unidades = new TextField();
        unidades.setValue("unidades");
        //precio.setCaption("Precio del producto");
        

        Button add = new Button("Añadir Stocks");
        add.addClickListener(e -> {
            for(Producto p:inv.getProductos()) {
            	if(p.getNombre().equals(nombrePro.getValue())) {
            		p.addStock(Integer.parseInt(unidades.getValue()));
            		inv.delBeneficio(p.getPrecio()*Double.parseDouble(unidades.getValue()));
            		Calendar caa = Calendar.getInstance();
            		Transaccion td=new Transaccion("Se compro el producto "+nombrePro.getValue(),caa.getTime());
                    inv.addTransaccion(td);
                    tablaTransaccion.getDataProvider().refreshAll();
                    gridd.getDataProvider().refreshAll();
            	}
            }
        	
            Notification notif = new Notification(
            	    "Stock Añadido",
            	    Notification.TYPE_WARNING_MESSAGE);

            	// Customize it
            	notif.setDelayMsec(20000);
            	notif.setPosition(Position.BOTTOM_RIGHT);
            	notif.setStyleName("mystyle");            
            	// Show it in the page
            	notif.show(Page.getCurrent());
            nombrePro.setValue("");
            unidades.setValue("");
        });
        
               
        final TextField nombreProd = new TextField();
        nombreProd.setValue("nombre");
        //crear.setCaption("Nombre del producto");
        
        
        final TextField unidadess = new TextField();
        unidadess.setValue("unidades");
        //precio.setCaption("Precio del producto");
        
        
        final TextField ingresos = new TextField();
        ingresos.setValue("cantidad");
        //precio.setCaption("Precio del producto");
        
        
        final TextField gastos = new TextField();
        gastos.setValue("cantidad");
        //precio.setCaption("Precio del producto");
        
        
        Button ingresar = new Button("Ingresar");
        ingresar.addClickListener(e -> {
        	
        	inv.addBeneficio(Double.parseDouble(ingresos.getValue()));
        	Calendar caa = Calendar.getInstance();
        	Transaccion td=new Transaccion("Ingreso por valor de "+ingresos.getValue(),caa.getTime());
            inv.addTransaccion(td);
            tablaTransaccion.getDataProvider().refreshAll();
            gridd.getDataProvider().refreshAll();
            Notification notif = new Notification(
            	    "ingresado",
            	    ingresos.getValue(),
            	    Notification.TYPE_WARNING_MESSAGE);

            	// Customize it
            	notif.setDelayMsec(600);
            	notif.setPosition(Position.BOTTOM_RIGHT);
            	notif.setStyleName("mystyle");
            
            	// Show it in the page
            	notif.show(Page.getCurrent());
                ingresos.setValue("");

            
        });
        
        Button gastar = new Button("Gastos");
        gastar.addClickListener(e -> {
        	
        	inv.delBeneficio(Double.parseDouble(gastos.getValue()));
        	Calendar caa = Calendar.getInstance();
        	Transaccion td=new Transaccion("Gasto por valor de "+gastos.getValue(),caa.getTime());
            inv.addTransaccion(td);
            tablaTransaccion.getDataProvider().refreshAll();
            Notification notif = new Notification(
            	    "Gastos ",
            	    gastos.getValue(),
            	    Notification.TYPE_WARNING_MESSAGE);

            	// Customize it
            	notif.setDelayMsec(600);
            	notif.setPosition(Position.BOTTOM_RIGHT);
            	notif.setStyleName("mystyle");
            
            	// Show it in the page
            	notif.show(Page.getCurrent());
                gastos.setValue("");
            
        });
        

        Button del = new Button("Eliminar Stocks");
        del.addClickListener(e -> {
            for(Producto p:inv.getProductos()) {
            	if(p.getNombre().equals(nombreProd.getValue())) {
            		p.removeStock(Integer.parseInt(unidadess.getValue()));
            		inv.addBeneficio(p.getPrecio()*Double.parseDouble(unidadess.getValue()));
            		Calendar caa = Calendar.getInstance();
            		Transaccion td=new Transaccion("Se vendio el producto "+nombreProd.getValue(),caa.getTime());
                    inv.addTransaccion(td);
                    tablaTransaccion.getDataProvider().refreshAll();
                    gridd.getDataProvider().refreshAll();
            	}
            }
        	
            Notification notif = new Notification(
            	    "Stock Eliminado",
            	    Notification.TYPE_WARNING_MESSAGE);

            	// Customize it
            	notif.setDelayMsec(900);
            	notif.setPosition(Position.BOTTOM_RIGHT);
            	notif.setStyleName("mystyle");
            	
            	// Show it in the page
            	notif.show(Page.getCurrent());
            nombreProd.setValue("");
            unidadess.setValue("");
        });
        
               
        Button ver = new Button("Beneficios");
        ver.addClickListener(e -> {
        	
            Notification notif = new Notification(
            	    "Beneficio",
            	    String.valueOf(inv.getBeneficio()),
            	    Notification.TYPE_WARNING_MESSAGE);

            	// Customize it
            	notif.setDelayMsec(600);
            	notif.setPosition(Position.BOTTOM_RIGHT);
            	notif.setStyleName("mystyle");
            
            	// Show it in the page
            	notif.show(Page.getCurrent());            
        });
        
        /*AÑADIMOS LOS  COMPONENTES */
        
       gridLayout.addComponent(new Label("PRACTICA 3 - INVENTARIO"),0,1);
       gridLayout.addComponent(new Label("Bienvenidos al inventario de Ivan Martin y Angel Rey"),0,2);
       gridLayout.addComponent(new Label(""),0,3);
       gridLayout.addComponent(crear,0,4);
       gridLayout.addComponent(precio,1,4);
       gridLayout.addComponent(crearButton,2,4);
       gridLayout.addComponent(new Label(" "),0,5);
       gridLayout.addComponent(borrar,0,6);
       gridLayout.addComponent(buttonBorrar,2,6);
       gridLayout.addComponent(numeroProductos,3,8);
       gridLayout.addComponent(tablaTransaccion,3,20);
       gridLayout.addComponent(cambioDivisa,3,9);
       gridLayout.addComponent(listaProductos,3,6);
       gridLayout.addComponent(gridd,3,12);
       gridLayout.addComponent(nombrePro,0,9);
       gridLayout.addComponent(unidades,1,9);
       gridLayout.addComponent(add,2,9);
       gridLayout.addComponent(nombreProd,0,8);
       gridLayout.addComponent(unidadess,1,8);
       gridLayout.addComponent(ingresos,0,10);
       gridLayout.addComponent(gastos,1,10);
       gridLayout.addComponent(ingresar,0,11);
       gridLayout.addComponent(gastar,1,11);
       gridLayout.addComponent(ver,3,4);
       gridLayout.addComponent(del,2,8);
       setContent(gridLayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServledt", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}