package com.techlab.excepciones;

public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(int id) {
        super("Producto con ID " + id + " no encontrado.");
    }
}
