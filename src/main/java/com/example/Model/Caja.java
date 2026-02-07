package com.example.Model;

import java.util.UUID;

public class Caja < T , K > {

    private UUID Id;
    private T Contenido;
    private K Clave;

    public Caja(T contenido, K clave){

        Id = UUID.randomUUID();
        Contenido = contenido;
        Clave = clave;

    }

    public K getClave() {
        return Clave;
    }

    public T getContenido() {
        return Contenido;
    }

    public UUID getId() {
        return Id;
    }

    @Override
    public String toString(){

        return "ID: " + Id + " - Contenido: " + Contenido;

    }

}
