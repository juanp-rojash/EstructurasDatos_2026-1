package com.example.Model;

public class Automovil extends Vehiculo{

    private String TipoTransmision;

    public Automovil(String tipoTransmision) {
        TipoTransmision = tipoTransmision;
    }

    public Automovil(String placa, String modelo, String color, String tipoTransmision) {
        super(placa, modelo, color);
        TipoTransmision = tipoTransmision;
    }

    @Override
    public String acelerar() {
        return "El automovil acelera";
    }

    @Override
    public String frenar() {
        return "El automovil frena";
    }
}
