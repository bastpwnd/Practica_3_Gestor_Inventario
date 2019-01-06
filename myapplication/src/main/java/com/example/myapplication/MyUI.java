package com.example.myapplication;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
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
        Producto a=new Producto("Play Station 4");
        Producto b=new Producto("Ordenador");
        Producto c=new Producto("Silla");
        inv.addProducto(a);
        inv.addProducto(b);
        inv.addProducto(c);
        final VerticalLayout layout = new VerticalLayout();
        
        layout.addComponent(new Label("PRACTICA 3 - INVENTARIO"));
        layout.addComponent(new Label("Bienvenidos al inventario de Ivan y Angel"));
        layout.addComponent(new Label(" "));
        
        final TextField crear = new TextField();
        Button crearButton = new Button("Crear Producto");
        crearButton.addClickListener(e -> {
            inv.addProducto(new Producto(crear.getValue()));
            layout.addComponent(new Label("Producto creado " + crear.getValue()));
            //System.out.println(crear.getValue());
            crear.setValue("");
        });
        
        layout.addComponent(new Label(" "));
        final TextField borrar = new TextField();
        //borrar.setCaption("Borrar producto");
        Button buttonBorrar = new Button("Borrar Producto");
        buttonBorrar.addClickListener(e -> {
        	if(inv.removeProducto(borrar.getValue())) {
        		layout.addComponent(new Label("Producto borrado " ));
        		borrar.setValue("");
        	}else {
        		layout.addComponent(new Label("Producto no borrado"));
        		borrar.setValue("");
        	}
            
        });
        
        
        Button numeroProductos = new Button("Mostrar numero de productos");
        numeroProductos.addClickListener(e -> {
            layout.addComponent(new Label("Numero de Productos :" + inv.getProductos().size() 
                    ));
        });
        
        
       
        Button listaProductos = new Button("Mostrar productos");
        listaProductos.addClickListener(e -> {
        	for(Producto p: inv.getProductos()) {
            	layout.addComponent(new Label(p.mostrar()));
    		}
        });
        
        /*
        final TextField name2 = new TextField();
        name2.setCaption("Actualmente tenemos por defecto :"+String.valueOf(inv.getProductos().size())+" productos");
        */
        Grid grid = new Grid("Listado de productos"); 
        
        
        //////////////////////////////////////
        
       
        layout.addComponents(crear, crearButton);

        layout.addComponents(borrar, buttonBorrar);
        layout.addComponent(new Label(" "));
        layout.addComponents(numeroProductos);
        layout.addComponents(listaProductos);
        layout.addComponent(new Label(" "));
        
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServledt", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
