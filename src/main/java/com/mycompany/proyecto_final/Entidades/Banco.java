package com.mycompany.proyecto_final.Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Banco implements Serializable{
    
    private List<Gerente> gerentes = new ArrayList<>();
    private List<Cajero> cajeros = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Transaccion> transaciones = new ArrayList<>();
    /**
     * Cosntructor vacio de la entidad banco
     */
    public Banco(){

    }
    /**
     * CONSTRUCTOR CON PARAMETROS DEL BANCO
     * @param gerentes
     * @param cajeros
     * @param clientes
     * @param transaciones
     */
    public Banco(ArrayList<Gerente> gerentes,ArrayList<Cajero> cajeros,ArrayList<Cliente> clientes,ArrayList<Transaccion> transaciones){
        this.gerentes=gerentes;
        this.cajeros=cajeros;
        this.clientes=clientes;
        this.transaciones=transaciones;
    }
    public List<Cajero> getCajeros() {
        return cajeros;
    }
    public void setCajeros(List<Cajero> cajeros) {
        this.cajeros = cajeros;
    }
    public List<Cliente> getClientes() {
        return clientes;
    }
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    public List<Gerente> getGerentes() {
        return gerentes;
    }
    public void setGerentes(List<Gerente> gerentes) {
        this.gerentes = gerentes;
    }
    public List<Transaccion> getTransaciones() {
        return transaciones;
    }
    public void setTransaciones(List<Transaccion> transaciones) {
        this.transaciones = transaciones;
    }
}
