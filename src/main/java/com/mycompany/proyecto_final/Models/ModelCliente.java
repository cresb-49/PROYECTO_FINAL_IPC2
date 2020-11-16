package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.proyecto_final.Entidades.Cliente;
import com.mycompany.proyecto_final.EntidadesEspeciales.ClienteConDineroTotal;
import com.mycompany.proyecto_final.EntidadesEspeciales.ClienteSumaTransacciones;

public class ModelCliente {

    private static Connection connection = ConnectDB.getInstance();

    private final String BUSCAR_CLIENTE = "SELECT * FROM " + Cliente.CLIENTE_DB_TABLE + " WHERE "
            + Cliente.CLIENTE_DB_CODIGO + " = ?";
    private final String REGISTRAR_CLIENTE_CREADO = "INSERT INTO " + Cliente.CLIENTE_DB_TABLE + " ("
            + Cliente.CLIENTE_DB_CODIGO + "," + Cliente.CLIENTE_DB_NOMBRE + "," + Cliente.CLIENTE_DB_BIRTH + ","
            + Cliente.CLIENTE_DB_DPI + "," + Cliente.CLIENTE_DB_DIRECCION + "," + Cliente.CLIENTE_DB_SEXO
            + ") VALUES (?,?,?,?,?,?)";
    private final String MODIFICAR_CLIENTE = "UPDATE " + Cliente.CLIENTE_DB_TABLE + " SET " + Cliente.CLIENTE_DB_NOMBRE
            + "=?," + Cliente.CLIENTE_DB_BIRTH + "=?," + Cliente.CLIENTE_DB_DPI + "=?," + Cliente.CLIENTE_DB_DIRECCION
            + "=?," + Cliente.CLIENTE_DB_SEXO + "=? WHERE " + Cliente.CLIENTE_DB_CODIGO + " = ?";

    private final String CLIENTES_CON_MAS_DINERO = "SELECT *,SUM(CUENTA.credito) AS cantidad FROM CLIENTE INNER JOIN CUENTA  ON CLIENTE.codigo = CUENTA.codigo_CLIENTE GROUP BY CUENTA.codigo_CLIENTE ORDER BY SUM(CUENTA.credito) DESC LIMIT 10";

    private final String CLIENTES_TRANSACCIONES_MAYORES = "SELECT C.* FROM CLIENTE AS C INNER JOIN TRANSACCION AS T INNER JOIN CUENTA AS CB ON CB.codigo_CLIENTE = C.codigo AND CB.codigo = T.codigo_CUENTA AND T.monto > ? GROUP BY C.codigo";

    private final String TRANSACACIONES_CLIENTES_SUMADAS = "SELECT CL.*,SUM(T.monto) AS suma FROM TRANSACCION AS T INNER JOIN CUENTA AS C INNER JOIN CLIENTE AS CL ON C.codigo = T.codigo_CUENTA AND CL.codigo = C.codigo_CLIENTE GROUP BY CL.codigo ORDER BY SUM(T.monto) DESC";

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
    public void RegistroCliente(Cliente cliente) throws SQLException {
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
                    result.getString(Cliente.CLIENTE_DB_NOMBRE), result.getString(Cliente.CLIENTE_DB_DPI),
                    result.getString(Cliente.CLIENTE_DB_SEXO), result.getString(Cliente.CLIENTE_DB_DIRECCION),
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

    /**
     * RETORNA LOS 10 CLIENTES CON MAS DINERO
     *
     * @return
     */
    public List<ClienteConDineroTotal> diezClientesConMasDinero() throws SQLException {
        List<ClienteConDineroTotal> clientes = new ArrayList<>();
        PreparedStatement preSt = connection.prepareStatement(CLIENTES_CON_MAS_DINERO);
        ResultSet result = preSt.executeQuery();
        while (result.next()) {
            clientes.add(
                    new ClienteConDineroTotal(
                            new Cliente(result.getLong("codigo"), null, result.getString("nombre"),
                                    result.getString("dpi"), null, result.getString("direccion"), null, null),
                            result.getDouble("cantidad")));
        }
        return clientes;
    }

    /**
     * RETORNA LOS CLIENTES CON TRANSACCIONES MAYORES A UN LIMITE
     * 
     * @param codigo
     * @return
     * @throws SQLException
     */
    public List<Cliente> clientesTransaccionesMayores(String cantidad) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement preSt = connection.prepareStatement(CLIENTES_TRANSACCIONES_MAYORES);
        preSt.setString(1, cantidad);
        ResultSet result = preSt.executeQuery();
        while (result.next()) {
            clientes.add(new Cliente(result.getLong(Cliente.CLIENTE_DB_CODIGO), null,
                    result.getString(Cliente.CLIENTE_DB_NOMBRE), result.getString(Cliente.CLIENTE_DB_DPI),
                    result.getString(Cliente.CLIENTE_DB_SEXO), result.getString(Cliente.CLIENTE_DB_DIRECCION),
                    result.getDate(Cliente.CLIENTE_DB_BIRTH), null));
        }
        return clientes;
    }

    /**
     * RETRONA LOS CLIENTES CON SU SUMA DE TRANSACCION
     * 
     * @return
     * @throws SQLException
     */
    public List<ClienteSumaTransacciones> clientesTransaccionesSumadas() throws SQLException {
        List<ClienteSumaTransacciones> clientes = new ArrayList<>();
        PreparedStatement preSt = connection.prepareStatement(TRANSACACIONES_CLIENTES_SUMADAS);

        ResultSet result = preSt.executeQuery();
        while (result.next()) {
            clientes.add(new ClienteSumaTransacciones(new Cliente(result.getLong(Cliente.CLIENTE_DB_CODIGO), null,
                    result.getString(Cliente.CLIENTE_DB_NOMBRE), result.getString(Cliente.CLIENTE_DB_DPI),
                    result.getString(Cliente.CLIENTE_DB_SEXO), result.getString(Cliente.CLIENTE_DB_DIRECCION),
                    result.getDate(Cliente.CLIENTE_DB_BIRTH), null), result.getDouble(7)));
        }
        return clientes;
    }
}
