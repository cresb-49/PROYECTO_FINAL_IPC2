package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mycompany.proyecto_final.Conversiones.ConversionesVariables;
import com.mycompany.proyecto_final.Entidades.CuentaBancaria;

public class ModelCuentaBancaria {
    private static Connection connection = ConnectDB.getInstance();
    private ConversionesVariables conv = new ConversionesVariables();

    private final String CREAR_CUENTA = "INSERT INTO " + CuentaBancaria.CLIENTE_DB_TABLE + " ("
            + CuentaBancaria.CUENTA_DB_CODIGO_CLIENTE + "," + CuentaBancaria.CUENTA_DB_CREDITO + ","
            + CuentaBancaria.CUENTA_DB_FECHA_APERTURA + ") VALUES (?,?,?)";

    /**
     * CONTRUCTOR VACIO DE LA ENTIDAD CREAR CUENTA BANCARIA
     */
    public ModelCuentaBancaria() {

    }
    /**
     * 
     * @param cuenta
     * @param codigoCliente
     * @return
     * @throws SQLException
     */
    public long CrearCuenta(String codigoCliente) throws SQLException {
        java.time.LocalDate today = java.time.LocalDate.now();
        PreparedStatement preSt = connection.prepareStatement(CREAR_CUENTA,Statement.RETURN_GENERATED_KEYS);
        preSt.setString(1, codigoCliente);
        preSt.setDouble(2, 0);
        preSt.setDate(3, this.conv.stringToDate(today.toString()));

        preSt.executeUpdate();
        
        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }
        return -1;
    }
}
