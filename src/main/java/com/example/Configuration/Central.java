package com.example.Configuration;

import com.example.Model.Pedido.Pedido;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Central {

    private static Central Instancia;

    private ArrayList<Pedido> Pedidos;

    private Central (){

        Pedidos = new ArrayList<>();

    }

    public static Central getInstance(){

        if (Instancia == null){
            Instancia = new Central();
        }

        return Instancia;

    }

    public ArrayList<Pedido> getPedidos(){ return Pedidos; }

    private static final Logger logger = LogManager.getLogger(Central.class.getName());

    public void agregarPedido(Pedido pedido){
        logger.info("Petición: Agregar pedido a la central: {}", pedido);
        if (pedido == null) {
            logger.error("Respuesta: El pedido no puede ser nulo");
            throw new IllegalArgumentException("El pedido no puede ser nulo");
        }
        if (Pedidos == null) {
            logger.error("Respuesta: La lista de pedidos no está inicializada");
            throw new IllegalStateException("La lista de pedidos no está inicializada");
        }
        Pedidos.add(pedido);
        logger.info("Respuesta: Pedido agregado exitosamente a la central: {}", pedido);
    }

}
