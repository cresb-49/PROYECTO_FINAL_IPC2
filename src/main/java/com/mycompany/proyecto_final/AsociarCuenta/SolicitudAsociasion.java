package com.mycompany.proyecto_final.AsociarCuenta;

public class SolicitudAsociasion {

    private Long id;
    private Long idClienteSolicitante;
    private Long idClientePropietario;
    private Long idCuenta;
    private String estado;
    private int intento;

    public static final String SOLICITUD_DB_TABLE = "ASOCIACION";
    public static final String SOLICITUD_DB_ID = "id";
    public static final String SOLICITUD_DB_CODIGO_CLIENTE_SOLICITANTE = "codigo_CLIENTE_solicitante";
    public static final String SOLICITUD_DB_CODIGO_CLIENTE_PROPIETARIO = "codigo_CLIENTE_propietario";
    public static final String SOLICITUD_DB_CODIGO_CUENTA = "codigo_CUENTA";
    public static final String SOLICITUD_DB_ESTADO = "estado";
    public static final String SOLICITUD_DB_INTENTO = "intento";
    ///////
    public static final String ESTADO_SOLICITUD_1 = "ASOCIADO";
    public static final String ESTADO_SOLICITUD_2 = "RECHAZADO";
    public static final String ESTADO_SOLICITUD_3 = "ESPERA";


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
    public SolicitudAsociasion(Long id, Long idClienteSolicitante, Long idClientePropietario, Long idCuenta, String estado, int intento) {
        this.id = id;
        this.idClienteSolicitante = idClienteSolicitante;
        this.idClientePropietario = idClientePropietario;
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
     * Retorna el id del cliente solicintate de la asociacion
     */
    public Long getIdClienteSolicitante() {
        return idClienteSolicitante;
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
     * Asigna el id del cliente solicitante
     * @param idCliente
     */
    public void setIdClienteSolicitante(Long idClienteSolicitante) {
        this.idClienteSolicitante = idClienteSolicitante;
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
    /**
     * 
     * @return
     */
    public Long getIdClientePropietario() {
        return idClientePropietario;
    }
    /**
     * 
     * @param idClientePropietario
     */
    public void setIdClientePropietario(Long idClientePropietario) {
        this.idClientePropietario = idClientePropietario;
    }

    @Override
    public String toString() {
        return "SolicitudAsociasion{" + "id=" + id + ", idClienteSolicitante=" + idClienteSolicitante + ", idClientePropietario=" + idClientePropietario + ", idCuenta=" + idCuenta + ", estado=" + estado + ", intento=" + intento + '}';
    }
    
}
