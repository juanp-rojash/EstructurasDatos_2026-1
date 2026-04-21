package com.example.Model;

import java.time.LocalDateTime;

public class Paciente implements Comparable<Paciente> {

    private String Nombre;
    private String TipoUrgencia;
    private LocalDateTime HorarioIngreso;
    private int NivelEmergencia;

    public Paciente(String nombre, String tipoUrgencia, LocalDateTime horarioIngreso) {

        Nombre = nombre;
        TipoUrgencia = tipoUrgencia;
        HorarioIngreso = horarioIngreso;

        evalurNivelUrgencia(tipoUrgencia);

    }

    public String getNombre() {
        return Nombre;
    }

    public String getTipoUrgencia() {
        return TipoUrgencia;
    }

    public LocalDateTime getHorarioIngreso() {
        return HorarioIngreso;
    }

    public int getNivelEmergencia() {
        return NivelEmergencia;
    }

    private void evalurNivelUrgencia(String tipoUrgencia){

        switch (tipoUrgencia.toUpperCase()){

            case "RESUCITACION" -> NivelEmergencia = 1;
            case "EMERGENCIA" -> NivelEmergencia = 2;
            case "URGENCIA" -> NivelEmergencia = 3;
            case "URGENCIA MENOR" -> NivelEmergencia = 4;
            case "SIN URGENCIA" -> NivelEmergencia = 5;
            default -> NivelEmergencia = 6;

        }

    }

    @Override
    public int compareTo(Paciente otra){

        int resultado = 0;

        resultado = Integer.compare(NivelEmergencia, otra.getNivelEmergencia());

        if (resultado != 0) return resultado;

        resultado = HorarioIngreso.compareTo(otra.getHorarioIngreso());

        return resultado;

    }

    @Override
    public String toString() {
        return "\nPaciente {\n" +
                "\tNombre = " + Nombre + "\n" +
                "\tTipoUrgencia = " + TipoUrgencia + "\n" +
                "\tHorarioIngreso = " + HorarioIngreso + "\n" +
                "\tNivelEmergencia = " + NivelEmergencia + "\n" +
                "}";
    }
}
