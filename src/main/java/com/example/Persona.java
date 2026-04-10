package com.example;

public class Persona implements Comparable<Persona> {

    private String Nombre;
    private int RangoEmergencia;

    public Persona(String nombre, int rangoEmergencia) {
        Nombre = nombre;
        RangoEmergencia = rangoEmergencia;
    }

    public String getNombre() {
        return Nombre;
    }

    public int getRangoEmergencia() {
        return RangoEmergencia;
    }

    @Override
    public int compareTo(Persona otra){

        int resultado = 0;

        resultado = Integer.compare(RangoEmergencia, otra.getRangoEmergencia());

        return resultado;

    }

    @Override
    public String toString(){

        String mensaje = "Paciente: " + Nombre + " *** Emergencia: " + RangoEmergencia + " ***";

        return mensaje;

    }
}
