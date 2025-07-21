package com.techlab.pedidos;

import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.productos.Producto;
import com.techlab.repositorios.PedidoRepository;
import com.techlab.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Pedido crearPedido(Pedido pedido) {
        for (LineaPedido linea : pedido.getLineas()) {
            Producto producto = productoRepository.findById(linea.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            if (producto.getStock() < linea.getCantidad()) {
                throw new StockInsuficienteException("Stock insuficiente para: " + producto.getNombre());
            }
            producto.setStock(producto.getStock() - linea.getCantidad());
            productoRepository.save(producto);
        }
        return pedidoRepository.save(pedido);
    }

    public java.util.List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}
