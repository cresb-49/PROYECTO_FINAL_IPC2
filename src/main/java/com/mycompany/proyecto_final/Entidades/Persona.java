package com.mycompany.proyecto_final.Entidades;

public class Persona extends UsuarioDeSistema{
    
    private static final long serialVersionUID = -1110350536359505400L;

    private String nombre;
    private String dpi;
    private String sexo;
    private String direccion;
    /**
     * Constructor vacio
     */
    public Persona(){

    }
    /**
     * CONSTRECTOR CON PARAMETROS PARA INICIALIZACION DEL OBJETO
     * @param codigo
     * @param password
     * @param rol
     * @param nombre
     * @param dpi
     * @param sexo
     * @param direccion
     */
    public Persona(Long codigo, String password, String rol,String nombre, String dpi,String sexo,String direccion){
        super(codigo, password, rol);
        this.nombre=nombre;
        this.dpi=dpi;
        this.sexo=sexo;
        this.direccion=direccion;
    }

    /**
     * Retorna el nombre de la persona
     * @return
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Asigna el nombre de la persona
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Retona el dpi de la persona
     * @return
     */
    public String getDpi() {
        return dpi;
    }
    /**
     * Asigna el dpi de la persona
     * @param dpi
     */
    public void setDpi(String dpi) {
        this.dpi = dpi;
    }
    /**
     * Retornal el sexo de la persona
     * @return
     */
    public String getSexo() {
        return sexo;
    }
    /**
     * Asigna el sexo de la persona
     * @param sexo
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    /**
     * Retorna la direccion de la persona
     * @return
     */
    public String getDireccion() {
        return direccion;
    }
    /**
     * Asigna la direccion de la persona
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", dpi=" + dpi + ", sexo=" + sexo + ", direccion=" + direccion + '}';
    }
    
}
