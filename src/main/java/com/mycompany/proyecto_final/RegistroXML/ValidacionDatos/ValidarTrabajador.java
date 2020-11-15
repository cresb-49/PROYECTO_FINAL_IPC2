package com.mycompany.proyecto_final.RegistroXML.ValidacionDatos;

import com.mycompany.proyecto_final.Entidades.Cajero;
import com.mycompany.proyecto_final.Entidades.Gerente;
import com.mycompany.proyecto_final.RegistroXML.ExprecionesRegulares.ExprecionesValidacion;

public class ValidarTrabajador {
    private ExprecionesValidacion expr = new ExprecionesValidacion();
    
    public ValidarTrabajador() {
        
    }
    
    public String validarCajero(Cajero cajero){
        String analisis ="";
        
        if(!this.expr.validarCodigoNumerico(cajero.getCodigo())){
            analisis = analisis +"\n - El cajero no tiene un codigo de identificacion valido solo deben ser digitos";
        }
        if(!this.expr.validarTurno(cajero.getTurno())){
            analisis = analisis +"\n - El cajero no tiene una refrencia de turno correcta debes ser MATUTINO ó VESPERTINO";
        }
        if(!this.expr.validarDPI(cajero.getDpi())){
            analisis = analisis +"\n - El cajero no tiene un numero de DPI valido deben ser 13 digitos";
        }
        if(!this.expr.validarSexo(cajero.getSexo())){
            analisis = analisis +"\n - El cajero no tiene un sexo correcto debe ser Masculino ó Femenino";
        }
        return analisis;
    }
    
    public String validarGerente(Gerente gerente){
        String analisis ="";
        
        if(!this.expr.validarCodigoNumerico(gerente.getCodigo())){
            analisis = analisis +"\n - El gerente no tiene un codigo de identificacion valido solo deben ser digitos";
        }
        if(!this.expr.validarTurno(gerente.getTurno())){
            analisis = analisis +"\n - El gerente no tiene una refrencia de turno correcta debes ser MATUTINO ó VESPERTINO";
        }
        if(!this.expr.validarDPI(gerente.getDpi())){
            analisis = analisis +"\n - El gerente no tiene un numero de DPI valido deben ser 13 digitos";
        }
        if(!this.expr.validarSexo(gerente.getSexo())){
            analisis = analisis +"\n - El gerente no tiene un sexo correcto debe ser Masculino ó Femenino";
        }
        return analisis;
    }
    
}
