package com.example;


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

        System.out.println(ZFighters);

        for (Peleador p : ZFighters){

            System.out.println(p.name());

        }

        ConnectionApi<Pokemon> apiPokemon = new ConnectionApi<>(
                "https://pokeapi.co/api/v2/pokemon/",
                "results",
                Pokemon.class
        );

        List<Pokemon> equipoAsh = apiPokemon.obtenerData();

        for (Pokemon p : equipoAsh){

            System.out.println(p.name() + " - " + p.url());

        }

    }

}