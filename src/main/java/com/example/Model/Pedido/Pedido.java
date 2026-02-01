package com.example.Model.Pedido;

import com.example.Model.Cliente.Cliente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.UUID;

public class Pedido {

    private static final Logger logger = LogManager.getLogger(Pedido.class.getName());

    private UUID Id;
    private String Descripcion;
    private double Precio;
    private Cliente cliente;
    private Date FechaCreacion;

    public Pedido(UUID id, Date fechaCreacion, String descripcion, double precio, Cliente cliente) {
        Id = id;
        FechaCreacion = fechaCreacion;
        Descripcion = descripcion;
        Precio = precio;
        this.cliente = cliente;
    }

    public UUID getId() {
        return Id;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public double getPrecio() {
        return Precio;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public Date getFechaCreacion() {
        return FechaCreacion;
    }

    //TODO: Terminar implementación ADT: Herencia Object

}
