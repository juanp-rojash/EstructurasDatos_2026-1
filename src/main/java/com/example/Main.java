package com.example;

import com.example.Configuration.Central;
import com.example.Model.Cliente.Cliente;
import com.example.Model.Pedido.Pedido;
import com.example.Services.Api.ApiImpl;
import com.example.Services.Api.Interface.IApi;

public class Main {

    public static void main(String[] args) {
        try {
            // Crear cliente
            Cliente cliente = new Cliente(
                    "Juan", "Pérez", "juan.perez@example.com", "123456");

            // Crear pedido
            Pedido pedido = new Pedido(
                    java.util.UUID.randomUUID(),
                    java.sql.Date.valueOf("2023-10-01"),
                    "Pedido de prueba",
                    100.50,
                    cliente);

            // Agregar pedido a la central
            Central central = Central.getInstance();
            central.agregarPedido(pedido);

            // Consultar pedidos por cliente
            IApi api = new ApiImpl();
            String pedidosPorCliente = api.pedidosPorCliente("123456");
            System.out.println("Pedidos por cliente: " + pedidosPorCliente);

            // Consultar pedidos por fecha
            String pedidosPorFecha = api.pedidosPorFecha(java.sql.Date.valueOf("2023-10-01"));
            System.out.println("Pedidos por fecha: " + pedidosPorFecha);

            // Consultar pedidos por rango de precio
            String pedidosPorRangoPrecio = api.pedidosPorRangoPrecio(50, 150);
            System.out.println("Pedidos por rango de precio: " + pedidosPorRangoPrecio);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}