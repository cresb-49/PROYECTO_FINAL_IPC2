package com.mycompany.proyecto_final.EntidadesEspeciales;

import com.mycompany.proyecto_final.Entidades.Cliente;

public class ClienteSumaTransacciones {
    private Cliente cliente;
    private Double cantidad;
    /**
     * CONTRUCTOR VACIO DE LA ENTIDAD
     */
    public ClienteSumaTransacciones(){

    }

    public ClienteSumaTransacciones(Cliente cliente,Double cantidad){
        this.cliente = cliente;
        this.cantidad = cantidad;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
