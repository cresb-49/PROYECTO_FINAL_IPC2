package com.mycompany.proyecto_final.Entidades;

public class Cajero extends Persona{

    private static final long serialVersionUID = 4573195179505144046L;

    private String turno;
    /**
     * Constructor vacio de la clase Cajero
     */
    public Cajero(){

    }
    public Cajero(String codigo, String password,String dpi, String nombre, String sexo,String direccion){
        super(codigo, password, "CAJERO", nombre, dpi, sexo, direccion);
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
        return super.toString();
    }
}
