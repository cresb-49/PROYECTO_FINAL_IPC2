package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mycompany.proyecto_final.Entidades.Transaccion;

public class ModelTransaccion {
    private static Connection connection = ConnectDB.getInstance();
    private final String REGISTRAR_TRANSACCION = "INSERT INTO TRANSACCION (codigo_CUENTA,codigo_CAJERO,fecha,hora,monto,tipo) VALUES (?,?,?,?,?,?)";
    /**
     * CONTRUCTOR VACIO DE LA ENTIDAD
     */
    public ModelTransaccion(){

    }

    /**
     * REGISTRA UNA TRANSACCION BANCARIA
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
}
