package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.proyecto_final.Entidades.Transaccion;

public class ModelTransaccion {
    private static Connection connection = ConnectDB.getInstance();
    private final String REGISTRAR_TRANSACCION = "INSERT INTO TRANSACCION (codigo_CUENTA,codigo_CAJERO,fecha,hora,monto,tipo) VALUES (?,?,?,?,?,?)";
    private final String L5_TRANSACCIONES_MAYORES_YEAR = "SELECT * FROM TRANSACCION WHERE YEAR("
            + Transaccion.TRANSACCION_DB_FECHA + ") = ? AND " + Transaccion.TRANSACCION_DB_CODIGO_CUENTA
            + " = ? ORDER BY monto DESC LIMIT 15";

    private final String TRANSACCIONES_INTERVALO_DE_TIEMPO = "SELECT * FROM " + Transaccion.TRANSACCION_DB_TABLE
            + " WHERE " + Transaccion.TRANSACCION_DB_CODIGO_CUENTA + " = ? AND " + Transaccion.TRANSACCION_DB_FECHA
            + " BETWEEN ? AND ? ORDER BY " + Transaccion.TRANSACCION_DB_CODIGO + " ASC";

    private final String TRANSACCIONES_SEGUN_CAJERO_INTERVALO_TIEMPO = "SELECT * FROM TRANSACCION WHERE hora BETWEEN ? AND ? AND codigo_CAJERO = ? AND fecha = ? ORDER BY hora";

    /**
     * CONTRUCTOR VACIO DE LA ENTIDAD
     */
    public ModelTransaccion() {

    }

    /**
     * REGISTRA UNA TRANSACCION BANCARIA
     * 
     * @param transaccion
     * @return
     * @throws SQLException
     */
    public long RegistrarTransaccion(Transaccion transaccion) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(REGISTRAR_TRANSACCION, Statement.RETURN_GENERATED_KEYS);
        preSt.setLong(1, transaccion.getIdCuenta());
        preSt.setLong(2, transaccion.getIdCajero());
        preSt.setDate(3, transaccion.getFechaTransaccion());
        preSt.setString(4, transaccion.getHora());
        preSt.setDouble(5, transaccion.getMonto());
        preSt.setString(6, transaccion.getTipo());

        preSt.executeUpdate();

        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        return -1;
    }

    /**
     * RETRONA LAS 15 TRANSACCIONES MAS GRANDES DEL AÑO
     * 
     * @param cuenta
     * @return
     */
    public List<Transaccion> Ultimas15Transacciones(String cuenta) throws SQLException {
        List<Transaccion> transacciones = new ArrayList<>();
        PreparedStatement preSt = connection.prepareStatement(L5_TRANSACCIONES_MAYORES_YEAR);
        java.time.LocalDate today = java.time.LocalDate.now();

        preSt.setString(1, String.valueOf(today.getYear()));
        preSt.setString(2, String.valueOf(cuenta));

        ResultSet result = preSt.executeQuery();

        while (result.next()) {
            transacciones.add(new Transaccion(result.getLong(Transaccion.TRANSACCION_DB_CODIGO),
                    result.getLong(Transaccion.TRANSACCION_DB_CODIGO_CUENTA),
                    result.getDate(Transaccion.TRANSACCION_DB_FECHA), result.getString(Transaccion.TRANSACCION_DB_HORA),
                    result.getDouble(Transaccion.TRANSACCION_DB_MONTO),
                    result.getLong(Transaccion.TRANSACCION_DB_CODIGO_CAJERO),
                    result.getString(Transaccion.TRANSACCION_DB_TIPO)));
        }
        return transacciones;
    }
    /**
     * DEVUELBE LA TRANSACCIONES DESDE UNA FECHA HASTA LA ACTUALIDAD EN BASE AL CODIGO DE CUENTA
     * @param cuenta
     * @param limiteInferior
     * @return
     * @throws SQLException
     */
    public List<Transaccion> TransaccionesIntervaloDeTiempo(String cuenta,String limiteInferior) throws SQLException {
        List<Transaccion> transacciones = new ArrayList<>();
        PreparedStatement preSt = connection.prepareStatement(TRANSACCIONES_INTERVALO_DE_TIEMPO);
        java.time.LocalDate today = java.time.LocalDate.now();

        preSt.setString(1, String.valueOf(cuenta));
        preSt.setString(2,limiteInferior);
        preSt.setString(3, today.toString());

        ResultSet result = preSt.executeQuery();

        while (result.next()) {
            transacciones.add(new Transaccion(result.getLong(Transaccion.TRANSACCION_DB_CODIGO),
                    result.getLong(Transaccion.TRANSACCION_DB_CODIGO_CUENTA),
                    result.getDate(Transaccion.TRANSACCION_DB_FECHA), result.getString(Transaccion.TRANSACCION_DB_HORA),
                    result.getDouble(Transaccion.TRANSACCION_DB_MONTO),
                    result.getLong(Transaccion.TRANSACCION_DB_CODIGO_CAJERO),
                    result.getString(Transaccion.TRANSACCION_DB_TIPO)));
        }
        return transacciones;
    }
    
    /**
     * RETORNA LAS TRANSACCIONES POR CAJERO EN INTERVALO DE TIEMPO
     * @param codigoCajero
     * @param horaMenor
     * @param horaMayor
     * @return
     * @throws SQLException
     */
    public List<Transaccion> transaccionesPorCajeroIntervaloTiempo(String codigoCajero,String horaMenor, String horaMayor,String fecha) throws SQLException{
        List<Transaccion> transacciones = new ArrayList<>();
        PreparedStatement preSt = connection.prepareStatement(TRANSACCIONES_SEGUN_CAJERO_INTERVALO_TIEMPO);
        
        preSt.setString(1, horaMenor);
        preSt.setString(2, horaMayor);
        preSt.setString(3, codigoCajero);
        preSt.setString(4, fecha);

        ResultSet result = preSt.executeQuery();

        while (result.next()) {
            transacciones.add(new Transaccion(result.getLong(Transaccion.TRANSACCION_DB_CODIGO),
                    result.getLong(Transaccion.TRANSACCION_DB_CODIGO_CUENTA),
                    result.getDate(Transaccion.TRANSACCION_DB_FECHA), result.getString(Transaccion.TRANSACCION_DB_HORA),
                    result.getDouble(Transaccion.TRANSACCION_DB_MONTO),
                    result.getLong(Transaccion.TRANSACCION_DB_CODIGO_CAJERO),
                    result.getString(Transaccion.TRANSACCION_DB_TIPO)));
        }

        return transacciones;
    }
}
