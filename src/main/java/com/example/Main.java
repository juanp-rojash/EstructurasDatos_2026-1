package com.example;


import com.example.Model.Nodo;
import com.example.Model.Paciente;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {

        // Ejercicio #1: Carga de Tareas

        int k = 3;

        int [] tarea = {15, 10, 20, 5, 15};

        int [] resultado;

        Nodo nodo;

        PriorityQueue<Nodo> nodosTrabajo = new PriorityQueue<Nodo>();

        for(int i = 0; i < k; i++){

            nodo = new Nodo(i, 0);

            nodosTrabajo.add(nodo);

        }

        resultado = balanceadorCarga(nodosTrabajo, tarea);

        for(int i = 0; i < resultado.length; i++) System.out.print("|" + resultado[i]);





        // Ejercicio #2: Triage de Emergencia

        PriorityQueue<Paciente> cola = new PriorityQueue<>();

        cola.add(new Paciente("Juan Perez", "URGENCIA", LocalDateTime.of(2026, 4, 10, 10, 30, 0)));
        cola.add(new Paciente("Maria Gomez", "EMERGENCIA", LocalDateTime.of(2026, 4, 10, 9, 15, 0)));
        cola.add(new Paciente("Carlos Ruiz", "RESUCITACION",  LocalDateTime.of(2026, 4, 10, 11, 45, 0)));
        cola.add(new Paciente("Sofia Ramirez", "EMERGENCIA", LocalDateTime.of(2026, 4, 10, 8, 0, 0)));
        cola.add(new Paciente("Pedro Lopez", "EMERGENCIA", LocalDateTime.of(2026, 4, 10, 12, 0, 0)));

        // Mostrar orden de atención

        System.out.println("\n\n\n");

        while (!cola.isEmpty()) { System.out.println(cola.poll()); }




        // Ejercicio #3: Stock Tienda



    }

    public static int [] balanceadorCarga(PriorityQueue<Nodo> nodos, int [] carga){

        int [] balanceador = new int[ carga.length ];

        Nodo auxiliar;

        for(int i = 0; i < carga.length; i++){

            auxiliar = nodos.poll();

            auxiliar.acumularCarga(carga[i]);

            System.out.println("\n *** Nodo ** \n " + auxiliar);

            balanceador[i] = auxiliar.getId();

            nodos.add(auxiliar);

        }

        System.out.println(nodos);

        return balanceador;

    }
}