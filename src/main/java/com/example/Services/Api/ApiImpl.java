package com.example.Services.Api;

import com.example.Configuration.Central;
import com.example.Model.Pedido.Pedido;
import com.example.Services.Api.Interface.IApi;
import com.google.gson.Gson;

import java.util.List;

public class ApiImpl implements IApi {

    private Central pedidosCentralizados;

    public ApiImpl(){

        pedidosCentralizados = Central.getInstance();

    }

    @Override
    public  String pedidosPorCliente(String identificacion){

        Gson gson = new Gson();

        String mensaje = "";

        List<Pedido> pedidosFiltrados = pedidosCentralizados.getPedidos().stream()
                .filter(cliente -> cliente.getId().equals(identificacion)).toList();

        mensaje = gson.toJson(pedidosFiltrados);

        return  mensaje;

        // TODO: Ver como funciona este metodo y la serializacion

    }

    // TODO : Terminar el contrato de la interfaz

}
