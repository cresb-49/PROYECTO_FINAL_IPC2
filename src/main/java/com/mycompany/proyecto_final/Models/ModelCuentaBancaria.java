package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.proyecto_final.Conversiones.ConversionesVariables;
import com.mycompany.proyecto_final.Entidades.CuentaBancaria;

public class ModelCuentaBancaria {
    private static Connection connection = ConnectDB.getInstance();
    private ConversionesVariables conv = new ConversionesVariables();
    
    private final String CREAR_CUENTA = "INSERT INTO " + CuentaBancaria.CLIENTE_DB_TABLE + " ("
            + CuentaBancaria.CUENTA_DB_CODIGO_CLIENTE + "," + CuentaBancaria.CUENTA_DB_CREDITO + ","
            + CuentaBancaria.CUENTA_DB_FECHA_APERTURA + ") VALUES (?,?,?)";
    
    private final String REGISTAR_CUENTA_EXPORTADA = "INSERT INTO " + CuentaBancaria.CLIENTE_DB_TABLE + " ("+CuentaBancaria.CUENTA_DB_CODIGO+","
            + CuentaBancaria.CUENTA_DB_CODIGO_CLIENTE + "," + CuentaBancaria.CUENTA_DB_CREDITO + ","
            + CuentaBancaria.CUENTA_DB_FECHA_APERTURA + ") VALUES (?,?,?,?)";
    
    private final String BUSCAR_CUENTA = "SELECT * FROM " + CuentaBancaria.CLIENTE_DB_TABLE + " WHERE "
            + CuentaBancaria.CUENTA_DB_CODIGO + " = ?";

    private final String BUSCAR_CUENTAS_CLIENTE = "SELECT * FROM "+CuentaBancaria.CLIENTE_DB_TABLE+" WHERE "+CuentaBancaria.CUENTA_DB_CODIGO_CLIENTE+" = ?";

    private final String ACTUALIZAR_CUENTA = "UPDATE " + CuentaBancaria.CLIENTE_DB_TABLE + " SET "
            + CuentaBancaria.CUENTA_DB_CREDITO + " = ? WHERE " + CuentaBancaria.CUENTA_DB_CODIGO + " = ?";
    private final String CUENTA_CON_MAS_DINERO ="SELECT * FROM CUENTA WHERE "+CuentaBancaria.CUENTA_DB_CODIGO_CLIENTE+" = ? ORDER BY "+CuentaBancaria.CUENTA_DB_CREDITO+" DESC LIMIT 1";
    /**
     * CONTRUCTOR VACIO DE LA ENTIDAD CREAR CUENTA BANCARIA
     */
    public ModelCuentaBancaria() {

    }

    /**
     * CREA UNA CUENTA BANCARIA
     * 
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
     * RESGISTRA UNA CUENTA EXPORTDA
     * @param codigoCliente
     * @return
     * @throws SQLException 
     */
    public void RegistrarCuenta(CuentaBancaria cuenta) throws SQLException {
        java.time.LocalDate today = java.time.LocalDate.now();
        PreparedStatement preSt = connection.prepareStatement(REGISTAR_CUENTA_EXPORTADA);
        preSt.setLong(1, cuenta.getCodigo());
        preSt.setLong(2, cuenta.getIdCliente());
        preSt.setDouble(3, cuenta.getCredito());
        preSt.setDate(4, cuenta.getFechaApertura());

        preSt.executeUpdate();
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
     * RETORNA LAS CUENTAS BANCARIAS DE UN USUARIO EN ESPECIFICO
     * @param codigoCliente
     * @return List<CuentaBancaria>
     * @throws SQLException
     */
    public List<CuentaBancaria> BuscarCuentas(String codigoCliente) throws SQLException {
        List<CuentaBancaria> cuentas = new ArrayList<>();
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CUENTAS_CLIENTE);
        preSt.setString(1, codigoCliente);
        ResultSet result = preSt.executeQuery();
        while (result.next()) {
            cuentas.add(new CuentaBancaria(result.getLong(CuentaBancaria.CUENTA_DB_CODIGO),
                    result.getDate(CuentaBancaria.CUENTA_DB_FECHA_APERTURA),
                    result.getDouble(CuentaBancaria.CUENTA_DB_CREDITO),
                    result.getLong(CuentaBancaria.CUENTA_DB_CODIGO_CLIENTE)));
        }
        return cuentas;
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
    /**
     * RETORNA LA CUENTA CON MAS DINERO DE UN CLIENTE
     * @param codigoCliente
     * @return
     * @throws SQLException
     */
    public CuentaBancaria CuentaConMasDinero(String codigoCliente) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(CUENTA_CON_MAS_DINERO);
        preSt.setString(1, codigoCliente);
        ResultSet result = preSt.executeQuery();
        while (result.next()) {
            return new CuentaBancaria(result.getLong(CuentaBancaria.CUENTA_DB_CODIGO),
                    result.getDate(CuentaBancaria.CUENTA_DB_FECHA_APERTURA),
                    result.getDouble(CuentaBancaria.CUENTA_DB_CREDITO),
                    result.getLong(CuentaBancaria.CUENTA_DB_CODIGO_CLIENTE));
        }
        return null;
    }
}
