package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import com.mycompany.proyecto_final.Entidades.Cliente;

public class ModelCliente {
    private static Connection connection = ConnectDB.getInstance();

    private final String BUSCAR_CLIENTE_ID_SISTEMA = "SELECT * FROM " + Cliente.CLIENTE_DB_TABLE + " WHERE " +Cliente.CLIENTE_DB_ID_USUARIO+ " = ?";
    private final String BUSCAR_CLIENTE_CODIGO = "SELECT * FROM " + Cliente.CLIENTE_DB_TABLE + " WHERE " +Cliente.CLIENTE_DB_CODIGO+ " = ?";
    private final String REGISTRAR_CLIENTE_EXPORTADO = "INSERT INTO " + Cliente.CLIENTE_DB_TABLE+" VALUES " + "("+Cliente.CLIENTE_DB_CODIGO+","+Cliente.CLIENTE_DB_ID_USUARIO+","+Cliente.CLIENTE_DB_NOMBRE+","+Cliente.CLIENTE_DB_BIRTH+","+Cliente.CLIENTE_DB_DPI+","+Cliente.CLIENTE_DB_DIRECCION+","+Cliente.CLIENTE_DB_SEXO+") VALUES (?,?,?,?,?,?,?)";
    private final String REGISTRAR_CLIENTE_CREADO = "INSERT INTO " + Cliente.CLIENTE_DB_TABLE+" VALUES " + "("+Cliente.CLIENTE_DB_ID_USUARIO+","+Cliente.CLIENTE_DB_NOMBRE+","+Cliente.CLIENTE_DB_BIRTH+","+Cliente.CLIENTE_DB_DPI+","+Cliente.CLIENTE_DB_DIRECCION+","+Cliente.CLIENTE_DB_SEXO+") VALUES (?,?,?,?,?,?)";

    public ModelCliente(){

    }
}
