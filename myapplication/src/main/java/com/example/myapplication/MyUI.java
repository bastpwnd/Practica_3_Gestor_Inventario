package com.example.myapplication;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.NativeButton;
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
	
	

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	Inventario inv=new Inventario();
        Producto a=new Producto("Play Station 4",245.5);
        Producto b=new Producto("Ordenador",1234.50);
        Producto c=new Producto("Silla",34.5);
        inv.addProducto(a);
        inv.addProducto(b);
        inv.addProducto(c);
        final VerticalLayout layout = new VerticalLayout();
        final HorizontalLayout layoutHorizontal = new HorizontalLayout();
        GridLayout gridLayout = new GridLayout(5, 15);
        gridLayout.setSpacing(true);
   
        //layout.addComponent(new Label("PRACTICA 3 - INVENTARIO"));
        gridLayout.addComponent(new Label("PRACTICA 3 - INVENTARIO"),0,1);
        gridLayout.addComponent(new Label("Bienvenidos al inventario de Ivan y Angel"),0,2);
        gridLayout.addComponent(new Label(""),0,3);
        //layout.addComponent(new Label("Bienvenidos al inventario de Ivan y Angel"));
        //layout.addComponent(new Label(" "));
        
        final TextField crear = new TextField();
        crear.setValue("nombre");
        

        //crear.setCaption("Nombre del producto");
        gridLayout.addComponent(crear,0,4);
        
        final TextField precio = new TextField();
        precio.setValue("precio");
        //precio.setCaption("Precio del producto");
        gridLayout.addComponent(precio,1,4);
        
        Button crearButton = new Button("Crear Producto");
        crearButton.addClickListener(e -> {
            inv.addProducto(new Producto(crear.getValue(),Integer.parseInt(precio.getValue())));
            Notification notif = new Notification(
            	    "Producto creado",
            	    Notification.TYPE_WARNING_MESSAGE);

            	// Customize it
            	notif.setDelayMsec(20000);
            	notif.setPosition(Position.BOTTOM_RIGHT);
            	notif.setStyleName("mystyle");
            	

            	// Show it in the page
            	notif.show(Page.getCurrent());
            //layout.addComponent(new Label("Producto creado " + crear.getValue()));
            //System.out.println(crear.getValue());
            crear.setValue("");
            precio.setValue("");
        });
        gridLayout.addComponent(crearButton,2,4);
        
        gridLayout.addComponent(new Label(" "),0,5);
        
        
        final TextField borrar = new TextField();
        //borrar.setCaption("Nombre del producto a Borrar");
        borrar.setValue("nombre");
        gridLayout.addComponent(borrar,0,6);
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
        gridLayout.addComponent(buttonBorrar,2,6);
        
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
        gridLayout.addComponent(numeroProductos,2,8);
        
        Button aa = new Button("aa");
        Grid<Producto> gridd = new Grid<>("Listado de productos");
    	gridd.setItems(inv.getProductos());
        gridd.addColumn(Producto::getNombre).setCaption("Nombre Producto");
        gridd.addColumn(Producto::getStock).setCaption("Stock");
        gridd.addColumn(Producto::getPrecio).setCaption("Precio por Unidad");
    
        gridd.setEnabled(true);
        //////////////////////////
        
        
      
        
        
        //////////////////////////
        Button listaProductos = new Button("Actualizar productos");
        listaProductos.addClickListener(e -> {
        	
        	
            gridd.getDataProvider().refreshAll();
        	
        	
        });
        gridLayout.addComponent(listaProductos,2,10);
        
        gridLayout.addComponent(gridd,3,12);

        
        final TextField nombrePro = new TextField();
        nombrePro.setValue("nombre");
        //crear.setCaption("Nombre del producto");
        gridLayout.addComponent(nombrePro,0,12);
        
        final TextField unidades = new TextField();
        unidades.setValue("unidades");
        //precio.setCaption("Precio del producto");
        gridLayout.addComponent(unidades,1,12);

        Button add = new Button("Añadir Stocks");
        add.addClickListener(e -> {
            for(Producto p:inv.getProductos()) {
            	if(p.getNombre().equals(nombrePro.getValue())) {
            		p.addStock(Integer.parseInt(unidades.getValue()));
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
            //layout.addComponent(new Label("Producto creado " + crear.getValue()));
            //System.out.println(crear.getValue());
            nombrePro.setValue("");
            unidades.setValue("");
        });
        gridLayout.addComponent(add,2,12);
        

        // Create a grid bound to the list
        
       
        
        //////////////////////////////////////
        
        /*
        layout.addComponents(crear,precio, crearButton);

        layout.addComponents(borrar, buttonBorrar);
        layout.addComponent(new Label(" "));
        //layout.setComponentAlignment(borrar, Alignment.MIDDLE_CENTER);
        layout.addComponents(numeroProductos);
        //layout.addComponents(listaProductos);
        layout.addComponent(new Label(" "));
        //layout.addComponent(gridd);
        //layout.addComponent(gridd);
        */
        
       setContent(gridLayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServledt", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
