package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;

public class ModelUsuarioSistema {
    private static Connection connection = ConnectDB.getInstance();

    private final String BUSCAR_USUARIO_SISTEMA = "SELECT * FROM " + UsuarioDeSistema.USUARIO_SISTEMA_DB_TABLE + " WHERE " + UsuarioDeSistema.USUARIO_SISTEMA_DB_CODIGO_USUARIO + " = ? AND "+UsuarioDeSistema.USUARIO_SISTEMA_DB_PASSWORD +"= ?";

    /**
     * CONSTRUCTOR VACIO DE MODEL USUARIO DE SISTEMA
     */
    public ModelUsuarioSistema(){

    }
    public UsuarioDeSistema accesoDeUsuario(String user, String pass) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_USUARIO_SISTEMA);
        preSt.setString(1,user);
        preSt.setString(2,pass);
        ResultSet result = preSt.executeQuery();
        while (result.next()){
            return new UsuarioDeSistema(
                result.getLong(UsuarioDeSistema.USUARIO_SISTEMA_DB_CODIGO_USUARIO),
                null,
                result.getString(UsuarioDeSistema.USUARIO_SISTEMA_DB_ROL),
                result.getLong(UsuarioDeSistema.USUARIO_SISTEMA_DB_ID)
            );
        }
        return null;
    }
}
