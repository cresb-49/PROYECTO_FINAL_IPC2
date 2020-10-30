package com.mycompany.proyecto_final.Entidades;

import java.sql.Date;

public class Cliente extends Persona{

    private Date fechaNacimiento;
    /**
     * Constructor Vacio
     */
    public Cliente(){

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
    public Cliente(String codigo,String password,String nombre,String dpi,String sexo,String direccion, Date fechaNacimiento){
        super(codigo, password, "CLIENTE", nombre, dpi, sexo, direccion);
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
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
