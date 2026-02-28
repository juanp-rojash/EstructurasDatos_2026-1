package com.example.Model.ListaEnlazadaSimple;

public class Node <T> {

    private T Data;
    private Node<T> Referencia;

    public Node(T data){

        Data = data;
        Referencia = null;

    }

    public Node<T> getReferencia() {
        return Referencia;
    }

    public void setReferencia(Node<T> referencia) {
        Referencia = referencia;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    @Override
    public String toString(){

        String mensaje = "";

        mensaje = "\t[ " + Data + " | -> ] " + Referencia;

        return mensaje;
    }

    // TODO: Impelemtar la sobrescritura del equals

}
