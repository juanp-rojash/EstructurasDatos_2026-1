package com.example;

import java.time.LocalDateTime;

public class Tarea implements Comparable<Tarea>{

    private String Descripcion;
    private int Prioridad;
    private LocalDateTime FechaIngreso;

    public Tarea(String descripcion, int prioridad, LocalDateTime fechaIngreso) {
        Descripcion = descripcion;
        Prioridad = prioridad;
        FechaIngreso = fechaIngreso;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public int getPrioridad() {
        return Prioridad;
    }

    public LocalDateTime getFechaIngreso() {
        return FechaIngreso;
    }

    @Override
    public int compareTo(Tarea otra){

        int resultado = 0;

        resultado = Integer.compare(Prioridad, otra.getPrioridad());

        if (resultado != 0) return resultado;

        resultado = otra.getFechaIngreso().compareTo(FechaIngreso);

        return resultado;

    }

    @Override
    public String toString(){

        String mensaje = "\n --- " + Descripcion + " --- \n* " + Prioridad + "\n* " + FechaIngreso + "\n";

        return mensaje;

    }
}
