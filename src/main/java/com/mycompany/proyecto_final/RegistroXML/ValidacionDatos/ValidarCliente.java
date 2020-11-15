/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_final.RegistroXML.ValidacionDatos;

import com.mycompany.proyecto_final.Entidades.Cliente;
import com.mycompany.proyecto_final.RegistroXML.ExprecionesRegulares.ExprecionesValidacion;

/**
 *
 * @author carlo
 */
public class ValidarCliente {

    private ExprecionesValidacion expr = new ExprecionesValidacion();

    public ValidarCliente() {
    }
    
    public String validarCliente(Cliente cliente){
        String analisis ="";
        if(!this.expr.validarCodigoNumerico(cliente.getCodigo())){
            analisis = analisis +"\n - El cliente no tiene un codigo de identificacion valido solo deben ser digitos";
        }
        if(!this.expr.validarDPI(cliente.getDpi())){
            analisis = analisis +"\n - El cliente no tiene un numero de DPI valido deben ser 13 digitos";
        }
        if(!this.expr.validarSexo(cliente.getSexo())){
            analisis = analisis +"\n - El cajero no tiene un sexo correcto debe ser Masculino ó Femenino";
        }
        if(cliente.getFotocopiaDPI().getDatos()==null){
            analisis = analisis +"\n - El cajero debe de tener una fotocopia de DPI para que sea registrado";
        }
        if(cliente.getFechaNacimiento()==null){
            analisis = analisis +"\n - El cajero debe de tener una fecha de nacimiento valida";
        }
        return analisis;
    }

}
