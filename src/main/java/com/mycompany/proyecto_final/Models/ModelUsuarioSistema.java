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
    private final String REGISTRAR_USUARIO_SISTEMA = "INSERT INTO " + UsuarioDeSistema.USUARIO_SISTEMA_DB_TABLE+" VALUES " + "("+UsuarioDeSistema.USUARIO_SISTEMA_DB_CODIGO_USUARIO+","+UsuarioDeSistema.USUARIO_SISTEMA_DB_PASSWORD+","+UsuarioDeSistema.USUARIO_SISTEMA_DB_ROL+") VALUES (?,?,?)";
    private final String CONTAR_REGISTROS_SISTEMA = "SELECT COUNT("+UsuarioDeSistema.USUARIO_SISTEMA_DB_ID+") FROM "+UsuarioDeSistema.USUARIO_SISTEMA_DB_TABLE;

    /**
     * CONSTRUCTOR VACIO DE MODEL USUARIO DE SISTEMA
     */
    public ModelUsuarioSistema(){

    }
    /**
     * RETORNA EL USUARIO DEL SISTEMA CON EL CODIGO Y PASSWORD INGRESADOS
     * @param user
     * @param pass
     * @return 
     * @throws java.sql.SQLException
     */
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

    /**
     * RETORNA LA CANTIDAD DE USUARIO QUE HAY EN LA TABLA
     * @return
     * @throws SQLException
     */
    public int CantidadUsuarios() throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(CONTAR_REGISTROS_SISTEMA);
        ResultSet result = preSt.executeQuery();
        while (result.next()){
            return result.getInt(1);
        }
        return 0;
    }

    /**
     * REALIZA EL REGISTRO DE UN USUARIO DE SISTEMA
     * @param usuarioDeSistema
     * @return
     * @throws SQLException
     */
    public Long RegistroUsuarioSistema(UsuarioDeSistema usuarioDeSistema) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(REGISTRAR_USUARIO_SISTEMA, Statement.RETURN_GENERATED_KEYS);
        preSt.setLong(1, usuarioDeSistema.getCodigo());
        preSt.setString(2, usuarioDeSistema.getPassword());
        preSt.setString(3, usuarioDeSistema.getRol());
        
        preSt.executeUpdate();

        ResultSet result = preSt.getGeneratedKeys();
        if (result.next()) {
            return result.getLong(1);
        }
        return (long) -1;
    }
}
