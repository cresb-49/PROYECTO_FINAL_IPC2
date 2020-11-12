package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.proyecto_final.Entidades.Cliente;

public class ModelCliente {
        private static Connection connection = ConnectDB.getInstance();

        private final String BUSCAR_CLIENTE = "SELECT * FROM " + Cliente.CLIENTE_DB_TABLE + " WHERE "
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
        private final String MODIFICAR_CLIENTE = "UPDATE "+Cliente.CLIENTE_DB_TABLE+" SET "+Cliente.CLIENTE_DB_NOMBRE+"=?,"+Cliente.CLIENTE_DB_BIRTH+"=?,"+Cliente.CLIENTE_DB_DPI+"=?,"+Cliente.CLIENTE_DB_DIRECCION+"=?,"+Cliente.CLIENTE_DB_SEXO+"=? WHERE "+Cliente.CLIENTE_DB_CODIGO+" = ?";

        /**
         * CONTRUCTOR VACIO DE LA ENTIDAD MDOEL CLIENTE
         */
        public ModelCliente() {

        }

        /**
         * REALIZA EL REGISTO DE UN CLIENTE EN EL SISTEMA
         * 
         * @param cliente
         * @throws SQLException
         */
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

        /**
         * OBTIENE EL CLIENTE EN BASE AL CODIGO DE BUSQUEDA
         * 
         * @param codigo
         * @return
         * @throws SQLException
         */
        public Cliente ObtenerCliente(String codigo) throws SQLException {
                PreparedStatement preSt = connection.prepareStatement(BUSCAR_CLIENTE);
                preSt.setString(1, codigo);
                ResultSet result = preSt.executeQuery();
                while (result.next()) {
                        return new Cliente(result.getLong(Cliente.CLIENTE_DB_CODIGO), null,
                                        result.getString(Cliente.CLIENTE_DB_NOMBRE),
                                        result.getString(Cliente.CLIENTE_DB_DPI),
                                        result.getString(Cliente.CLIENTE_DB_SEXO),
                                        result.getString(Cliente.CLIENTE_DB_DIRECCION),
                                        result.getDate(Cliente.CLIENTE_DB_BIRTH), null);
                }
                return null;
        }

        /**
         * MODIFICA EL CLIENTE SELECCIONADO
         * 
         * @param cliente
         * @throws SQLException
         */
        public void ModificarCliente(Cliente cliente) throws SQLException {
                PreparedStatement preSt = connection.prepareStatement(MODIFICAR_CLIENTE);

                preSt.setString(1, cliente.getNombre());
                preSt.setDate(2, cliente.getFechaNacimiento());
                preSt.setString(3, cliente.getDpi());
                preSt.setString(4, cliente.getDireccion());
                preSt.setString(5, cliente.getSexo());
                preSt.setLong(6, cliente.getCodigo());
                
                preSt.executeUpdate();
        }
}
