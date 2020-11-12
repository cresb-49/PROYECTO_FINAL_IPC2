package com.mycompany.proyecto_final.Transaccion;

import com.mycompany.proyecto_final.Entidades.CuentaBancaria;
import com.mycompany.proyecto_final.Entidades.Transaccion;

public class LogicaTransaccion {
    /**
     * CONTRUCTOR VACIO
     */
    public LogicaTransaccion(){

    }
    /**
     * REALIZA EL CREDTIO A UNA CUENTA SEGUN TRANSACCION
     * @param transaccion
     * @param cuenta
     */
    public void Credito(Transaccion transaccion,CuentaBancaria cuenta){
        Double credito = transaccion.getMonto()+cuenta.getCredito();
        cuenta.setCredito(credito);
    }
    /**
     * READLIZA EL DEBITO A UNA CUENTA SEGUN TRANSACCION
     * @param transaccion
     * @param cuenta
     */
    public void Debito(Transaccion transaccion,CuentaBancaria cuenta){
        Double credito = cuenta.getCredito() - transaccion.getMonto();
        cuenta.setCredito(credito);
    }

}
