package com.example;


import com.example.Model.Automovil;
import com.example.Model.Camioneta;
import com.example.Model.Vehiculo;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        try {

            //Instanciar Clases

            Vehiculo camioneta1 = new Camioneta("YYY111", "2019", "Negro", "Diesel");
            Vehiculo automovil1 = new Automovil("YYY222", "2015", "Blanco", "x2");

            ArrayList<Vehiculo> taller = new ArrayList<>();

            taller.add(camioneta1);
            taller.add(automovil1);

            String coloresVehiculos = "";

            coloresVehiculos = taller.stream()
                    .map(v -> "Color: " + v.getColor() + "\n")
                    .reduce("", (a, b) -> a + "\n\n ===== | ==== \n\n" + b);

            /*for(Vehiculo v : taller){

                System.out.println(v.getPlaca());

            }*/

            System.out.println(coloresVehiculos);



        } catch (Exception e) {
            System.out.println(e);
        }

    }

}