package com.example.Configuration;

import com.example.Model.Pedido.Pedido;

import java.util.ArrayList;

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

    public void agregarPedido(Pedido pedido){

        // TODO: Validar que el pedido no venga vacio
        // TODO: Validar que la lista ya se haya instanciado
        // TODO: Log de la accion agregar Pedido

        Pedidos.add(pedido);

    }

}
