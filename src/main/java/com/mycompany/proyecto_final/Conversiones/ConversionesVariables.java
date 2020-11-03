package com.mycompany.proyecto_final.Conversiones;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class ConversionesVariables {
    /**
     * CONSTRUCTOR VACIO
     */
    public ConversionesVariables(){
        
    }
    /**
     * Parcea una cadena String a Time con formato HH:mm
     * Si la cadena es valida devolvera un valor diferente de NULL
     * @param cadena
     * @return 
     */
    public Time stringToTime(String cadena){
        Time resultado=null;
        DateFormat formatoHora = new SimpleDateFormat("HH:mm");
        try {
            resultado = new Time(formatoHora.parse(cadena).getTime());
        } catch (Exception e) {
        }
        return resultado;
    }
    /**
     * Parcea una cadena String a Date con formato yyyy-MM-dd
     * Si la cadena es valida devolvera un valor diferende de NULL
     * @param cadena
     * @return 
     */
    public java.sql.Date stringToDate(String cadena){
        java.sql.Date resultado =null;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        try {
            resultado= new java.sql.Date(formatoFecha.parse(cadena).getTime());
        } catch (Exception e) {
        }
        return resultado;
    }
    /**
     * Parcea una cadena String a double
     * Si la cadena es valida devolvera un valor diferende de NULL
     * @param cadena
     * @return 
     */
    public Double stringToDouble(String cadena){
        Double resultado;
        resultado = null;
        try {
            resultado = Double.parseDouble(cadena);
        } catch (Exception e) {
        }
        return resultado;
    }
    /**
     * Parcea una cadena String a long
     * Si la cadena es valida devolvera un valor diferende de NULL
     * @param cadena
     * @return 
     */
    public Long stringToLong(String cadena){
        Long resultado = null;
        try {
            resultado=Long.parseLong(cadena);
        } catch (Exception e) {
        }
        return resultado;
    }
    /**
     * AJUSTA EL NOMBRE DEL FORMATO PARA EL GUARDADO EN LA BASE DE DATOS CON UN ESTANDAR
     * @param formato
     * @return 
     */
    public String ajusteFormatos(String formato){
        String temp = formato.toLowerCase();
        if(temp.equals("pdf")){
            return "PDF";
        }
        return "NULL";
    }
    
}
