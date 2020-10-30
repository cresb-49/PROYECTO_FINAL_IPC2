package com.mycompany.proyecto_final.Entidades;

public class Gerente extends Persona {
    private String turno;
    /**
     * Constructor vacio
     */
    public Gerente() {
        this.setRol("Gerente");
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
    public Gerente(String codigo, String nombre, String turno, String dpi, String direccion, String sexo,String password) {
        super(codigo, password, "GERENTE", nombre, dpi, sexo, direccion);
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
        return super.toString();
    }
}
