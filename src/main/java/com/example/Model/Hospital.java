package com.example.Model;

import java.util.Queue;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

import com.example.Configuration.LecturaArchivo;

public class Hospital {
    
    private Queue<Paciente> Pacientes;
    private Queue<Paciente> Uci;
    private Bag<String> InventarioMedicamentos;

    public Hospital(String rutaArchivo) throws Exception{

        try {
            Pacientes = LecturaArchivo.LecturaArchivoPacientes(rutaArchivo);
            Uci = new java.util.LinkedList<>();
            InventarioMedicamentos = new HashBag<>();

            ingresoSuministros();

        }
        catch (Exception e){
            throw new Exception("Error al cargar el hospital: " + e.getMessage());
        }

    }

    public boolean ingresoSuministros(){

        try {
            
            int cantidadAleatoriaVacunaA = (int) (Math.random() * 10) + 1;
            int cantidadAleatoriaSueroB = (int) (Math.random() * 10) + 1;
            int cantidadAleatoriaTanqueOxigeno = (int) (Math.random() * 10) + 1;

            if (InventarioMedicamentos == null)
                InventarioMedicamentos = new HashBag<>();

            InventarioMedicamentos.add("Vacuna A", cantidadAleatoriaVacunaA);
            InventarioMedicamentos.add("Suero B", cantidadAleatoriaSueroB);
            InventarioMedicamentos.add("Tanque Oxigeno", cantidadAleatoriaTanqueOxigeno);

            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public boolean restaSuministros(Paciente paciente) {

        try {
            
            Genoma genomaPaciente = paciente.getGenoma();

            int cantidadVacunaA = genomaPaciente.getVacunaA();
            int cantidadSueroB = genomaPaciente.getSueroB();
            int cantidadTanqueOxigeno = genomaPaciente.getTanqueOxigeno();

            if (InventarioMedicamentos.getCount("Vacuna A") >= cantidadVacunaA &&
                InventarioMedicamentos.getCount("Suero B") >= cantidadSueroB &&
                InventarioMedicamentos.getCount("Tanque Oxigeno") >= cantidadTanqueOxigeno) {
                
                InventarioMedicamentos.remove("Vacuna A", cantidadVacunaA);
                InventarioMedicamentos.remove("Suero B", cantidadSueroB);
                InventarioMedicamentos.remove("Tanque Oxigeno", cantidadTanqueOxigeno);

                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

    }

}
