package com.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        try{

            // Tipos de Datos

            // Crear Variable
            // [Tipo D] [Nombre Var] = [Dato];

            //Numericos
            byte datoByte = 1;
            short datoPequenio = 20;
            int datoEntero = 2000;
            long datoLargo = 233334;

            //Decimales
            float datoFloat = 1.5f;
            double datoDouble = 0.400404;

            //Cadenas de Caracteres
            String nombreUsuario = "Luisa";
            char caracter = 'T';

            //Booleanos
            boolean esMayorEdad = true;

            //Solicitar Datos
            // 1: Crear el scanner
            Scanner sc = new Scanner(System.in);

            //2: Solicitar el dato
            String cedulaUsuario = "";

            //Mostrar mensaje
            System.out.println("Digite su cedula: ");
            cedulaUsuario = sc.nextLine();

            short edadUsuario = 0;

            System.out.println("Ingres su edad: ");
            edadUsuario = sc.nextShort();
            sc.nextLine();

            //Coversiones de Datos
            //Parseo

            String numero = "20";
            //[Tipo D Obj] [Nombre Var] = [Obj].parse[Obj]([Valor]);
            short strShort = Short.parseShort(numero);
            float strFloat = Float.parseFloat("20");
            int strInt = Integer.parseInt(numero);

            //Double.parseDouble(20); Byte.parseByte(20);

            //Casteos:
            //[Variable] = ([Tipo de Dato Objetivo]) [Dato]

            long largo = 50000;
            short pequenio = (short) largo;

            //Concatenacion
            String primerNombre = "Juan";
            String primerApellido = "Rojas";

            String concatenacionElementos = primerNombre + " " + primerApellido + "\n Edad: " + edadUsuario;

            System.out.println(concatenacionElementos);

            // Condiciones
            // Operadores logicos and -> && or -> ||
            if(edadUsuario >= 50 && edadUsuario <= 100){
                System.out.println("Adulto Mayor");
            }
            else if(edadUsuario < 50 && edadUsuario >= 18){
                System.out.println("Adulto");
            }
            else{
                System.out.println("Joven");
            }

            int dado = 3;

            switch (dado){

                case 1:
                    System.out.println("cae la cara 1");
                    break;

                case 2:
                    System.out.println("cae la cara 2");
                    break;

                default:
                    System.out.println("cae la cara: " + dado);
            }

            // Ciclos

            // while ( [ expresion logica ] )

            int a = 0;
            while(true){

                if (a++ <= 10){
                    System.out.println("Iteracion #" + a);
                }
                else{
                    break;
                }

            }

            // for

            for(int i = 0; i <= 10; i++){
                System.out.println(i);
            }

            // do-while : Se ejecuta por lo menos una vez

            do{

                System.out.println("Primera ejecución del ciclo");

            }while(false);

            // Vector
            // [Tipo Dato] [ ] [Nombre Vector] = new [Tipo Dato] [ [ Cantidad de Datos ] ]

            String [] nombreEstudiantes = new String [3];

            nombreEstudiantes[0] = "Lucho";

            // Matriz
            // [Tipo Dato] [ ] [ ] [Nombre Matriz] = new [Tipo Dato] [ Cant. Filas ] [ Cant. Colum ]

            float [] [] temperaturas = new float [2] [3];

            temperaturas[0][1] = 4.6f;

        }
        catch (ArithmeticException ae){
            System.out.println("Error aritmetico: " + ae);
        }
        catch (Exception e) {
            throw new Exception("Error: " + e);
        }

    }

}