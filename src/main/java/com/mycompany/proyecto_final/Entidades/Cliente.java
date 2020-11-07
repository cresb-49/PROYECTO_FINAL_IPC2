package com.mycompany.proyecto_final.Entidades;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona{

    private static final long serialVersionUID = -4851497146888542151L;
    public static final String ROL_ENTIDAD = "CLIENTE";

    private List<CuentaBancaria> cuentas = new ArrayList<CuentaBancaria>();

    private Date fechaNacimiento;
    /**
     * Constructor Vacio
     */
    public Cliente(){
        this.setRol(ROL_ENTIDAD);
    }
    /**
     * Constructor con parametros
     * @param codigo
     * @param password
     * @param rol
     * @param nombre
     * @param dpi
     * @param sexo
     * @param direccion
     * @param fechaNacimiento
     */
    public Cliente(Long codigo,String password,String nombre,String dpi,String sexo,String direccion, Date fechaNacimiento){
        super(codigo, password, ROL_ENTIDAD, nombre, dpi, sexo, direccion);
        this.fechaNacimiento=fechaNacimiento;
    }
    /**
     * Retrona la fecha de nacimiento del cliente
     * @return
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    /**
     * Asigna la fecha de nacimiento al cliente
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    /**
     * Retorno de la cuentas bancarias
     * @return
     */
    public List<CuentaBancaria> getCuentas() {
        return cuentas;
    }
    /**
     * Asignacion de una lista de cuentas bancarias
     * @param cuentas
     */
    public void setCuentas(List<CuentaBancaria> cuentas) {
        this.cuentas = cuentas;
    }
    /**
     * Agrega un cuenta bancaria a la lista del cliente
     * @param cuenta
     */
    public void agregarCuenta(CuentaBancaria cuenta){
        this.cuentas.add(cuenta);
    }

    @Override
    public String toString() {
        return "Cliente{" +"codigo=" + this.getCodigo() + ", password=" + this.getPassword() + ", rol=" + this.getRol() +"nombre=" + this.getNombre() + ", dpi=" + this.getDpi() + ", sexo=" + this.getSexo() + ", direccion=" + this.getDireccion() + "cuentas=" + cuentas + ", fechaNacimiento=" + fechaNacimiento + '}';
    }
    
    
}
