package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.proyecto_final.Entidades.Gerente;

public class ModelGerente {
    private static Connection connection = ConnectDB.getInstance();

    private final String BUSCAR_GERENTE = "SELECT * FROM " + Gerente.GERENTE_DB_TABLE + " WHERE "
            + Gerente.GERENTE_DB_CODIGO + " = ?";

    private static final String REGISTRAR_GERENTE_CREADO = "INSERT INTO " + Gerente.GERENTE_DB_TABLE + " ("
            + Gerente.GERENTE_DB_CODIGO + "," + Gerente.GERENTE_DB_NOMBRE + "," + Gerente.GERENTE_DB_TURNO + ","
            + Gerente.GERENTE_DB_DPI + "," + Gerente.GERENTE_DB_DIRECCION + "," + Gerente.GERENTE_DB_SEXO
            + ") VALUES (?,?,?,?,?,?)";

    private final String MODIFICAR_GERENTE = "UPDATE " + Gerente.GERENTE_DB_TABLE + " SET " + Gerente.GERENTE_DB_NOMBRE
            + "=?," + Gerente.GERENTE_DB_TURNO + "=?," + Gerente.GERENTE_DB_DPI + "=?," + Gerente.GERENTE_DB_DIRECCION
            + "=?," + Gerente.GERENTE_DB_SEXO + "=? WHERE " + Gerente.GERENTE_DB_CODIGO + " = ?";

    /**
     * 
     * @param gerente
     * @throws SQLException
     */
    public void RegistroGerente(Gerente gerente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(REGISTRAR_GERENTE_CREADO);

        preSt.setLong(1, gerente.getCodigo());
        preSt.setString(2, gerente.getNombre());
        preSt.setString(3, gerente.getTurno());
        preSt.setString(4, gerente.getDpi());
        preSt.setString(5, gerente.getDireccion());
        preSt.setString(6, gerente.getSexo());

        preSt.executeUpdate();
    }

    /**
     * OBTIENTE EL GERENTE EN BASE AL CODIGO
     * @param codigo
     * @return
     * @throws SQLException
     */
    public Gerente ObtenerGerente(String codigo) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_GERENTE);
        preSt.setString(1, codigo);
        ResultSet result = preSt.executeQuery();
        while (result.next()) {

            return new Gerente(result.getLong(Gerente.GERENTE_DB_CODIGO), result.getString(Gerente.GERENTE_DB_NOMBRE),
                    result.getString(Gerente.GERENTE_DB_TURNO), result.getString(Gerente.GERENTE_DB_DPI),
                    result.getString(Gerente.GERENTE_DB_DIRECCION), result.getString(Gerente.GERENTE_DB_SEXO), null);
        }
        return null;
    }

    /**
     * MODIFICA LOS DATOS DEL CAJERO INGRESADO
     * @param gerente
     * @throws SQLException
     */
    public void ModificarGerente(Gerente gerente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(MODIFICAR_GERENTE);

        preSt.setString(1, gerente.getNombre());
        preSt.setString(2, gerente.getTurno());
        preSt.setString(3, gerente.getDpi());
        preSt.setString(4, gerente.getDireccion());
        preSt.setString(5, gerente.getSexo());
        preSt.setLong(6, gerente.getCodigo());

        preSt.executeUpdate();
    }
}
