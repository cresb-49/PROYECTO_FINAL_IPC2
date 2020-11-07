package com.mycompany.proyecto_final.Entidades;

import java.io.InputStream;
import java.io.Serializable;

public class Archivo implements Serializable{

    private static final long serialVersionUID = -7382395521714559110L;

    private String nombre;
    private InputStream datos;

    /**
     * CONTRUCTOR VACIO DE LA ENTIDAD ARCHIVO
     */
    public Archivo(){

    }
    /**
     * CONSTRUCTOR CON PARAMETROS
     * @param nombre
     * @param datos
     */
    public Archivo(String nombre, InputStream datos){
        this.nombre=nombre;
        this.datos=datos;
    }
    /**
     * RETORNA EL STREAM DE LOS DATOS DEL ARCHIVO
     * @return
     */
    public InputStream getDatos() {
        return datos;
    }
    /**
     * ASIGNA EL STREAM DE DATOS DEL ARCHIVO
     * @param datos
     */
    public void setDatos(InputStream datos) {
        this.datos = datos;
    }
    /**
     * RETORNA EN NOMBRE DEL ARCHIVO
     * @return
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * ASIGNA EL NOMBRE DE LOS ARCHIVO
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
