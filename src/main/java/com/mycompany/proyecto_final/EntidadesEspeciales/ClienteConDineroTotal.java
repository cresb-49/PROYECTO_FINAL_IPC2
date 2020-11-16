package com.mycompany.proyecto_final.EntidadesEspeciales;

import com.mycompany.proyecto_final.Entidades.Cliente;

public class ClienteConDineroTotal {
    private Cliente cliente;
    private Double cantidad;

    public ClienteConDineroTotal() {
    }

    public ClienteConDineroTotal(Cliente cliente, Double cantidad) {
        this.cliente = cliente;
        this.cantidad = cantidad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ClienteConDineroTotal{" + "cliente=" + cliente + ", cantidad=" + cantidad + '}';
    }
    
}
