package com.example;

import com.example.Model.Caja;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        // Tipos Genericos
        Caja <String, Integer> caja1 = new Caja<>("Contenido Caja #1", 1);
        Caja <Float, Integer> caja2 = new Caja<>(0.5f, 2);

        System.out.println(caja1);
        System.out.println(caja2);

        // Bag
        Bag<String> bolsa1 = new HashBag<>();
        Bag<Caja<Integer, String>> bolsa2 = new HashBag<>();
        Bag<Caja> bolsa3 = new HashBag<>();

        bolsa3.add(caja1);
        bolsa3.add(caja2);

        bolsa1.add("Mandarina");
        bolsa1.add("Fresa");
        bolsa1.add("Mango");
        bolsa1.add("Mandarina");
        bolsa1.add("Mango");

        System.out.println("\n");
        System.out.println("Contenido de la bolsa \n" + bolsa1);
        System.out.println("Mandarina: " + bolsa1.getCount("Mandarina"));
        System.out.println("Cantidad elementos almcenados: " + bolsa1.size());
        System.out.println("Elementos de la bolsa: " + bolsa1.uniqueSet());
        bolsa1.remove("Mandarina", 1);
        System.out.println("Contenido de la bolsa \n" + bolsa1);
        System.out.println("\n");

        // Queue
        System.out.println("\n");

        Queue<Caja> filaObjetos = new LinkedList<>();

        filaObjetos.add(caja2);
        filaObjetos.add(caja1);

        System.out.println("Fila: \n" + filaObjetos);

        Caja aux;
        int contador = 0;

        while (! filaObjetos.isEmpty() ){

            aux = filaObjetos.poll();

            System.out.println(++contador + " \n " + aux);

        }

        // Stack

        Stack<String> pila = new Stack<>();

        pila.push("Plato Mama");
        pila.push("Plato Hermano");
        pila.push("Plato Prima");

        String elemento = "";

        while (pila.size() > 0){

            elemento = pila.pop();

            System.out.println(elemento);

        }


    }

}