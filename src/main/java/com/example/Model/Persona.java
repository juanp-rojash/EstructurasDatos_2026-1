package com.example.Model;

import java.util.Objects;

public class Persona {

    private String Nombre;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }


    private int Edad;

    public Persona(String nombre, int edad) throws  Exception{

        try{

            if (nombre == null || nombre.trim().isEmpty()){
                throw  new IllegalArgumentException("Nombre invalido");
            }

            if (edad <= 0 || edad >= 150){
                throw  new IllegalArgumentException("Edad invalida, fuera de rango");
            }

            Nombre = nombre;
            Edad = edad;

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public int aumentoEdad() throws Exception{

        try{

            Edad++;

            return Edad;

        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    @Override
    public String toString(){

        String mensaje = "Nombre: " + Nombre + "\nEdad: " + Edad;

        return mensaje;

    }

    @Override
    public boolean equals(Object obj){

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Persona paux = (Persona) obj;

        return Nombre.equals(paux.Nombre) && Edad == paux.Edad;

    }

    @Override
    public int hashCode(){

        return Objects.hash(Nombre, Edad);

    }
}
