package com.mycompany.proyecto_final.Entidades;

import java.io.Serializable;

public class Gerente extends Persona implements Serializable{
    
    public static final String ROL_ENTIDAD = "GERENTE";

    public static final String GERENTE_DB_TABLE = "GERENTE";
    public static final String GERENTE_DB_CODIGO= "codigo";
    public static final String GERENTE_DB_NOMBRE = "nombre";
    public static final String GERENTE_DB_TURNO = "turno";
    public static final String GERENTE_DB_DPI = "dpi";
    public static final String GERENTE_DB_DIRECCION = "direccion";
    public static final String GERENTE_DB_SEXO = "sexo";
    public static final String GERENTE_DB_ID_USUARIO = "id_USUARIO";

    private String turno;
    /**
     * Constructor vacio
     */
    public Gerente() {
        this.setRol(ROL_ENTIDAD);
    }
    /**
     * Constructor con atributos
     * @param codigo
     * @param nombre
     * @param turno
     * @param dpi
     * @param direccion
     * @param sexo
     * @param password
     */
    public Gerente(Long codigo, String nombre, String turno, String dpi, String direccion, String sexo,String password) {
        super(codigo, password,ROL_ENTIDAD, nombre, dpi, sexo, direccion);
        this.turno=turno;
    }
    /**
     * Retorna el tipo de turno del gerente
     * @return
     */
    public String getTurno() {
        return turno;
    }
    /**
     * Asigna el tipo de turno del gerente
     * @param turno
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }
    @Override
    public String toString() {
        return "Gerente{" +"codigo=" + this.getCodigo() + ", password=" + this.getPassword() + ", rol=" + this.getRol() +", nombre=" + this.getNombre() + ", dpi=" + this.getDpi() + ", sexo=" + this.getSexo() + ", direccion=" + this.getDireccion() +", turno=" + turno + '}';
    }
}
