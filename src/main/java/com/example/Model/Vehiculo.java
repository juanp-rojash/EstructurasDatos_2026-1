package com.example.Model;

import com.example.Model.Interface.IConduccion;

public abstract class Vehiculo implements IConduccion {

    // Visibilidad TipoDato NombreAtributo
    private String Placa;
    private String Modelo;
    private String Color;

    // Constructor
    public Vehiculo(){

        Placa = "";
        Modelo = "";
        Color = "";

    }

    public Vehiculo(String placa, String modelo, String color){

        if(null == placa || placa.isEmpty() || placa.length() != 6){
            throw new IllegalArgumentException("Placa invalida " + (placa == null ? "Nulo" : placa));
        }

        Placa = placa;
        Modelo = modelo;
        Color = color;

    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }


}
