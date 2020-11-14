package com.mycompany.proyecto_final.ApoyoCajero;

import java.util.ArrayList;
import java.util.List;
import com.mycompany.proyecto_final.Entidades.Transaccion;

public class BalanceTransacciones {
    private List<Transaccion> transaciones = new ArrayList<>();
    /**
     * CONTRUCTOR VACIO DE LA ENTIDAD 
     */
    public BalanceTransacciones(){

    }
    /**
     * CONTRUCTOR CON PARAMETROS
     * @param transaciones
     */
    public BalanceTransacciones(List<Transaccion> transaciones){
        this.transaciones = transaciones;
    }
    /**
     * Asigna la lista de transacciones
     * @param transaciones
     */
    public void setTransaciones(List<Transaccion> transaciones) {
        this.transaciones = transaciones;
    }
    /**
     * OBTIENE LOS DEPOSITOS
     * @return
     */
    public Double obtenerDepocitos(){
        double suma = 0;
        for(Transaccion transaccion: transaciones){
            if(transaccion.getTipo().equals("CREDITO")){
                suma = suma +transaccion.getMonto();
            }
        }
        return suma;
    }
    /**
     * OBTENER RETIROS
     */
    public Double obtenerRetiros(){
        double suma =0;
        for(Transaccion transaccion: transaciones){
            if(transaccion.getTipo().equals("DEBITO")){
                suma = suma +transaccion.getMonto();
            }
        }
        return suma;
    }
    /**
     * OBTENER BALANCE
     * @return
     */
    public Double obtenerBalance(){
        return (this.obtenerDepocitos())-(this.obtenerRetiros());
    }
}
