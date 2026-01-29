package com.example;

import com.example.Model.Persona;

public class Main {

    public static void main(String[] args) {

        try {

            Persona p1 = new Persona("Luis", 20);
            Persona p2 = new Persona("Luis", 20);

            Persona p3 = p2;

            p3.setNombre("Carlos");

            System.out.println(p2);

        } catch (Exception e) {
            System.out.println(e);
        }


    }

}