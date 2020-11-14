package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.proyecto_final.AsociarCuenta.SolicitudAsociasion;

public class ModelSolicitudAsociacion {
    private static Connection connection = ConnectDB.getInstance();

    private final String REGISTRO_SOLICITUD = "INSERT INTO " + SolicitudAsociasion.SOLICITUD_DB_TABLE + " ("
            + SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE_SOLICITANTE + ","
            + SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE_PROPIETARIO + ","
            + SolicitudAsociasion.SOLICITUD_DB_CODIGO_CUENTA + "," + SolicitudAsociasion.SOLICITUD_DB_ESTADO + ","
            + SolicitudAsociasion.SOLICITUD_DB_INTENTO + ") VALUES (?,?,?,?,?)";

    private final String BUSQUEDA_SOLICITUD = "SELECT * FROM " + SolicitudAsociasion.SOLICITUD_DB_TABLE + " WHERE "
            + SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE_SOLICITANTE + " = ? AND "
            + SolicitudAsociasion.SOLICITUD_DB_CODIGO_CUENTA + " =?";

    private final String BUSQUEDA_SOLICITUD_BY_ID = "SELECT * FROM " + SolicitudAsociasion.SOLICITUD_DB_TABLE
            + " WHERE " + SolicitudAsociasion.SOLICITUD_DB_ID + " = ?";

    private final String BUSQUEDA_SOLICITUD_PROPIETARIO = "SELECT * FROM " + SolicitudAsociasion.SOLICITUD_DB_TABLE
            + " WHERE " + SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE_PROPIETARIO + " = ? AND "
            + SolicitudAsociasion.SOLICITUD_DB_ESTADO + " =?";

    private final String BUSQUEDA_SOLICITUD_PROPIETARIO_TODAS = "SELECT * FROM "
            + SolicitudAsociasion.SOLICITUD_DB_TABLE + " WHERE "
            + SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE_PROPIETARIO + " = ?";

    private final String BUSQUEDA_SOLICITUD_SOLICITANTE = "SELECT * FROM " + SolicitudAsociasion.SOLICITUD_DB_TABLE
            + " WHERE " + SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE_SOLICITANTE + " = ?";

    private final String ACTUALIZAR_SOLICITUD = "UPDATE " + SolicitudAsociasion.SOLICITUD_DB_TABLE + " SET "
            + SolicitudAsociasion.SOLICITUD_DB_ESTADO + "= ?," + SolicitudAsociasion.SOLICITUD_DB_INTENTO + " =? WHERE "
            + SolicitudAsociasion.SOLICITUD_DB_ID + " = ?";

    /**
     * CONTRUCTOR VACIO DE LA ENTIDAD
     */
    public ModelSolicitudAsociacion() {

    }

    /**
     * BUSCA UNA SOLICITUD DE ASOCIACION EN BASE AL CODIGO DE CUENTA Y CODIGO DEL
     * CLIENTE CLIENTE
     * 
     * @param idCuenta
     * @param idClienteSolicitante
     * @return
     * @throws SQLException
     */
    public SolicitudAsociasion BuscarSolicitud(String idCuenta, String idClienteSolicitante) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSQUEDA_SOLICITUD);
        preSt.setString(1, idClienteSolicitante);
        preSt.setString(2, idCuenta);
        ResultSet result = preSt.executeQuery();

        while (result.next()) {

            return new SolicitudAsociasion(result.getLong(SolicitudAsociasion.SOLICITUD_DB_ID),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE_SOLICITANTE),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE_PROPIETARIO),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CUENTA),
                    result.getString(SolicitudAsociasion.SOLICITUD_DB_ESTADO),
                    result.getInt(SolicitudAsociasion.SOLICITUD_DB_INTENTO));
        }
        return null;
    }

    /**
     * Retorna la solicitud en base al id de identifiacion
     * 
     * @param id
     * @return
     * @throws SQLException
     */
    public SolicitudAsociasion BuscarSolicitudID(String id) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSQUEDA_SOLICITUD_BY_ID);
        preSt.setString(1, id);
        ResultSet result = preSt.executeQuery();
        while (result.next()) {

            return new SolicitudAsociasion(result.getLong(SolicitudAsociasion.SOLICITUD_DB_ID),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE_SOLICITANTE),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE_PROPIETARIO),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CUENTA),
                    result.getString(SolicitudAsociasion.SOLICITUD_DB_ESTADO),
                    result.getInt(SolicitudAsociasion.SOLICITUD_DB_INTENTO));
        }
        return null;
    }

    /**
     * RETORNA LAS SOLICITUDES RECIBIDAS A UN USUARIO EN BASE A SU CODIGO Y ESTADO
     * DE ASOCIACION
     * 
     * @param idCuenta
     * @param idClientePropietario
     * @return
     * @throws SQLException
     */
    public List<SolicitudAsociasion> BuscarSolicitudPropietario(String idClientePropietario) throws SQLException {
        List<SolicitudAsociasion> solicitudes = new ArrayList<>();
        PreparedStatement preSt = connection.prepareStatement(BUSQUEDA_SOLICITUD_PROPIETARIO);
        preSt.setString(1, idClientePropietario);
        preSt.setString(2, SolicitudAsociasion.ESTADO_SOLICITUD_3);
        ResultSet result = preSt.executeQuery();

        while (result.next()) {

            solicitudes.add(new SolicitudAsociasion(result.getLong(SolicitudAsociasion.SOLICITUD_DB_ID),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE_SOLICITANTE),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE_PROPIETARIO),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CUENTA),
                    result.getString(SolicitudAsociasion.SOLICITUD_DB_ESTADO),
                    result.getInt(SolicitudAsociasion.SOLICITUD_DB_INTENTO)));
        }
        return solicitudes;
    }
    /**
     * RETRONA TODAS LAS SOLICITUDES RECIBIDAS
     * @param idClientePropietario
     * @return
     * @throws SQLException
     */
    public List<SolicitudAsociasion> BuscarSolicitudPropietarioTodas(String idClientePropietario) throws SQLException {
        List<SolicitudAsociasion> solicitudes = new ArrayList<>();
        PreparedStatement preSt = connection.prepareStatement(BUSQUEDA_SOLICITUD_PROPIETARIO_TODAS);
        preSt.setString(1, idClientePropietario);
        ResultSet result = preSt.executeQuery();
        while (result.next()) {

            solicitudes.add(new SolicitudAsociasion(result.getLong(SolicitudAsociasion.SOLICITUD_DB_ID),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE_SOLICITANTE),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE_PROPIETARIO),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CUENTA),
                    result.getString(SolicitudAsociasion.SOLICITUD_DB_ESTADO),
                    result.getInt(SolicitudAsociasion.SOLICITUD_DB_INTENTO)));
        }
        return solicitudes;
    }

    /**
     * RETORNA LAS SOLICITUDES ENVIADAS POR UN CLIENTE MEDIANTE SU CODIGO
     * 
     * @param idCuenta
     * @param idClientePropietario
     * @return
     * @throws SQLException
     */
    public List<SolicitudAsociasion> BuscarSolicitudSolicitante(String idClienteSolicitante) throws SQLException {
        List<SolicitudAsociasion> solicitudes = new ArrayList<>();
        PreparedStatement preSt = connection.prepareStatement(BUSQUEDA_SOLICITUD_SOLICITANTE);
        preSt.setString(1, idClienteSolicitante);
        ResultSet result = preSt.executeQuery();

        while (result.next()) {

            solicitudes.add(new SolicitudAsociasion(result.getLong(SolicitudAsociasion.SOLICITUD_DB_ID),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE_SOLICITANTE),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE_PROPIETARIO),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CUENTA),
                    result.getString(SolicitudAsociasion.SOLICITUD_DB_ESTADO),
                    result.getInt(SolicitudAsociasion.SOLICITUD_DB_INTENTO)));
        }
        return solicitudes;
    }

    /**
     * REGISTRA LA SOLICITUD DE ASOCIACION DE CUENTA
     * 
     * @param solicitud
     * @return
     * @throws SQLException
     */
    public long RegistarSolicitud(SolicitudAsociasion solicitud) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(REGISTRO_SOLICITUD, Statement.RETURN_GENERATED_KEYS);

        preSt.setLong(1, solicitud.getIdClienteSolicitante());
        preSt.setLong(2, solicitud.getIdClientePropietario());
        preSt.setLong(3, solicitud.getIdCuenta());
        preSt.setString(4, solicitud.getEstado());
        preSt.setInt(5, solicitud.getIntento());

        preSt.executeUpdate();

        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        return -1;
    }

    /**
     * ACTUALIZA LA SOLICITUD DE ASOCIACION DE CUENTA
     * 
     * @param solicitudAsociasion
     * @throws SQLException
     */
    public void ActualizarSolicitud(SolicitudAsociasion solicitudAsociasion) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(ACTUALIZAR_SOLICITUD);

        preSt.setString(1, solicitudAsociasion.getEstado());
        preSt.setInt(2, solicitudAsociasion.getIntento());
        preSt.setLong(3, solicitudAsociasion.getId());

        preSt.executeUpdate();
    }

}
