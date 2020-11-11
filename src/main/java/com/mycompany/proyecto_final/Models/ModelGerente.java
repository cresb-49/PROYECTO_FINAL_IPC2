package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mycompany.proyecto_final.Entidades.Gerente;

public class ModelGerente {
    private static Connection connection = ConnectDB.getInstance();
    private static final String REGISTRAR_GERENTE_CREADO = "INSERT INTO " + Gerente.GERENTE_DB_TABLE + " ("
            + Gerente.GERENTE_DB_CODIGO + "," + Gerente.GERENTE_DB_NOMBRE + "," + Gerente.GERENTE_DB_TURNO + ","
            + Gerente.GERENTE_DB_DPI + "," + Gerente.GERENTE_DB_DIRECCION + "," + Gerente.GERENTE_DB_SEXO
            + ") VALUES (?,?,?,?,?,?)";

    /**
     * 
     * @param gerente
     * @throws SQLException
     */
    public void RegistroGerenteCreado(Gerente gerente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(REGISTRAR_GERENTE_CREADO);

        preSt.setLong(1, gerente.getCodigo());
        preSt.setString(2, gerente.getNombre());
        preSt.setString(3, gerente.getTurno());
        preSt.setString(4, gerente.getDpi());
        preSt.setString(5, gerente.getDireccion());
        preSt.setString(6, gerente.getSexo());

        preSt.executeUpdate();
    }
}
