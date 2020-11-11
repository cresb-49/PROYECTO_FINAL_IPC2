package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mycompany.proyecto_final.Entidades.Cliente;

public class ModelCliente {
        private static Connection connection = ConnectDB.getInstance();

        private final String BUSCAR_CLIENTE_ID_SISTEMA = "SELECT * FROM " + Cliente.CLIENTE_DB_TABLE + " WHERE "
                        + Cliente.CLIENTE_DB_ID_USUARIO + " = ?";
        private final String BUSCAR_CLIENTE_CODIGO = "SELECT * FROM " + Cliente.CLIENTE_DB_TABLE + " WHERE "
                        + Cliente.CLIENTE_DB_CODIGO + " = ?";
        private final String REGISTRAR_CLIENTE_EXPORTADO = "INSERT INTO " + Cliente.CLIENTE_DB_TABLE + " ("
                        + Cliente.CLIENTE_DB_CODIGO + "," + Cliente.CLIENTE_DB_ID_USUARIO + ","
                        + Cliente.CLIENTE_DB_NOMBRE + "," + Cliente.CLIENTE_DB_BIRTH + "," + Cliente.CLIENTE_DB_DPI
                        + "," + Cliente.CLIENTE_DB_DIRECCION + "," + Cliente.CLIENTE_DB_SEXO
                        + ") VALUES (?,?,?,?,?,?,?)";
        private final String REGISTRAR_CLIENTE_CREADO = "INSERT INTO " + Cliente.CLIENTE_DB_TABLE + " ("
                        + Cliente.CLIENTE_DB_CODIGO + "," + Cliente.CLIENTE_DB_NOMBRE + "," + Cliente.CLIENTE_DB_BIRTH
                        + "," + Cliente.CLIENTE_DB_DPI + "," + Cliente.CLIENTE_DB_DIRECCION + ","
                        + Cliente.CLIENTE_DB_SEXO + ") VALUES (?,?,?,?,?,?)";

        /**
         * CONTRUCTOR VACIO DE LA ENTIDAD MDOEL CLIENTE
         */
        public ModelCliente() {

        }

        public void RegistroClienteCreado(Cliente cliente) throws SQLException {
                PreparedStatement preSt = connection.prepareStatement(REGISTRAR_CLIENTE_CREADO);

                preSt.setLong(1, cliente.getCodigo());
                preSt.setString(2, cliente.getNombre());
                preSt.setDate(3, cliente.getFechaNacimiento());
                preSt.setString(4, cliente.getDpi());
                preSt.setString(5, cliente.getDireccion());
                preSt.setString(6, cliente.getSexo());

                preSt.executeUpdate();
        }
}
