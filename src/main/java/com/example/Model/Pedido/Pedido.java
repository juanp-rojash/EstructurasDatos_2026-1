package com.example.Model.Pedido;

import com.example.Model.Cliente.Cliente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

public class Pedido {

    private static final Logger logger = LogManager.getLogger(Pedido.class.getName());

    private UUID Id;
    private String Descripcion;
    private double Precio;
    private Cliente cliente;
    private Date FechaCreacion;

    public Pedido(UUID id, Date fechaCreacion, String descripcion, double precio, Cliente cliente) {
        logger.info("Petición: Crear pedido con datos: Id={}, Fecha={}, Descripción='{}', Precio={}, Cliente={}", id, fechaCreacion, descripcion, precio, cliente);
        Id = id;
        FechaCreacion = fechaCreacion;
        Descripcion = descripcion;
        Precio = precio;
        this.cliente = cliente;
    }

    public UUID getId() { return Id; }
    public String getDescripcion() { return Descripcion; }
    public double getPrecio() { return Precio; }
    public Cliente getCliente() { return cliente; }
    public Date getFechaCreacion() { return FechaCreacion; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pedido pedido = (Pedido) obj;
        return Double.compare(pedido.Precio, Precio) == 0 &&
                Objects.equals(Id, pedido.Id) &&
                Objects.equals(Descripcion, pedido.Descripcion) &&
                Objects.equals(cliente, pedido.cliente) &&
                Objects.equals(FechaCreacion, pedido.FechaCreacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Descripcion, Precio, cliente, FechaCreacion);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "Id=" + Id +
                ", Descripcion='" + Descripcion + '\'' +
                ", Precio=" + Precio +
                ", cliente=" + (cliente != null ? cliente.toString() : "null") +
                ", FechaCreacion=" + FechaCreacion +
                '}';
    }

}
