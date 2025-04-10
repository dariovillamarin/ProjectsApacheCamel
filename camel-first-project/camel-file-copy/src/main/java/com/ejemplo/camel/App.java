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
                    .process(exchange -> {
                    String nombreArchivo = exchange.getIn().getHeader("CamelFileName", String.class);
                    System.out.println("Archivo copiado: " + nombreArchivo);
                     })
                    .to("file://output");
            }
        });

        // Iniciar el contexto
        context.start();

        // Mostrar mensaje "Escuchando..." cada 5 segundos en un hilo separado
        new Thread(() -> {
            while (true) {
                System.out.println("Escuchando...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();

        // Mantener la aplicación viva, escuchando
        Thread.currentThread().join();;

        // Detener el contexto
        //context.stop();
    
    }
}
