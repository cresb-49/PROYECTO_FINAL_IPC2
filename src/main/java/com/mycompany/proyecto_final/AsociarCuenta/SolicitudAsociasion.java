package com.mycompany.proyecto_final.AsociarCuenta;

public class SolicitudAsociasion {

    private Long id;
    private Long idCliente;
    private Long idCuenta;
    private String estado;
    private int intento;

    public static final String SOLICITUD_DB_TABLE = "ASOCIACION";
    public static final String SOLICITUD_DB_ID = "id";
    public static final String SOLICITUD_DB_CODIGO_CLIENTE = "codigo_CLIENTE";
    public static final String SOLICITUD_DB_CODIGO_CUENTA = "codigo_CUENTA";
    public static final String SOLICITUD_DB_ESTADO = "estado";
    public static final String SOLICITUD_DB_INTENTO = "intento";


    /**
     * CONTRUCTOR VACIO DE LA ENTIDAD
     */
    public SolicitudAsociasion() {

    }
    /**
     * CONTRUCTOR CON PARAMETROS DE LA ENTIDAD
     * @param id
     * @param idCliente
     * @param idCuenta
     * @param estado
     * @param intento
     */
    public SolicitudAsociasion(Long id, Long idCliente, Long idCuenta, String estado, int intento) {
        this.id = id;
        this.idCliente = idCliente;
        this.idCuenta = idCuenta;
        this.estado = estado;
        this.intento = intento;
    }
    /**
     * Retorna el estado de la solicitud
     * @return
     */
    public String getEstado() {
        return estado;
    }
    /**
     * Asigna el estado de la solicitud 
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /**
     * Retorna el id de la asociacion
     * @return
     */
    public Long getId() {
        return id;
    }
    /**
     * Retorna el id del cliente de la asociacion
     */
    public Long getIdCliente() {
        return idCliente;
    }
    /**
     * Asigna el id de la asociacion
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Retorna el id de la cuenta
     */
    public Long getIdCuenta() {
        return idCuenta;
    }
    /**
     * Asigna el id del cliente
     * @param idCliente
     */
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    /**
     * Retorna el intento de la asociacion
     */
    public int getIntento() {
        return intento;
    }
    /**
     * Asgina el id de cuenta
     * @param idCuenta
     */
    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }
    /**
     * Asigna el intendo de la solicitud
     * @param intento
     */
    public void setIntento(int intento) {
        this.intento = intento;
    }
}
