package com.example.Model;

import java.util.Objects;

import com.example.Model.Interface.ICicloMutacion;

public abstract class Paciente implements ICicloMutacion {

    protected int Id;
    protected int NivelInfeccion;
    protected int NivelSalud;
    protected Genoma TablaGenoma;

    protected int Prioridad;

    public Paciente(int id, int nivelInfeccion, int nivelSalud, String genoma){

        Id = id;
        NivelInfeccion = nivelInfeccion;
        NivelSalud = nivelSalud;
        TablaGenoma = new Genoma(genoma);
        Prioridad = -1;

    }

    public int getId() {
        return Id;
    }

    public int getNivelInfeccion() {
        return NivelInfeccion;
    }

    public int getNivelSalud() {
        return NivelSalud;
    }

    public Genoma getGenoma() {
        return TablaGenoma;
    }

    public int getPrioridad(){
        return Prioridad;
    }

    @Override
    public String toString() {

        String resultado = "Paciente: " + Id + "\n";
        resultado += "Nivel de Infección: " + NivelInfeccion + "\n";
        resultado += "Nivel de Salud: " + NivelSalud + "\n";
        resultado += "Genoma: \n";
        resultado += TablaGenoma.toString() + "\n";

        return resultado;

    }

    @Override
    public boolean equals (Object obj){

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Paciente paciente = (Paciente) obj;

        return Id == paciente.Id && NivelInfeccion == paciente.NivelInfeccion && NivelSalud == paciente.NivelSalud && TablaGenoma.equals(paciente.TablaGenoma);

    }

     @Override
    public int hashCode() {
        
        return Objects.hash(Id, NivelInfeccion, NivelSalud, TablaGenoma);

    }

}
