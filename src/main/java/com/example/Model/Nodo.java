package com.example.Model;

public class Nodo implements Comparable<Nodo> {

    private int Id;
    private int Carga;

    public Nodo(int id, int carga) {
        Id = id;
        Carga = carga;
    }

    public int getId() {
        return Id;
    }

    public int getCarga() {
        return Carga;
    }

    public void acumularCarga(int carga){

        Carga += carga;

    }

    @Override
    public int compareTo(Nodo otro){

        int resultado = 0;

        resultado = Integer.compare(Carga, otro.getCarga());

        if (resultado != 0) return resultado;

        resultado = Integer.compare(Id, otro.getId());

        return resultado;

    }

    @Override
    public String toString(){

        String mensaje = "[ " + Id + " | " + Carga + " ]";

        return  mensaje;

    }
}
