package com.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Ejemplo de Programación Asincrónica con CompletableFuture
 * 
 * CompletableFuture permite ejecutar tareas en segundo plano sin bloquear el hilo principal.
 * 
 * PASOS CLAVE:
 * Paso 1: Definir el Supplier -> La tarea/operación que se ejecutará en segundo plano
 * Paso 2: supplyAsync() -> Especifica que la tarea se ejecutará de manera asincrónica
 * Paso 3: CompletableFuture -> Retorna un "ticket" (promesa) que representa el resultado futuro
 * Paso 4: thenAccept/thenApply -> Callback que reacciona cuando la tarea se completa
 * 
 * VENTAJA: El hilo principal no se bloquea esperando el resultado
 */
public class Async {

    // Variable para almacenar resultados de operaciones asincrónicas
    // NOTA: En operaciones reales, es mejor usar thenAccept() para manejar resultados

    public static int resultado;

    public static void main(String[] args){

        try{
            System.out.println("Invocacion a metodo Consultar Datos");

            // Ejemplo 1: thenAccept()
            // thenAccept(Consumer): Recibe el resultado y lo usa (imprimir, asignar, etc)
            // NO retorna nada, solo ejecuta una acción
            consultarDatos().thenAccept( x -> System.out.println(x) );

            // Ejemplo 2: Operación en segundo plano 
            // supplyAsync() -> Ejecuta en un hilo
            // Nota: Si usamos .get() aquí, BLOQUEAMOS el hilo principal

            // resultado = operacionSegundoPlano(5).get(); 
            
            // Alternativa: usar thenAccept para procesar el resultado cuando llegue
            operacionSegundoPlano(5).thenAccept(result -> {
                resultado = result;
                System.out.println("Resultado de operación asincrónica: " + resultado);
            });

            // El hilo principal CONTINÚA ejecutándose sin esperar
            System.out.println("Termino el flujo principal <Dormir hilo principal>");

            Thread.sleep(11000);

            System.out.println(resultado);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static CompletableFuture<String> consultarDatos(){

        // CompletableFuture.supplyAsync() ejecuta la tarea en un thread pool diferente
        // La tarea se ejecuta SIN BLOQUEAR el hilo principal
        return CompletableFuture.supplyAsync( () -> {

            try {

                Thread.sleep(3000);

                return "\tLista Usuarios -> [X]";

            }
            catch (Exception e){
                return e.getMessage();
            }

        } );

    }

    public static CompletableFuture<Integer> operacionSegundoPlano(int x){

        // supplyAsync() retorna un CompletableFuture con el resultado futuro
        return CompletableFuture.supplyAsync( () -> {

            try {
                int y = x * 2;

                Thread.sleep(3000);
                
                return y;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

    }

}
