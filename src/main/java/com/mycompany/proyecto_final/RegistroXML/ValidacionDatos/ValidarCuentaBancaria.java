
package com.mycompany.proyecto_final.RegistroXML.ValidacionDatos;

import com.mycompany.proyecto_final.Entidades.CuentaBancaria;
import com.mycompany.proyecto_final.RegistroXML.ExprecionesRegulares.ExprecionesValidacion;
import java.util.List;

public class ValidarCuentaBancaria {
    private ExprecionesValidacion expr = new ExprecionesValidacion();

    public ValidarCuentaBancaria() {
    }
    public String validarCuentaBancaria(CuentaBancaria cuentaBancaria){
        String analisis ="";
        if(!this.expr.validarCodigoNumerico(cuentaBancaria.getCodigo())){
            analisis = analisis +"\n - La Cuenta no tiene un codigo de identificacion valido solo deben ser digitos";
        }
        if(!this.expr.validarCosto(cuentaBancaria.getCredito())){
            analisis = analisis +"\n - La Cuenta no tiene un credito correcto solo deben ser cantidades decimales o enteras ";
        }
        if(cuentaBancaria.getFechaApertura()==null){
            analisis = analisis +"\n - La Cuenta no tiene una fecha valida de apertura";
        }
        return analisis;
    }
}
