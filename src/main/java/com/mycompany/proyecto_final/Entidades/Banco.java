package com.mycompany.proyecto_final.Entidades;

import java.util.ArrayList;

public class Banco {
    
    private ArrayList<Gerente> gerentes = new ArrayList<>();
    private ArrayList<Cajero> cajeros = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Transaccion> transaciones = new ArrayList<>();
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
    public ArrayList<Cajero> getCajeros() {
        return cajeros;
    }
    public void setCajeros(ArrayList<Cajero> cajeros) {
        this.cajeros = cajeros;
    }
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
    public ArrayList<Gerente> getGerentes() {
        return gerentes;
    }
    public void setGerentes(ArrayList<Gerente> gerentes) {
        this.gerentes = gerentes;
    }
    public ArrayList<Transaccion> getTransaciones() {
        return transaciones;
    }
    public void setTransaciones(ArrayList<Transaccion> transaciones) {
        this.transaciones = transaciones;
    }
}
