package com.example.Model;

public class Camioneta extends Vehiculo{

    private String Combustion;

    public Camioneta(){
        super();

        Combustion = "";
    }

    public Camioneta(String placa, String modelo, String color , String combustion){

        super(placa, modelo, color);

        Combustion = combustion;

    }

    @Override
    public String acelerar(){

        String mensaje = "La camioneta acelera";

        return mensaje;

    }

    @Override
    public String frenar(){

        String mensaje = "La camioneta frena";

        return mensaje;

    }

}
