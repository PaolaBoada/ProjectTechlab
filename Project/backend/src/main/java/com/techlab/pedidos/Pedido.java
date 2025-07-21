package com.techlab.pedidos;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date fecha = new Date();

    private String estado = "pendiente";

    @OneToMany(cascade = CascadeType.ALL)
    private List<LineaPedido> lineas = new ArrayList<>();

    public Pedido() {}

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
    }

    public double calcularTotal() {
        return lineas.stream().mapToDouble(LineaPedido::calcularSubtotal).sum();
    }

     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaPedido> lineas) {
        this.lineas = lineas;
        }   
    }