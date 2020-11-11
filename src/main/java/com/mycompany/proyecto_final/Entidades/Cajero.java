package com.mycompany.proyecto_final.Entidades;

import java.io.Serializable;

public class Cajero extends Persona implements Serializable{

    private static final long serialVersionUID = 4573195179505144046L;
    public static final String ROL_ENTIDAD = "CAJERO";
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
