package com.mycompany.proyecto_final.Entidades;

import java.io.Serializable;

public class UsuarioDeSistema implements Serializable{

    private static final long serialVersionUID = 4050320114047821544L;
    
    private Long codigo;
    private String password;
    private String rol;
    /**
     * Constructor vacio
     */
    public UsuarioDeSistema(){

    }
    /**
     * CONSTRUCTOR CON PARAMETROS DE INICIALIZACION
     * @param codigo
     * @param password
     * @param rol
     */
    public UsuarioDeSistema(Long codigo, String password, String rol){
        this.codigo=codigo;
        this.password=password;
        this.rol=rol;
    }
    /**
     * Retorna el codigo del usuario
     */
    public Long getCodigo() {
        return codigo;
    }
    /**
     * Asigna el codigo del usuario
     * @param codigo
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    /**
     * Retrona el password del usuario
     * @return
     */
    public String getPassword() {
        return password;
    }
    /**
     * Asigna el password del usuario
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Retorna el rol del usuario
     * @return
     */
    public String getRol() {
        return rol;
    }
    /**
     * asigna el rol del usuario
     * @param rol
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
