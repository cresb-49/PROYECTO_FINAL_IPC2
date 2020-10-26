package com.mycompany.proyecto_final.Entidades;

public class UsuarioDeSistema {
    private String codigo;
    private String password;
    private String rol;
    /**
     * Constructor vacio
     */
    public UsuarioDeSistema(){

    }
    /**
     * Retorna el codigo del usuario
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     * Asigna el codigo del usuario
     * @param codigo
     */
    public void setCodigo(String codigo) {
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
