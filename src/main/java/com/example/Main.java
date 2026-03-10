package com.example;

import com.example.EficienciaTemporal.AlgoritmoDeCarga;
import com.example.Model.Carro;
import com.example.Model.Motor;
import com.example.Util.PerformanceReporter;

public class Main {

    public static void main(String[] args) {

        long carga = 1_000_000L;

        AlgoritmoDeCarga.fuerzaBrutaRaizCuadrada(carga);

        AlgoritmoDeCarga.multiplicacionMatrices(500);

        Motor m = new Motor(10, 10);
        PerformanceReporter.medirPesoObjeto(m, "Motor");

        Carro c = new Carro(m, "TTT111");
        PerformanceReporter.medirPesoObjeto(c, "Carro");

    }

}