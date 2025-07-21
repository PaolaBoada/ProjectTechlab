package com.techlab.productos;

import com.techlab.excepciones.ProductoNoEncontradoException;
import com.techlab.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(int id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(id));
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizar(int id, Producto productoActualizado) {
        Producto existente = obtenerPorId(id);
        existente.setNombre(productoActualizado.getNombre());
        existente.setDescripcion(productoActualizado.getDescripcion());
        existente.setPrecio(productoActualizado.getPrecio());
        existente.setCategoria(productoActualizado.getCategoria());
        existente.setImagenUrl(productoActualizado.getImagenUrl());
        existente.setStock(productoActualizado.getStock());
        return productoRepository.save(existente);
    }

    public void eliminar(int id) {
        productoRepository.deleteById(id);
    }
}
