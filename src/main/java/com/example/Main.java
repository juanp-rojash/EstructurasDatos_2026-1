package com.example;

import java.time.LocalDateTime;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Cola de prioridad Minima
        PriorityQueue<Integer> colaPrioridadMin = new PriorityQueue<>();

        colaPrioridadMin.add(10);
        colaPrioridadMin.add(29);
        colaPrioridadMin.add(43);
        colaPrioridadMin.add(2);
        colaPrioridadMin.add(54);

        System.out.println("Cola de prioridad minima");
        System.out.println(colaPrioridadMin);

        // Cola de prioridad Maxima
        PriorityQueue<Integer> colaPrioridaMax = new PriorityQueue<>(Collections.reverseOrder());

        colaPrioridaMax.add(10);
        colaPrioridaMax.add(29);
        colaPrioridaMax.add(43);
        colaPrioridaMax.add(2);
        colaPrioridaMax.add(54);

        System.out.println("Cola de prioridad maxima");
        System.out.println(colaPrioridaMax);

        // Obtener el menor dato:
        int valorMinimo = colaPrioridadMin.remove();

        // Obtener el mayor dato
        int valorMaximo = colaPrioridaMax.remove();

        System.out.println("Cola de prioridad minima reorganizado");
        System.out.println(colaPrioridadMin);

        System.out.println("Cola de prioridad maxima reorganizado");
        System.out.println(colaPrioridaMax);

        List<Integer> listaNumerica = Arrays.asList(90, 23, 65, 88, 1, 6, 90);

        PriorityQueue<Integer> colaPrioridad = new PriorityQueue<>(listaNumerica);

        System.out.println("Extracción de nodos iterativo");
        while(!colaPrioridad.isEmpty()){

            System.out.println(colaPrioridad.remove());

        }

        /////

        PriorityQueue<Tarea> kanban = new PriorityQueue<>(Collections.reverseOrder());

        kanban.add( new Tarea("Taller Bases de Datos", 5, LocalDateTime.now().minusMinutes(2)));
        kanban.add( new Tarea("Crear Api", 10, LocalDateTime.now().minusMinutes(10)));
        kanban.add( new Tarea("Proyecto Arquitectura", 15, LocalDateTime.now().minusMinutes(7)));
        kanban.add( new Tarea("Comer", 20, LocalDateTime.now().minusMinutes(1)));
        kanban.add( new Tarea("Electricidad & Magnetismo", 15, LocalDateTime.now().minusMinutes(2)));

        while(!kanban.isEmpty()){

            System.out.println(kanban.remove());

        }

    }

}