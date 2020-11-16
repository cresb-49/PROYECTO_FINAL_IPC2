package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.proyecto_final.Entidades.Cajero;
import com.mycompany.proyecto_final.EntidadesEspeciales.CajeroConNumeroDeTransacciones;

public class ModelCajero {

    private static Connection connection = ConnectDB.getInstance();

    private final String BUSCAR_CAJERO = "SELECT * FROM " + Cajero.CAJERO_DB_TABLE + " WHERE "
            + Cajero.CAJERO_DB_CODIGO + " = ?";

    private static final String REGISTRAR_CAJERO_CREADO = "INSERT INTO " + Cajero.CAJERO_DB_TABLE + " ("
            + Cajero.CAJERO_DB_CODIGO + "," + Cajero.CAJERO_DB_NOMBRE + "," + Cajero.CAJERO_DB_TURNO + ","
            + Cajero.CAJERO_DB_DPI + "," + Cajero.CAJERO_DB_DIRECCION + "," + Cajero.CAJERO_DB_SEXO
            + ") VALUES (?,?,?,?,?,?)";

    private final String MODIFICAR_CAJERO = "UPDATE " + Cajero.CAJERO_DB_TABLE + " SET " + Cajero.CAJERO_DB_NOMBRE
            + "=?," + Cajero.CAJERO_DB_TURNO + "=?," + Cajero.CAJERO_DB_DPI + "=?," + Cajero.CAJERO_DB_DIRECCION + "=?,"
            + Cajero.CAJERO_DB_SEXO + "=? WHERE " + Cajero.CAJERO_DB_CODIGO + " = ?";

    private final String CAJERO_MAS_TRANSACCIONES_INTERVALO = "SELECT C.*,COUNT(T.codigo_CAJERO) FROM TRANSACCION AS T INNER JOIN CAJERO AS C ON C.codigo=T.codigo_CAJERO AND T.fecha BETWEEN ? AND ? GROUP BY  codigo_CAJERO ORDER BY COUNT(codigo_CAJERO) DESC LIMIT 1";
    /**
     * 
     * @param cajero
     * @throws SQLException
     */
    public void RegistroCajero(Cajero cajero) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(REGISTRAR_CAJERO_CREADO);

        preSt.setLong(1, cajero.getCodigo());
        preSt.setString(2, cajero.getNombre());
        preSt.setString(3, cajero.getTurno());
        preSt.setString(4, cajero.getDpi());
        preSt.setString(5, cajero.getDireccion());
        preSt.setString(6, cajero.getSexo());

        preSt.executeUpdate();
    }
    
    /**
     * OBTIENE EL CAJERO EN BASE AL CODIGO DE ENTRADA
     * @param codigo
     * @return
     * @throws SQLException
     */
    public Cajero ObtenerCajero(String codigo) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CAJERO);
        preSt.setString(1, codigo);
        ResultSet result = preSt.executeQuery();
        while (result.next()) {
            return new Cajero(result.getLong(Cajero.CAJERO_DB_CODIGO), null, result.getString(Cajero.CAJERO_DB_DPI),
                    result.getString(Cajero.CAJERO_DB_NOMBRE), result.getString(Cajero.CAJERO_DB_SEXO),
                    result.getString(Cajero.CAJERO_DB_DIRECCION), result.getString(Cajero.CAJERO_DB_TURNO));
        }
        return null;
    }
    /**
     * RETORNA EL CAJERO CON MAS TRANSACCIONES EN UN UN INTERVALO DE TIEMPO
     * @param inicio
     * @param fin
     * @return
     * @throws SQLException
     */
    public CajeroConNumeroDeTransacciones obtnerCajeroMasTransaccionesIntervalo(String inicio, String fin) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(CAJERO_MAS_TRANSACCIONES_INTERVALO);
        preSt.setString(1, inicio);
        preSt.setString(2, fin);
        ResultSet result = preSt.executeQuery();
        while (result.next()) {
            return new CajeroConNumeroDeTransacciones(new Cajero(result.getLong(Cajero.CAJERO_DB_CODIGO), null, result.getString(Cajero.CAJERO_DB_DPI),
            result.getString(Cajero.CAJERO_DB_NOMBRE), result.getString(Cajero.CAJERO_DB_SEXO),
            result.getString(Cajero.CAJERO_DB_DIRECCION), result.getString(Cajero.CAJERO_DB_TURNO)), result.getInt(7));
            
        }
        return null;
    }

    /**
     * REALIZA LA MODIFCACION DEL CAJERO INGRESADO
     * @param cajero
     * @throws SQLException
     */
    public void ModificarCajero(Cajero cajero) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(MODIFICAR_CAJERO);

        preSt.setString(1, cajero.getNombre());
        preSt.setString(2, cajero.getTurno());
        preSt.setString(3, cajero.getDpi());
        preSt.setString(4, cajero.getDireccion());
        preSt.setString(5, cajero.getSexo());
        preSt.setLong(6, cajero.getCodigo());

        preSt.executeUpdate();
    }
}
