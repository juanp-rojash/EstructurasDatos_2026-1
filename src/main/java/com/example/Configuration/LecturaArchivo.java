package com.example.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Queue;

import com.example.Model.Civil;
import com.example.Model.Medico;
import com.example.Model.Militar;
import com.example.Model.Paciente;

public class LecturaArchivo {
    
    public static Queue<Paciente> LecturaArchivoPacientes(String rutaArchivo) throws Exception{

        try{

            String linea = "";
            String categoria = "";

            String[] campos;

            Paciente paciente;

            Queue<Paciente> fila = new LinkedList<>();

            File archivo = new File(rutaArchivo);
            FileReader lectorArchivo = new FileReader(archivo);
            BufferedReader datos = new BufferedReader(lectorArchivo);

            while ((linea = datos.readLine()) != null) {
                campos= linea.split(",");
                if (campos.length == 5) {
                    
                    categoria = campos[1].strip().toUpperCase();

                    paciente = clasificarPaciente(categoria, campos);

                    fila.add(paciente);
                    
                }
            }

            datos.close();
            lectorArchivo.close();

            return fila;

        }
        catch (IOException io){
            throw new Exception("Error al leer el archivo: " + io.getMessage());
        }
        catch (Exception e){
            throw new Exception("Error: " + e.getMessage());
        }

    }

    private static Paciente clasificarPaciente(String categoria, String[] campos) throws Exception{

        Paciente paciente = null;

        switch (categoria){

            case "MILITAR": paciente = new Militar(Integer.parseInt(campos[0]), Integer.parseInt(campos[2]), Integer.parseInt(campos[3]), campos[4]); break;
            case "MEDICO": paciente = new Medico(Integer.parseInt(campos[0]), Integer.parseInt(campos[2]), Integer.parseInt(campos[3]), campos[4]); break;
            case "CIVIL": paciente = new Civil(Integer.parseInt(campos[0]), Integer.parseInt(campos[2]), Integer.parseInt(campos[3]), campos[4]); break;
            default: throw new Exception("Error en la categoría del paciente: " + categoria);

        }

        return paciente;
        
    }

}
