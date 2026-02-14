package com.example.Api;

import com.example.Model.Peleador;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Servicio {

    public static String crearTorneo(List<Peleador> participantes) throws Exception {

        try{

            String [] lineaEnfrentamientoA;
            String [] lineaEnfrentamientoB;

            int cantidadElementosFila = 0;
            int divisorCantidadElementos = 0;
            int contadorA = 0;
            int contadorB = 0;

            String enfrentamientos = "";

            Queue<Peleador> filaPeleadores = new LinkedList<>();

            filaPeleadores.addAll(participantes);

            cantidadElementosFila = filaPeleadores.size();

            divisorCantidadElementos = (int) Math.floor(cantidadElementosFila / 2);

            lineaEnfrentamientoA = new String[divisorCantidadElementos];
            lineaEnfrentamientoB = new String[divisorCantidadElementos];

            while(!filaPeleadores.isEmpty()){

                if(filaPeleadores.size() > divisorCantidadElementos){

                    lineaEnfrentamientoA[contadorA++]= filaPeleadores.poll().name();

                }
                else if(filaPeleadores.size() <= lineaEnfrentamientoB.length){

                    lineaEnfrentamientoB[contadorB++]= filaPeleadores.poll().name();

                }

            }

            enfrentamientos = alineamientoTorneo(lineaEnfrentamientoA, lineaEnfrentamientoB);

            return enfrentamientos;

        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    public static String rastrearPoder(List<Peleador> participantes) throws Exception{

        try{

            String filtroLuchadores = "";

            Stack<Peleador> rastreador = new Stack<>();

            participantes
                    .stream()
                    .filter(p -> Integer.parseInt(p.ki().replace(".", "")) >= 1_000_000)
                    .forEach(rastreador::add);

            filtroLuchadores = alineamientoFiltroPoder(rastreador);

            return filtroLuchadores;

        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    // -- //

    private static String alineamientoTorneo(String[] lineaA, String[] lineaB) throws Exception {

        try {

            String enfrentamientos = "";

            for(int i = 0; i < lineaA.length; i++){

                enfrentamientos += "\n" + lineaA[i] + " VS " + lineaB[i];

            }

            return enfrentamientos;

        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    private static String alineamientoFiltroPoder(Stack<Peleador> rastreador) throws Exception {

        try{

            String acumuladorPeleadoresSuperiores = "";

            Peleador luchadorAuxiliar;

            while (!rastreador.isEmpty()){

                luchadorAuxiliar = rastreador.pop();

                acumuladorPeleadoresSuperiores += "\n" + luchadorAuxiliar.name() + "\t: " + luchadorAuxiliar.ki();

            }

            return acumuladorPeleadoresSuperiores;

        } catch (Exception e) {
            throw new Exception(e);
        }

    }

}
