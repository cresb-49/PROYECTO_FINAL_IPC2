package com.mycompany.proyecto_final.Entidades;

import java.io.Serializable;
import java.sql.Date;

public class Transaccion implements Serializable{
    
    private static final long serialVersionUID = 3762617963630563510L;
    
    private String codigo;
    private String idCuenta;
    private Date fechaTransaccion;
    private String hora;
    private Double monto;
    private String idCajero;
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
    public Transaccion(String codigo,String idCuenta,Date fechaTransaccion, String hora,Double monto,String idCajero){
        this.codigo=codigo;
        this.idCuenta=idCuenta;
        this.fechaTransaccion=fechaTransaccion;
        this.hora=hora;
        this.monto=monto;
        this.idCajero=idCajero;   
    }
    /**
     * Retorna el codido de la transaccion
     * @return
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     * Asigna el codigo de la transaccion
     * @param codigo
     */
    public void setCodigo(String codigo) {
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
    public String getIdCajero() {
        return idCajero;
    }
    /**
     * Asigna el codigo del cajero asociado
     * @param idCajero
     */
    public void setIdCajero(String idCajero) {
        this.idCajero = idCajero;
    }
    /**
     * Retorna el codigo de cuenta asociada
     * @return
     */
    public String getIdCuenta() {
        return idCuenta;
    }
    /**
     * Asigna el codigo de cuenta asociada
     * @param idCuenta
     */
    public void setIdCuenta(String idCuenta) {
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
}
