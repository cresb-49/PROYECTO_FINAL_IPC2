package com.mycompany.proyecto_final.EntidadesEspeciales;

import com.mycompany.proyecto_final.Entidades.Cajero;

public class CajeroConNumeroDeTransacciones {
    private Cajero cajero;
    private int cantidad;
    public CajeroConNumeroDeTransacciones(){

    }
    public CajeroConNumeroDeTransacciones(Cajero cajero,int cantidad){
        this.cajero=cajero;
        this.cantidad=cantidad;
    }
    public Cajero getCajero() {
        return cajero;
    }
    public void setCajero(Cajero cajero) {
        this.cajero = cajero;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
