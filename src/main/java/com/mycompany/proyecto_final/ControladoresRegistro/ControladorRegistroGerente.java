package com.mycompany.proyecto_final.ControladoresRegistro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.proyecto_final.Entidades.Gerente;

@WebServlet("/RegistroGerente")
public class ControladorRegistroGerente extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre=null;
        String dpi=null;
        String sexo=null;
        String direccion=null;
        String turno=null;
        String password=null;

        nombre = req.getParameter("nombreEntidad");
        sexo = req.getParameter("sexo");
        direccion = req.getParameter("direccion");
        dpi = req.getParameter("numeroDPI");
        turno = req.getParameter("turno");
        password = req.getParameter("passInicial");
        
        Gerente gerente = new Gerente(null, nombre, turno, dpi, direccion, sexo, password);

        System.out.println(gerente.toString());
    }
}
