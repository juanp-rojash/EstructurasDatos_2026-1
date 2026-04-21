package com.example.Model;

public class Producto {

    private String Nombre;
    private int Stock;
    private int TasaVenta;
    private float TiempoAgotamiento;

    public Producto(String nombre, int stock, int tasaVenta) {
        Nombre = nombre;
        Stock = stock;
        TasaVenta = tasaVenta;
    }

    public String getNombre() {
        return Nombre;
    }

    public int getStock() {
        return Stock;
    }

    public int getTasaVenta() {
        return TasaVenta;
    }

    public float getTiempoAgotamiento() {
        return TiempoAgotamiento;
    }
}
