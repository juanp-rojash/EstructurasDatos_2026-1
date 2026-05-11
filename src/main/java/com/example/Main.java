package com.example;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {


        Map<String, Integer> calendario = new HashMap<>();

        calendario.put("Enero", 1);
        calendario.put("Febrero", 2);
        calendario.put("Marzo", 3);

        System.out.println(calendario);

        System.out.println("Numero de mes de Febrero: " + calendario.get("Febrero"));

        System.out.println("¿Existe el mes de Abril? " + calendario.containsKey("Abril"));

        for(Map.Entry<String, Integer> entrada : calendario.entrySet()){

            System.out.println(" [ " + entrada.getKey() + " ] = " + entrada.getValue());

        }

    ///////////////////////////////////////////////////

        Map<Persona, String> organigrama = new HashMap<>();

        Persona p1 = new Persona(1, "Miguel", Timestamp.valueOf("2020-01-01 00:00:00"));
        Persona p2 = new Persona(2, "Hiler", Timestamp.valueOf("2020-01-02 00:00:00"));
        Persona p3 = new Persona(3, "Juan", Timestamp.valueOf("2020-01-01 00:00:00"));

        organigrama.put(p1, "Gerente");
        organigrama.put(p2, "Junior");
        organigrama.put(p3, "Empleado");

        System.out.println(organigrama);

        Persona p4 = new Persona(4, "Pablo", Timestamp.valueOf("2020-01-01 00:00:00"));

        String vacante = organigrama.remove(p1);

        organigrama.put(p4, vacante);
        organigrama.put(p2, "Senior");

        System.out.println(organigrama);

        p2.getFechaNacimiento().setTime(100000000);

        System.out.println(p2);

        System.out.println(organigrama.get(p2));

    }

}