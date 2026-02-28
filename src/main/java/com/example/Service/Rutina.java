package com.example.Service;

import org.apache.commons.collections4.Bag;

import com.example.Model.Genoma;
import com.example.Model.Paciente;

public class Rutina {

    public static Paciente analisisGenoma(Paciente pacienteFila, Paciente pacienteUci) {

        Paciente pacienteSeleccionado;

        Genoma genomaFila = pacienteFila.getGenoma();
        Genoma genomaUci = pacienteUci.getGenoma();

        int cantidadVacunaAFila = genomaFila.getVacunaA();
        int cantidadVacunaAUci = genomaUci.getVacunaA();

        int cantidadSueroBFila = genomaFila.getSueroB();
        int cantidadSueroBUci = genomaUci.getSueroB();

        int cantidadTanqueOxigenoFila = genomaFila.getTanqueOxigeno();
        int cantidadTanqueOxigenoUci = genomaUci.getTanqueOxigeno();

        int totalRecursosFila = cantidadVacunaAFila + cantidadSueroBFila + cantidadTanqueOxigenoFila;
        int totalRecursosUci = cantidadVacunaAUci + cantidadSueroBUci + cantidadTanqueOxigenoUci;

        if (totalRecursosFila < totalRecursosUci) {
            pacienteSeleccionado = pacienteFila;
        } 
        else if (totalRecursosFila == totalRecursosUci) {
            if(pacienteFila.getPrioridad() > pacienteUci.getPrioridad()){
                pacienteSeleccionado = pacienteFila;
            }
            else if (pacienteFila.getNivelInfeccion() < pacienteUci.getNivelInfeccion()){
                pacienteSeleccionado = pacienteUci;
            }
            else{
                pacienteSeleccionado = pacienteUci;
            }
        }
        else {
            pacienteSeleccionado = pacienteUci;
        }

        return pacienteSeleccionado;

    }

    public boolean analisisSuministros(Bag<String> inventarioMedicamentos, Paciente paciente) {

        Genoma genomaPaciente = paciente.getGenoma();

        int cantidadVacunaA = genomaPaciente.getVacunaA();
        int cantidadSueroB = genomaPaciente.getSueroB();
        int cantidadTanqueOxigeno = genomaPaciente.getTanqueOxigeno();

        if (inventarioMedicamentos.getCount("Vacuna A") >= cantidadVacunaA &&
            inventarioMedicamentos.getCount("Suero B") >= cantidadSueroB &&
            inventarioMedicamentos.getCount("Tanque Oxigeno") >= cantidadTanqueOxigeno) {
            return true;
        } else {
            return false;
        }

    }
    
}
