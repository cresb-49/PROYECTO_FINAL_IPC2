/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_final.RegistroXML.ValidacionDatos;

import com.mycompany.proyecto_final.Entidades.Cajero;
import com.mycompany.proyecto_final.Entidades.Transaccion;
import com.mycompany.proyecto_final.RegistroXML.ExprecionesRegulares.ExprecionesValidacion;

/**
 *
 * @author carlo
 */
public class ValidarTransaccion {

    private ExprecionesValidacion expr = new ExprecionesValidacion();

    public ValidarTransaccion() {
    }
    
    public String validarTransaccion(Transaccion transaccion){
        String analisis ="";
        
        if(!this.expr.validarCodigoNumerico(transaccion.getCodigo())){
            analisis = analisis +"\n - La transaccion no tiene un codigo de identificacion valido solo deben ser digitos";
        }
        if(!this.expr.validarCodigoNumerico(transaccion.getIdCuenta())){
            analisis = analisis +"\n - La transaccion no tiene un codigo de identificacion valido para la Cuenta solo deben ser digitos";
        }
        if(!this.expr.validarCodigoNumerico(transaccion.getIdCajero())){
            analisis = analisis +"\n - La transaccion no tiene un codigo de identificacion valido para el Cajero solo deben ser digitos";
        }
        if(!this.expr.validarTipoTransaccion(transaccion.getTipo())){
            analisis = analisis +"\n - La transaccion no tiene no tiene una descripcion de movimiento valido debe ser CREDITO Ó DEBITO";
        }
        if(!this.expr.validarCosto(transaccion.getMonto())){
            analisis = analisis +"\n - La transaccion no tiene un monto correcto solo pueden ser descripciones numericas decimales o enteras";
        }
        if(transaccion.getFechaTransaccion()==null){
            analisis = analisis +"\n - La transaccion no tiene una fecha de referencia valida";
        }
        return analisis;
    }
}
