package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mycompany.proyecto_final.Entidades.Archivo;
import com.mycompany.proyecto_final.Entidades.Cliente;

public class ModelFotocopiaDPF {
    private static Connection connection = ConnectDB.getInstance();

    private final String REGISTRAR_PDF_DPI = "INSERT INTO "+Archivo.CLIENTE_DB_TABLE+" ("+Archivo.CLIENTE_DB_CODIGO_CLIENTE+","+Archivo.CLIENTE_DB_PDF+") VALUES (?,?)";

    /**
     * CONSTRUCTOR VACIO DEL MODEL
     */
    public ModelFotocopiaDPF(){

    }

    public void AgregarFotocopia(Cliente cliente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(REGISTRAR_PDF_DPI);

        preSt.setLong(1, cliente.getCodigo());
        preSt.setBlob(2, cliente.getFotocopiaDPI().getDatos());

        preSt.executeUpdate();
    }

}
