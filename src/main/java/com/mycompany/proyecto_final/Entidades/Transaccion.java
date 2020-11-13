package com.mycompany.proyecto_final.Entidades;

import java.io.Serializable;
import java.sql.Date;

public class Transaccion implements Serializable{

    public static final String TRANSACCION_DB_TABLE = "TRANSACCION";
    public static final String TRANSACCION_DB_CODIGO = "codigo";
    public static final String TRANSACCION_DB_CODIGO_CUENTA = "codigo_CUENTA";
    public static final String TRANSACCION_DB_CODIGO_CAJERO = "codigo_CAJERO";
    public static final String TRANSACCION_DB_TIPO = "tipo";
    public static final String TRANSACCION_DB_FECHA = "fecha";
    public static final String TRANSACCION_DB_HORA = "hora";
    public static final String TRANSACCION_DB_MONTO = "monto";

    private Long codigo;
    private Long idCuenta;
    private Date fechaTransaccion;
    private String hora;
    private String tipo;
    private Double monto;
    private Long idCajero;
    /**
     * CONSTRUCTOR VACIO DEL OBJETO TRANSACCION
     */
    public Transaccion(){

    }
    /**
     * CONSTRUCTOR CON PARAMETROS PARA EL OBJETO TRANSACCION
     * @param codigo
     * @param idCuenta
     * @param fechaTransaccion
     * @param hora
     * @param monto
     * @param idCajero
     */
    public Transaccion(Long codigo,Long idCuenta,Date fechaTransaccion, String hora,Double monto,Long idCajero,String tipo){
        this.codigo=codigo;
        this.idCuenta=idCuenta;
        this.fechaTransaccion=fechaTransaccion;
        this.hora=hora;
        this.monto=monto;
        this.idCajero=idCajero;   
        this.tipo=tipo;
    }
    /**
     * Retorna el codido de la transaccion
     * @return
     */
    public Long getCodigo() {
        return codigo;
    }
    /**
     * Asigna el codigo de la transaccion
     * @param codigo
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    /**
     * Retorna la fecha de la transaccion
     * @return
     */
    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }
    /**
     * Asigna la fecha de la transaccion
     * @param fechaTransaccion
     */
    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }
    /**
     * Retorna la fecha de la transaccion
     * @return
     */
    public String getHora() {
        return hora;
    }
    /**
     * Asigna la fecha de la transaccion
     * @param hora
     */
    public void setHora(String hora) {
        this.hora = hora;
    }
    /**
     * Retorna el codigo del cajero asociado
     */
    public Long getIdCajero() {
        return idCajero;
    }
    /**
     * Asigna el codigo del cajero asociado
     * @param idCajero
     */
    public void setIdCajero(Long idCajero) {
        this.idCajero = idCajero;
    }
    /**
     * Retorna el codigo de cuenta asociada
     * @return
     */
    public Long getIdCuenta() {
        return idCuenta;
    }
    /**
     * Asigna el codigo de cuenta asociada
     * @param idCuenta
     */
    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }
    /**
     * Retorna el monto de la transaccion
     * @return
     */
    public Double getMonto() {
        return monto;
    }
    /**
     * Asigna el monto de la transaccion
     * @param monto
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }
    /**
     * Retrona el tipo de transaccion
     * @return 
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Asigna el tipo de transaccion
     * @param tipo 
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return "Transaccion{" + "codigo=" + codigo + ", idCuenta=" + idCuenta + ", fechaTransaccion=" + fechaTransaccion + ", hora=" + hora + ", monto=" + monto + ", idCajero=" + idCajero + ", tipo="+tipo+'}';
    }
    
}
