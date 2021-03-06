package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import org.apache.commons.codec.digest.DigestUtils;

public class ModelUsuarioSistema {
    private static Connection connection = ConnectDB.getInstance();

    private final String BUSCAR_USUARIO_SISTEMA_LOGIN = "SELECT * FROM " + UsuarioDeSistema.USUARIO_SISTEMA_DB_TABLE + " WHERE " + UsuarioDeSistema.USUARIO_SISTEMA_DB_CODIGO_USUARIO + " = ? AND "+UsuarioDeSistema.USUARIO_SISTEMA_DB_PASSWORD +"= ?";
    private final String BUSCAR_USUARIO_SISTEMA = "SELECT * FROM " + UsuarioDeSistema.USUARIO_SISTEMA_DB_TABLE + " WHERE " + UsuarioDeSistema.USUARIO_SISTEMA_DB_CODIGO_USUARIO + " = ?";
    private final String REGISTRAR_USUARIO_SISTEMA = "INSERT INTO "+UsuarioDeSistema.USUARIO_SISTEMA_DB_TABLE+" ("+UsuarioDeSistema.USUARIO_SISTEMA_DB_PASSWORD+","+UsuarioDeSistema.USUARIO_SISTEMA_DB_ROL+") VALUES (?,?)";
    private final String REGISTRAR_USUARIO_SISTEMA_EXPORTADO = "INSERT INTO "+UsuarioDeSistema.USUARIO_SISTEMA_DB_TABLE+" ("+UsuarioDeSistema.USUARIO_SISTEMA_DB_CODIGO_USUARIO+","+UsuarioDeSistema.USUARIO_SISTEMA_DB_PASSWORD+","+UsuarioDeSistema.USUARIO_SISTEMA_DB_ROL+") VALUES (?,?,?)";
    private final String ELIMINAR_USUARIO_SISTEMA = "DELETE FROM " + UsuarioDeSistema.USUARIO_SISTEMA_DB_TABLE+" WHERE " + UsuarioDeSistema.USUARIO_SISTEMA_DB_CODIGO_USUARIO + " = ?";
    private final String CONTAR_REGISTROS_SISTEMA = "SELECT COUNT("+UsuarioDeSistema.USUARIO_SISTEMA_DB_CODIGO_USUARIO+") FROM "+UsuarioDeSistema.USUARIO_SISTEMA_DB_TABLE;

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
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_USUARIO_SISTEMA_LOGIN);
        pass = DigestUtils.md5Hex(pass);
        preSt.setString(1,user);
        preSt.setString(2,pass);
        ResultSet result = preSt.executeQuery();
        while (result.next()){
            return new UsuarioDeSistema(
                result.getLong(UsuarioDeSistema.USUARIO_SISTEMA_DB_CODIGO_USUARIO),
                null,
                result.getString(UsuarioDeSistema.USUARIO_SISTEMA_DB_ROL)
            );
        }
        return null;
    }
    /**
     * RETORNA EL USUARIO ENE EL SISTEMA EN BASE AL CODIGO UNICO DE IDENTIFICACION
     * @param user
     * @param pass
     * @return
     * @throws SQLException 
     */
    public UsuarioDeSistema buscarUsuario(Long codigo) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_USUARIO_SISTEMA);
        preSt.setLong(1,codigo);
        ResultSet result = preSt.executeQuery();
        while (result.next()){
            return new UsuarioDeSistema(
                result.getLong(UsuarioDeSistema.USUARIO_SISTEMA_DB_CODIGO_USUARIO),
                null,
                result.getString(UsuarioDeSistema.USUARIO_SISTEMA_DB_ROL)
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

        preSt.close();
        result.close();

        return 0;
    }

    /**
     * REALIZA EL REGISTRO DE UN USUARIO DE SISTEMA
     * @param usuarioDeSistema
     * @return
     * @throws SQLException
     */
    public long RegistroUsuarioSistema(UsuarioDeSistema usuarioDeSistema) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(REGISTRAR_USUARIO_SISTEMA, Statement.RETURN_GENERATED_KEYS);
        String pass = DigestUtils.md5Hex(usuarioDeSistema.getPassword());
        preSt.setString(1, pass);
        preSt.setString(2, usuarioDeSistema.getRol());
        
        preSt.executeUpdate();

        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }

        return -1;
    }
    /**
     * REALIZA EL REGISTRO DE UN USUARIO DE SISTEMA EXPORTADO AL SISTEMA
     * @param usuarioDeSistema
     * @throws SQLException
     */
    public void RegistroUsuarioSistemaExportado(UsuarioDeSistema usuarioDeSistema) throws SQLException{
        PreparedStatement preSt = connection.prepareStatement(REGISTRAR_USUARIO_SISTEMA_EXPORTADO);
        String pass = DigestUtils.md5Hex(usuarioDeSistema.getPassword());
        preSt.setLong(1, usuarioDeSistema.getCodigo());
        preSt.setString(2, pass);
        preSt.setString(3, usuarioDeSistema.getRol());
        
        preSt.executeUpdate();
    }
    /**
     * ELIMINACION DE UN REGISTRO DE UN USUARIO SEGUN SU ID DE IDENTIFICACION
     * @param codigo
     * @throws SQLException
     */
    public void EliminarUsuarioSistema(Long codigo) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(ELIMINAR_USUARIO_SISTEMA);
        preSt.setLong(1, codigo);
        preSt.executeUpdate();

        preSt.close();
    }
}
