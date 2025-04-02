package com.ejemplo.camel;

/**
 * Hello world!
 *
 */

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        //System.out.println( "Hello World!" );
        
        // Crear el contexto de Camel
        CamelContext context = new DefaultCamelContext();

        // Agregar una ruta
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                // Ruta simple: mover archivos .txt de input a output
                from("file://input?noop=true")
                    .to("file://output");
            }
        });

        // Iniciar el contexto
        context.start();

        // Esperar un poco para que se copien los archivos
        Thread.sleep(5000);

        // Detener el contexto
        context.stop();
    
    }
}
