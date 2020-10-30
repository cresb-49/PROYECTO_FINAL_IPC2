package com.mycompany.proyecto_final.Entidades;

import java.sql.Date;

/**
 * CuentaBancaria
 */
public class CuentaBancaria {

    public static final String CUENTA_DB_CODIGO = "codigo";
    public static final String CUENTA_DB_FECHA_APERTURA = "fecha_apertura";
    public static final String CUENTA_DB_CREDITO = "credito";

    private String codigo;
    private Date fechaApertura;
    private Double credito;

    public CuentaBancaria() {

    }

    public CuentaBancaria(String codigo, Date fechaApertura, Double credito) {
        this.codigo = codigo;
        this.fechaApertura = fechaApertura;
        this.credito = credito;
    }

    /**
     * Retorna el codigo de la cuenta
     * @return
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     * Asigna el codigo de la cuenta
     * @param codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    /**
     * Retorna el credito de la cuenta
     * @return
     */
    public Double getCredito() {
        return credito;
    }
    /**
     * Asigna el credito de la cuenta
     * @param credito
     */
    public void setCredito(Double credito) {
        this.credito = credito;
    }
    /**
     * Retorna fecha de apertura
     * @return
     */
    public Date getFechaApertura() {
        return fechaApertura;
    }
    /**
     * Asigna la fecha de apertura
     * @param fechaApertura
     */
    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}