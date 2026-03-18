package com.example;

import java.util.concurrent.CompletableFuture;

public class Buscador {

    public static void main(String[] args) {
        
        try {

            long tiempoInicio = 0l;
            long tiempoFinal = 0l;
            long promedioSync = 0l;
            long promedioAsync = 0l;
            long resultadoDiferencia = 0l;

            int dato1 = 9_999_001;
            int dato2 = 9_878_600;
            
            int cantidadElementos = 1_000_000_000;

            int [] vector = new int[cantidadElementos];

            for(int i = 0; i < vector.length; i++) vector[i] = i;

            System.out.println("== Sync ==");

            for(int i = 0; i < 3; i++){

                tiempoInicio = System.currentTimeMillis();
                busquedaSync(vector, dato1);
                busquedaSync(vector, dato2);
                tiempoFinal = System.currentTimeMillis();

                resultadoDiferencia = tiempoFinal - tiempoInicio;
                promedioSync += resultadoDiferencia;

                System.out.println("Ejecución #"+ i + "ms: " + resultadoDiferencia);

            }

            System.out.println("Tiempo promedio Sync = " + (promedioSync / 3) + " ms");


            System.out.println("== Async ==");

            for(int i = 0; i < 3; i++){

                tiempoInicio = System.currentTimeMillis();
                CompletableFuture<Integer> promesa1 = busquedaAsync(vector, dato1);
                CompletableFuture<Integer> promesa2 = busquedaAsync(vector, dato2);
        
                CompletableFuture.allOf(promesa1, promesa2).join();

                tiempoFinal = System.currentTimeMillis();

                resultadoDiferencia = tiempoFinal - tiempoInicio;
                promedioAsync += resultadoDiferencia;

                System.out.println("Ejecución #"+ i + "ms: " + resultadoDiferencia);

            }

            System.out.println("Tiempo promedio Async = " + (promedioAsync / 3) + " ms");

        } 
        catch (Exception e) {
            System.out.println("Error " + e);
        }

    }

    public static int busquedaSync(int [] vector, int dato) throws Exception{

        try {

            for(int i = 0; i < vector.length; i++){

                if(vector[i] == dato) return i;

            }

            return -1;
            
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    public static CompletableFuture<Integer> busquedaAsync(int [] vector, int dato){


        return CompletableFuture.supplyAsync(() -> {

            for(int i = 0; i < vector.length; i++){

                if(vector[i] == dato) return i;

            }

            return -1;

        });

    }
    
}
