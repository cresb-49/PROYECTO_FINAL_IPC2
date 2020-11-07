package com.mycompany.proyecto_final.Entidades;

import java.io.Serializable;
import java.sql.Date;

/**
 * CuentaBancaria
 */
public class CuentaBancaria implements Serializable {
    
    private static final long serialVersionUID = -6441304557106789462L;
    
    public static final String CUENTA_DB_CODIGO = "codigo";
    public static final String CUENTA_DB_FECHA_APERTURA = "fecha_apertura";
    public static final String CUENTA_DB_CREDITO = "credito";

    private Long codigo;
    private Date fechaApertura;
    private Double credito;

    public CuentaBancaria() {

    }

    public CuentaBancaria(Long codigo, Date fechaApertura, Double credito) {
        this.codigo = codigo;
        this.fechaApertura = fechaApertura;
        this.credito = credito;
    }

    /**
     * Retorna el codigo de la cuenta
     * @return
     */
    public Long getCodigo() {
        return codigo;
    }
    /**
     * Asigna el codigo de la cuenta
     * @param codigo
     */
    public void setCodigo(Long codigo) {
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
        return "CuentaBancaria{" + "codigo=" + codigo + ", fechaApertura=" + fechaApertura + ", credito=" + credito + '}';
    }
}