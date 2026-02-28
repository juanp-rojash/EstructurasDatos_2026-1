package com.example;

import java.util.LinkedList;

public class MainLinkedList {

    public static void main(String [] args){

        LinkedList<Integer> lista_D_Enlazada = new LinkedList<>();

        lista_D_Enlazada.add(1);
        lista_D_Enlazada.add(0, 2);
        lista_D_Enlazada.add(3);
        lista_D_Enlazada.add(4);

        System.out.println("Elemento indice 1: " + lista_D_Enlazada.get(1));

        for( int i = 0; i < lista_D_Enlazada.size(); i++){

            System.out.println("Elemento Indice: " + i + " : " + lista_D_Enlazada.get(i));

        }

        lista_D_Enlazada.remove(1);
        System.out.println(lista_D_Enlazada);

    }

}
