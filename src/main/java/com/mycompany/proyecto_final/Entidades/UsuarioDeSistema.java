package com.mycompany.proyecto_final.Entidades;

import java.io.Serializable;

public class UsuarioDeSistema implements Serializable{

    private static final long serialVersionUID = 4050320114047821544L;
    
    public static final String USUARIO_SISTEMA_DB_TABLE = "USUARIO";
    public static final String USUARIO_SISTEMA_DB_ID = "id";
    public static final String USUARIO_SISTEMA_DB_CODIGO_USUARIO = "codigo_usuario";
    public static final String USUARIO_SISTEMA_DB_ROL = "rol";
    public static final String USUARIO_SISTEMA_DB_PASSWORD = "password";

    private Long codigo;
    private String password;
    private String rol;

    //VARIABLE EXCLUSIVA REFERNTE A BASE DE DATOS
    private Long id;

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
     * CONTRUCTOR PARA RECUPERACION DE USUARIO DE LA BASE DE DATOS
     * @param codigo
     * @param password
     * @param rol
     * @param id
     */
    public UsuarioDeSistema(Long codigo, String password, String rol, Long id){
        this.codigo=codigo;
        this.password=password;
        this.rol=rol;
        this.id=id;
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

    //METODOS UTILIZADOS PARA RECUPERAR ID DE LA BASE DE DATOS DE USUARIO
    /**
     * Retorna el id referente al usuario en la base de datos
     * @return
     */
    public Long getId() {
        return id;
    }
    /**
     * Asigna el id referente al usuario en el base de datos
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UsuarioDeSistema:"+"codigo= "+codigo+" ,password= "+password+" ,rol= "+rol+" ,id= "+id;
    }
}
