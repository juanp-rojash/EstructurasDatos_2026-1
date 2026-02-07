package com.example.Services.Api.Interface;

public interface IApi {

    String pedidosPorCliente(String identificacion);
    String pedidosPorFecha(java.sql.Date fecha);
    String pedidosPorRangoPrecio(double minimo, double maximo);

}
