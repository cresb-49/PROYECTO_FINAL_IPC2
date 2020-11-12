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

    private final String BUSCAR_CUENTA = "SELECT * FROM " + CuentaBancaria.CLIENTE_DB_TABLE + " WHERE "
            + CuentaBancaria.CUENTA_DB_CODIGO + " = ?";
    private final String ACTUALIZAR_CUENTA = "UPDATE " + CuentaBancaria.CLIENTE_DB_TABLE + " SET "
            + CuentaBancaria.CUENTA_DB_CREDITO + " = ? WHERE " + CuentaBancaria.CUENTA_DB_CODIGO + " = ?";

    /**
     * CONTRUCTOR VACIO DE LA ENTIDAD CREAR CUENTA BANCARIA
     */
    public ModelCuentaBancaria() {

    }

    /**
     * CREA UNA CUENTA BANCARIA
     * @param cuenta
     * @param codigoCliente
     * @return
     * @throws SQLException
     */
    public long CrearCuenta(String codigoCliente) throws SQLException {
        java.time.LocalDate today = java.time.LocalDate.now();
        PreparedStatement preSt = connection.prepareStatement(CREAR_CUENTA, Statement.RETURN_GENERATED_KEYS);
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

    /**
     * RETORNA LA CUENTA BANCARIA EN BASE AL CODIGO
     * 
     * @param codigoCuenta
     * @return
     * @throws SQLException
     */
    public CuentaBancaria BuscarCuenta(String codigoCuenta) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CUENTA);
        preSt.setString(1, codigoCuenta);
        ResultSet result = preSt.executeQuery();

        while (result.next()) {
            return new CuentaBancaria(result.getLong(CuentaBancaria.CUENTA_DB_CODIGO),
                    result.getDate(CuentaBancaria.CUENTA_DB_FECHA_APERTURA),
                    result.getDouble(CuentaBancaria.CUENTA_DB_CREDITO),
                    result.getLong(CuentaBancaria.CUENTA_DB_CODIGO_CLIENTE));
        }
        return null;
    }

    /**
     * ACTUALIZA EL CREDITO DE UNA CUENTA EN BASE AL CODIGO
     * 
     * @param cuenta
     * @throws SQLException
     */
    public void ActualizarCuenta(CuentaBancaria cuenta) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(ACTUALIZAR_CUENTA);
        preSt.setDouble(1, cuenta.getCredito());
        preSt.setLong(2, cuenta.getCodigo());

        preSt.executeUpdate();
    }
}
