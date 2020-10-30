package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModelUsuarioSistema {
    private static Connection connection = ConnectDB.getInstance();
    public ModelUsuarioSistema(){

    }
}
