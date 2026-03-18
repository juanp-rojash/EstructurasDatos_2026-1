package com.example;

public class Sync {

    public static void main(String[] args) {

        try {

            System.out.println("Invocacion metodo consulta datos");

            String resultado = consultaDatos();

            System.out.println("Resultados: " + resultado);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static String consultaDatos() throws Exception {

        try {

            System.out.println("\tInicio Consulta Datos");

            Thread.sleep(3000);

            System.out.println("\tFin Consulta Datos");

            return "Lista Datos [X]";


        } catch (Exception e) {
            throw new Exception(e);
        }

    }

}