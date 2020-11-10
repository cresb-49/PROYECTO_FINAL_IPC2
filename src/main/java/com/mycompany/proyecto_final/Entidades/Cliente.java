package com.mycompany.proyecto_final.Entidades;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona implements Serializable{

    public static final String CLIENTE_DB_TABLE = "CLIENTE";
    public static final String CLIENTE_DB_CODIGO= "codigo";
    public static final String CLIENTE_DB_ID_USUARIO = "id_USUARIO";
    public static final String CLIENTE_DB_NOMBRE = "nombre";
    public static final String CLIENTE_DB_BIRTH = "fecha_nacimiento";
    public static final String CLIENTE_DB_DPI = "dpi";
    public static final String CLIENTE_DB_DIRECCION = "direccion";
    public static final String CLIENTE_DB_SEXO = "sexo";

    public static final String ROL_ENTIDAD = "CLIENTE";

    private List<CuentaBancaria> cuentas = new ArrayList<CuentaBancaria>();

    private Date fechaNacimiento;
    private Archivo fotocopiaDPI = new Archivo();
    
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
     * @param nombre
     * @param dpi
     * @param sexo
     * @param direccion
     * @param fechaNacimiento
     * @param fotocopiaDPI
     */
    public Cliente(Long codigo,String password,String nombre,String dpi,String sexo,String direccion, Date fechaNacimiento, Archivo fotocopiaDPI){
        super(codigo, password, ROL_ENTIDAD, nombre, dpi, sexo, direccion);
        this.fechaNacimiento=fechaNacimiento;
        this.fotocopiaDPI=fotocopiaDPI;
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
    /**
     * Retorna la fotocopia de DPI
     * @return 
     */
    public Archivo getFotocopiaDPI() {
        return fotocopiaDPI;
    }
    /**
     * Asigna la fotocopia de DPI
     * @param fotocopiaDPI 
     */
    public void setFotocopiaDPI(Archivo fotocopiaDPI) {
        this.fotocopiaDPI = fotocopiaDPI;
    }
    
    @Override
    public String toString() {
        return "Cliente{" +"codigo=" + this.getCodigo() + ", password=" + this.getPassword() + ", rol=" + this.getRol() +"nombre=" + this.getNombre() + ", dpi=" + this.getDpi() + ", sexo=" + this.getSexo() + ", direccion=" + this.getDireccion() + "cuentas=" + cuentas + ", fechaNacimiento=" + fechaNacimiento + ", fotocopiaDPI="+fotocopiaDPI+'}';
    }
}
