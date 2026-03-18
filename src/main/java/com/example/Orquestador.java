package com.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Orquestador {

    public static void main(String[] args){

        CompletableFuture<String> consultaHistorias = CompletableFuture.supplyAsync(( ) -> {
            dormir(2000); return "Datos Historias";
        });

        CompletableFuture<String> consultaFeed = CompletableFuture.supplyAsync(( ) -> {
            dormir(2000); return "Datos Feed Application";
        });

        CompletableFuture<Void> orquestador = CompletableFuture.allOf(consultaHistorias, consultaFeed);

        orquestador.thenRun(() -> {

            try {

                System.out.println("Dashboard");
                System.out.println(consultaHistorias.get());
                System.out.println(consultaFeed.get());

            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        System.out.println("El usuario sigue usando la app");
        dormir(5000);
    }

    public static void dormir(int ms){

        try{Thread.sleep(ms);}catch(Exception e){}

    }

}
