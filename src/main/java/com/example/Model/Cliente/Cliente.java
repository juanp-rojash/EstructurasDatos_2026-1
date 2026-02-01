package com.example.Model.Cliente;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Cliente {

    // variable privada para unico uso en la clase
    // estatica para usarla en cualquier contexto del objeto
    // final: No modificable

    // Logger: Objeto de la libreria: org.apache.logging.log4j
    // LogManager: Objet de la libreria: org.apache.logging.log4j
    // Importante, tienen que ser compatibles, por eso se usa la misma libreria

    // getLogger([Clase]): Identifica la ubicación del paquete de la clase para escribirlo en los logs del log4j

    private static final Logger logger = LogManager.getLogger(Cliente.class.getName());

    private String Nombre;
    private String Apellido;
    private String Email;
    private String Identificacion;

    public Cliente(String nombre, String apellido, String email, String identificacion) throws Exception{

        try {

            if (nombre == null || nombre.isEmpty() ||
                apellido == null || apellido.isEmpty() ||
                email == null || email.isEmpty() ||
                identificacion == null || identificacion.isEmpty()){

                logger.error("Error en los datos para la creacion del cliente " +
                        "\nNombre: " + (nombre == null ? "Nulo" : nombre)  );

                // TODO: Completar el log

                throw new IllegalArgumentException("Datos invalidos");

            }

            logger.info("Creacion del cliente exitosa: ");

            // TODO: Completar el log con todos los parametros : ToString()

            Nombre = nombre;
            Apellido = apellido;
            Email = email;
            Identificacion = identificacion;

        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    public String getApellido() {
        return Apellido;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getIdentificacion() {
        return Identificacion;
    }

    public String getEmail() {
        return Email;
    }

    @Override
    public String toString(){

        String mensaje = "\nCliente: " + Nombre + "\nApellido: " + Apellido + "\nIdentificacion: " + Email;

        return  mensaje;

    }

    @Override
    public boolean equals(Object obj){

        if(this == obj) return true;
        if(!(obj instanceof Cliente))return false;

        Cliente c = (Cliente) obj;

        //TODO: Realizar la comparación de todos los atributos

        return true;

    }

    @Override
    public int hashCode(){

        return Objects.hash(Nombre, Apellido, Email, Identificacion);

    }

}
