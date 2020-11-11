package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mycompany.proyecto_final.Entidades.Cajero;

public class ModelCajero {
    private static Connection connection = ConnectDB.getInstance();
    private static final String REGISTRAR_CAJERO_CREADO = "INSERT INTO " + Cajero.CAJERO_DB_TABLE + " ("
            + Cajero.CAJERO_DB_CODIGO + "," + Cajero.CAJERO_DB_NOMBRE + "," + Cajero.CAJERO_DB_TURNO + ","
            + Cajero.CAJERO_DB_DPI + "," + Cajero.CAJERO_DB_DIRECCION + "," + Cajero.CAJERO_DB_SEXO
            + ") VALUES (?,?,?,?,?,?)";

    /**
     * 
     * @param cajero
     * @throws SQLException
     */
    public void RegistroCajeroCreado(Cajero cajero) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(REGISTRAR_CAJERO_CREADO);

        preSt.setLong(1, cajero.getCodigo());
        preSt.setString(2, cajero.getNombre());
        preSt.setString(3, cajero.getTurno());
        preSt.setString(4, cajero.getDpi());
        preSt.setString(5, cajero.getDireccion());
        preSt.setString(6, cajero.getSexo());

        preSt.executeUpdate();
    }
}
