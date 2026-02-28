package com.example;

import com.example.Model.ListaEnlazadaSimple.LinkedSimpleList;

public class MainLinkedSimpleList {

    public static void main(String[] args) {

        LinkedSimpleList<String> contactoEstudiante = new LinkedSimpleList<>();

        contactoEstudiante.add("Thomas");
        contactoEstudiante.add("Juan");
        contactoEstudiante.add("Rojas");

        System.out.println(contactoEstudiante);

        contactoEstudiante.remove("Juan");

        System.out.println(contactoEstudiante);

        // TODO: Implementacion Manual de Listas Doblemente Enlazada


    }

}