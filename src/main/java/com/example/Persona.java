package com.example;

import java.sql.Timestamp;
import java.util.Objects;

public class Persona {

    private int Id;
    private String Nombre;
    private Timestamp FechaNacimiento;

    public Persona(int id, String nombre, Timestamp fechaNacimiento) {
        Id = id;
        Nombre = nombre;
        FechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public Timestamp getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Timestamp fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "\nPersona { \n" +
                " - Id=" + Id +
                "\n - Nombre='" + Nombre + '\'' +
                "\n - FechaNacimiento=" + FechaNacimiento +
                "\n}";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Persona persona)) return false;
        return Id == persona.Id && Objects.equals(Nombre, persona.Nombre) && Objects.equals(FechaNacimiento, persona.FechaNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Nombre, FechaNacimiento);
    }
}
