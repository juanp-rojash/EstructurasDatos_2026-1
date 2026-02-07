package com.example;

import com.example.Configuration.Central;
import com.example.Model.Cliente.Cliente;
import com.example.Model.Pedido.Pedido;
import com.example.Services.Api.ApiImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private Central central;
    private ApiImpl api;
    private Cliente cliente;
    private Pedido pedido;

    @BeforeEach
    public void setUp() throws Exception {
        central = Central.getInstance();
        api = new ApiImpl();
        cliente = new Cliente("Juan", "Perez", "juan.perez@email.com", "12345");
        pedido = new Pedido(UUID.randomUUID(), Date.valueOf("2024-02-04"), "Pedido de prueba", 100.0, cliente);
        // Limpiar pedidos antes de cada test
        central.getPedidos().clear();
    }

    @Test
    public void testCrearClienteValido() throws Exception {
        Cliente c = new Cliente("Ana", "Gomez", "ana.gomez@email.com", "54321");
        assertEquals("Ana", c.getNombre());
        assertEquals("Gomez", c.getApellido());
        assertEquals("ana.gomez@email.com", c.getEmail());
        assertEquals("54321", c.getIdentificacion());
    }

    @Test
    public void testCrearClienteInvalido() {
        Exception exception = assertThrows(Exception.class, () -> {
            new Cliente("", "", "", "");
        });
        assertTrue(exception.getCause() instanceof IllegalArgumentException);
    }

    @Test
    public void testAgregarPedidoCentral() {
        central.agregarPedido(pedido);
        assertTrue(central.getPedidos().contains(pedido));
    }

    @Test
    public void testAgregarPedidoNuloCentral() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            central.agregarPedido(null);
        });
        assertEquals("El pedido no puede ser nulo", exception.getMessage());
    }

    @Test
    public void testPedidosPorCliente() {
        central.agregarPedido(pedido);
        String pedidosJson = api.pedidosPorCliente(cliente.getIdentificacion());
        assertTrue(pedidosJson.contains("Pedido de prueba"));
    }

    @Test
    public void testPedidosPorClienteSinPedidos() {
        String pedidosJson = api.pedidosPorCliente("no-existe");
        assertEquals("[]", pedidosJson);
    }

    @Test
    public void testPedidosPorFecha() {
        central.agregarPedido(pedido);
        String pedidosJson = api.pedidosPorFecha(Date.valueOf("2024-02-04"));
        assertTrue(pedidosJson.contains("Pedido de prueba"));
    }

    @Test
    public void testPedidosPorFechaSinPedidos() {
        String pedidosJson = api.pedidosPorFecha(Date.valueOf("2025-01-01"));
        assertEquals("[]", pedidosJson);
    }

    @Test
    public void testPedidosPorRangoPrecio() {
        central.agregarPedido(pedido);
        String pedidosJson = api.pedidosPorRangoPrecio(50.0, 150.0);
        assertTrue(pedidosJson.contains("Pedido de prueba"));
    }

    @Test
    public void testPedidosPorRangoPrecioSinPedidos() {
        String pedidosJson = api.pedidosPorRangoPrecio(200.0, 300.0);
        assertEquals("[]", pedidosJson);
    }
}
