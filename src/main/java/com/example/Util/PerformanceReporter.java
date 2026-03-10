package com.example.Util;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import oshi.SystemInfo;

public class PerformanceReporter {
    
    private static final Logger loggerPerformance = LogManager.getLogger("performance");

    private static final SystemInfo si = new SystemInfo();
    
    // Medir peso de un objeto instanciado
    public static void medirPesoObjeto(Object objeto, String nombreObjeto) {

        // Mide el peso del objeto -> Analogia: Analiza el tamaño de la estructura de la casa
        long pesoBytes = ClassLayout.parseInstance(objeto).instanceSize();

        // Mide el peso del objeto y todas sus referencias -> Analogia: Analiza la estructura de la casa mas todo su contenido (muebles, electrodome.., etc)
        long pesoTotal = GraphLayout.parseInstance(objeto).totalSize();

        loggerPerformance.info(nombreObjeto + " pesa: " + String.format("%.5f",(pesoBytes / 1024.0)) + " KB");

        loggerPerformance.info(nombreObjeto + " ocupa: " + String.format("%.5f",(pesoTotal / 1024.0)) + " KB");

    }
    
    // Obtener estado de memoria del sistema
    public static void reportarMemoriaSistema() {

        Runtime runtime = Runtime.getRuntime();
        
        // runtime.totalMemory(): Devuelve el Heap actual asignado a la JVM (en bytes)
        // Dividir por 1024 × 1024: Convierte de bytes a MB
        // Qué hace: Cuánta memoria está reservada ahora para la JVM
        long totalHeapMB = runtime.totalMemory() / (1024L * 1024L);

        // runtime.freeMemory(): Espacio libre dentro del Heap asignado
        // totalMemory() - freeMemory(): Memoria ocupada por objetos activos
        // Qué hace: Calcula cuánta memoria de lo asignado está siendo usada realmente
        long usedHeapMB = (runtime.totalMemory() - runtime.freeMemory()) / (1024L * 1024L);
        
        // Qué hace: Te muestra el límite máximo que puede crecer la JVM
        long maxHeapMB = runtime.maxMemory() / (1024L * 1024L);
        
        // Qué hace: Calcula qué porcentaje del Heap asignado está en uso
        double porcentajeUso = (100.0 * (runtime.totalMemory() - runtime.freeMemory())) / runtime.totalMemory();
        
        loggerPerformance.info(String.format(
            "Memoria JVM - Total: %d MB | Usado: %d MB | Máximo: %d MB | Uso: %.2f%%",
            totalHeapMB, usedHeapMB, maxHeapMB, porcentajeUso));
    }

    

}
