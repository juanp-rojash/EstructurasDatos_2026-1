package com.example.Services.Api;

import com.example.Configuration.Central;
import com.example.Model.Pedido.Pedido;
import com.example.Services.Api.Interface.IApi;
import com.google.gson.Gson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ApiImpl implements IApi {

    private static final Logger logger = LogManager.getLogger(ApiImpl.class.getName());

    private Central pedidosCentralizados;

    public ApiImpl(){

        pedidosCentralizados = Central.getInstance();

    }

    @Override
    public String pedidosPorCliente(String identificacion){
        logger.info("Petición: Consultar pedidos por cliente con identificación: {}", identificacion);
        Gson gson = new Gson();
        List<Pedido> pedidosFiltrados = pedidosCentralizados.getPedidos().stream()
            .filter(pedido -> pedido.getCliente() != null && pedido.getCliente().getIdentificacion().equals(identificacion))
            .toList();
        String respuesta = gson.toJson(pedidosFiltrados);
        logger.info("Respuesta: Pedidos encontrados para cliente {}: {}", identificacion, respuesta);
        return respuesta;
    }

    @Override
    public String pedidosPorFecha(java.sql.Date fecha) {
        logger.info("Petición: Consultar pedidos por fecha: {}", fecha);
        Gson gson = new Gson();
        List<Pedido> pedidosFiltrados = pedidosCentralizados.getPedidos().stream()
            .filter(pedido -> pedido.getFechaCreacion().equals(fecha))
            .toList();
        String respuesta = gson.toJson(pedidosFiltrados);
        logger.info("Respuesta: Pedidos encontrados para fecha {}: {}", fecha, respuesta);
        return respuesta;
    }

    @Override
        public String pedidosPorRangoPrecio(double minimo, double maximo) {
            logger.info("Petición: Consultar pedidos por rango de precio: mínimo={}, máximo={}", minimo, maximo);
            Gson gson = new Gson();
            List<Pedido> pedidosFiltrados = pedidosCentralizados.getPedidos().stream()
                .filter(pedido -> pedido.getPrecio() >= minimo && pedido.getPrecio() <= maximo)
                .toList();
            String respuesta = gson.toJson(pedidosFiltrados);
            logger.info("Respuesta: Pedidos encontrados en rango de precio {}-{}: {}", minimo, maximo, respuesta);
            return respuesta;
        }

}
