package com.mycompany.proyecto_final.Entidades;

import java.io.Serializable;

public class Cajero extends Persona implements Serializable{

    public static final String ROL_ENTIDAD = "CAJERO";

    public static final String CAJERO_DB_TABLE = "CAJERO";
    public static final String CAJERO_DB_CODIGO= "codigo";
    public static final String CAJERO_DB_NOMBRE = "nombre";
    public static final String CAJERO_DB_TURNO = "turno";
    public static final String CAJERO_DB_DPI = "dpi";
    public static final String CAJERO_DB_DIRECCION = "direccion";
    public static final String CAJERO_DB_SEXO = "sexo";
    public static final String CAJERO_DB_ID_USUARIO = "id_USUARIO";

    private String turno;
    /**
     * Constructor vacio de la clase Cajero
     */
    public Cajero(){
        this.setRol(ROL_ENTIDAD);
    }
    public Cajero(Long codigo, String password,String dpi, String nombre, String sexo,String direccion, String turno){
        super(codigo, password, ROL_ENTIDAD, nombre, dpi, sexo, direccion);
        this.turno=turno;
    }
    /**
     * Retorno del tipo de turno
     * @return
     */
    public String getTurno() {
        return turno;
    }
    
    /**
     * Asignacion del tipo de turno
     * @param turno
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }
    @Override
    public String toString() {
        return "Cajero{"+"codigo=" + this.getCodigo() + ", password=" + this.getPassword() + ", rol=" + this.getRol() +", nombre=" + this.getNombre() + ", dpi=" + this.getDpi() + ", sexo=" + this.getSexo() + ", direccion=" + this.getDireccion() + ", turno=" + turno + '}';
    }
}
