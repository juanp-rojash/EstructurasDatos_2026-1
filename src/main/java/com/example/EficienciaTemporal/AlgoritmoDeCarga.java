package com.example.EficienciaTemporal;

import org.apache.logging.log4j.Logger;

import com.example.Util.PerformanceReporter;

import org.apache.logging.log4j.LogManager;

import java.util.stream.LongStream;

public class AlgoritmoDeCarga {

    private static final Logger logger = LogManager.getLogger( AlgoritmoDeCarga.class.getName() );

    private static final Logger loggerTiempos = LogManager.getLogger( "tiempos" );


    public static void fuerzaBrutaRaizCuadrada(long limiteOperacion) {
        
        long inicioTiempoProceso = 0;

        long finTiempoProceso = 0;
        
        long limite = limiteOperacion; 

        long cantidadPrimos = 0;

        double tiempoTotal = 0;

        int numeroLogicoNucleos = Runtime.getRuntime().availableProcessors();

        logger.info("Inicio de Prueba de Carga \n\t| Limite de proceso: " + limite + "\n\t| Numero de nucleos logicos: " + numeroLogicoNucleos);
        
        inicioTiempoProceso = System.currentTimeMillis();

        cantidadPrimos = LongStream.rangeClosed(2, limite)
                .parallel()
                .filter(AlgoritmoDeCarga::esPrimo)
                .count();

        finTiempoProceso = System.currentTimeMillis();

        tiempoTotal = (finTiempoProceso - inicioTiempoProceso) / 1000.0;

        loggerTiempos.info("\nTiempo de Ejecución [fuerzaBrutaRaizCuadrada] : " + tiempoTotal + " segundos");
        loggerTiempos.info("Rendimiento: " + Math.round(limite / tiempoTotal) + " operaciones/seg");

        logger.info("\n\n== Resultados ==\n -> Primos Encontrados = " + cantidadPrimos + "\n");
    }

    public static void multiplicacionMatrices(int cantidadEspacio) {

        int N = cantidadEspacio;

        double[][] A = new double[N][N];
        double[][] B = new double[N][N];
        double[][] C = new double[N][N];

        long inicioTiempoProceso = 0;

        long finTiempoProceso = 0;

        double tiempoTotal = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = Math.random();
                B[i][j] = Math.random();
            }
        }

        PerformanceReporter.medirPesoObjeto(A, "Matriz A" + "[" + N + "][" + N + "]");

        System.out.println("Iniciando multiplicación de matrices " + N + "x" + N + "...");

        PerformanceReporter.reportarMemoriaSistema();

        logger.info("Iniciando multiplicación de matrices " + N + "x" + N);

        inicioTiempoProceso = System.currentTimeMillis();

        // Algoritmo pesado O(n^3)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }

            PerformanceReporter.reportarMemoriaSistema();

        }

        PerformanceReporter.medirPesoObjeto(C, "Matriz C" + "[" + N + "][" + N + "]");

        finTiempoProceso = System.currentTimeMillis();

        tiempoTotal = (finTiempoProceso - inicioTiempoProceso) / 1000.0;

        // Imprimimos un valor aleatorio para evitar la eliminación de código muerto por el JIT
        System.out.println("Cálculo finalizado. Valor de prueba: " + C[N/2][N/2]);

        logger.info("Cálculo finalizado. Valor de prueba: " + C[N/2][N/2]);

        loggerTiempos.info("Tiempo de ejecución: " + tiempoTotal + " segundos");

    }

    private static boolean esPrimo(long numero) {
        
        if (numero <= 1) return false;
        
        for (long i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }

        return true;
    }

    
}
