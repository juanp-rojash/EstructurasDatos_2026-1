package com.example;


import com.example.Api.Servicio;
import com.example.Model.Peleador;
import com.example.Model.Pokemon;
import com.example.Service.ConnectionApi;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        ConnectionApi<Peleador> apiDragonBall = new ConnectionApi<>(
                "https://dragonball-api.com/api/characters",
                "items",
                Peleador.class
        );

        List<Peleador> ZFighters = apiDragonBall.obtenerData();

        System.out.println(" ENFRENTAMIENTOS: ");

        System.out.println(Servicio.crearTorneo(ZFighters));

        System.out.println(" RASTREADOR DE PODER (> 1.000.000) ");

        System.out.println(Servicio.rastrearPoder(ZFighters));



    }

}