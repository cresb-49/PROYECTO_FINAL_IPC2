package com.mycompany.proyecto_final.Entidades;

import java.io.InputStream;
import java.io.Serializable;

public class Archivo implements Serializable{

    public static final String CLIENTE_DB_TABLE = "PDF_DPI";
    public static final String CLIENTE_DB_ID = "id";
    public static final String CLIENTE_DB_CODIGO_CLIENTE = "codigo_CLIENTE";
    public static final String CLIENTE_DB_PDF = "pdf";

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
    
    @Override
    public String toString() {
        return "Archivo{" + "nombre=" + nombre + ", datos=" + datos + '}';
    }
}
