/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_final.Controladores;

import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelUsuarioSistema;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ControladorLogin")
public class ControladorLogin extends HttpServlet{
    ModelUsuarioSistema modelUsuario = new ModelUsuarioSistema();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("usuario");
        String pass = req.getParameter("password");
        System.out.println("Controlador Login: USER: "+user+" ,PASS:"+pass);
        try {
            
            UsuarioDeSistema usuario = this.modelUsuario.accesoDeUsuario(user, pass);
            System.out.println("Usuario Retornado: "+usuario.toString());
            
        } catch (SQLException e) {
            System.out.println("Error DB USUARIO: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
}
