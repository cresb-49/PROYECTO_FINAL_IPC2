package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mycompany.proyecto_final.AsociarCuenta.SolicitudAsociasion;

public class ModelSolicitudAsociacion {
    private static Connection connection = ConnectDB.getInstance();

    private final String REGISTRO_SOLICITUD = "INSERT INTO " + SolicitudAsociasion.SOLICITUD_DB_TABLE + " ("
            + SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE + "," + SolicitudAsociasion.SOLICITUD_DB_CODIGO_CUENTA
            + "," + SolicitudAsociasion.SOLICITUD_DB_ESTADO + "," + SolicitudAsociasion.SOLICITUD_DB_INTENTO
            + ") VALUES (?,?,?,?,?)";

    private final String BUSQUEDA_SOLICITUD = "SELECT * FROM " + SolicitudAsociasion.SOLICITUD_DB_TABLE + " WHERE "
            + SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE + " = ? AND "
            + SolicitudAsociasion.SOLICITUD_DB_CODIGO_CUENTA + " =? AND "+SolicitudAsociasion.SOLICITUD_DB_ESTADO+" =?";

    private final String ACTUALIZAR_SOLICITUD = "UPDATE " + SolicitudAsociasion.SOLICITUD_DB_TABLE + " SET ("
            + SolicitudAsociasion.SOLICITUD_DB_ESTADO + "," + SolicitudAsociasion.SOLICITUD_DB_INTENTO
            + ") VALUES (?,?) WHERE " + SolicitudAsociasion.SOLICITUD_DB_ID + " = ?";

    /**
     * CONTRUCTOR VACIO DE LA ENTIDAD
     */
    public ModelSolicitudAsociacion() {

    }

    /**
     * BUSCA UNA SOLICITUD DE ASOCIACION EN BASE AL CODIGO DE CUENTA Y CODIGO DE
     * CLIENTE
     * 
     * @param idCuenta
     * @param idCliente
     * @return
     * @throws SQLException
     */
    public SolicitudAsociasion BuscarSolicitud(String idCuenta, String idCliente,String estadoSolicitud) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSQUEDA_SOLICITUD);
        preSt.setString(1, idCliente);
        preSt.setString(2, idCuenta);
        preSt.setString(3, estadoSolicitud);
        ResultSet result = preSt.executeQuery();

        while (result.next()) {
            return new SolicitudAsociasion(result.getLong(SolicitudAsociasion.SOLICITUD_DB_ID),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CLIENTE),
                    result.getLong(SolicitudAsociasion.SOLICITUD_DB_CODIGO_CUENTA),
                    result.getString(SolicitudAsociasion.SOLICITUD_DB_ESTADO),
                    result.getInt(SolicitudAsociasion.SOLICITUD_DB_INTENTO));
        }
        return null;
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

        preSt.setLong(1, solicitud.getIdCliente());
        preSt.setLong(2, solicitud.getIdCuenta());
        preSt.setString(3, solicitud.getEstado());
        preSt.setInt(4, solicitud.getIntento());

        preSt.executeUpdate();

        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        return -1;
    }

    /**
     * ACTUALIZA LA SOLICITUD DE ASOCIACION DE CUENTA
     * @param solicitudAsociasion
     * @throws SQLException
     */
    public void ActualizarSolicitud(SolicitudAsociasion solicitudAsociasion) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(ACTUALIZAR_SOLICITUD);

        preSt.setString(1, solicitudAsociasion.getEstado());
        preSt.setInt(2, solicitudAsociasion.getIntento());

        preSt.executeUpdate();
    }

}
