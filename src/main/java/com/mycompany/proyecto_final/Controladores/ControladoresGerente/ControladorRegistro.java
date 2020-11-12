package com.mycompany.proyecto_final.Controladores.ControladoresGerente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistrosNuevos")
public class ControladorRegistro extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipoRegistro = req.getParameter("registro");
        // System.out.println("Estoy en el controlador de registros nuevos, Opcion=" +
        // tipoRegistro);
        switch (tipoRegistro) {
            case "1":
                req.setAttribute("success", 0);
                req.getRequestDispatcher("/Registros/RegistarCliente.jsp").forward(req, resp);
                break;
            case "2":
                req.setAttribute("success", 0);
                req.getRequestDispatcher("/Registros/RegistrarCajero.jsp").forward(req, resp);
                break;
            case "3":
                req.setAttribute("success", 0);
                req.getRequestDispatcher("/Registros/RegistrarGerente.jsp").forward(req, resp);
                break;
            default:
                req.getRequestDispatcher("/Perfiles/PerfilGerente.jsp").forward(req, resp);
                break;
        }

    }
}
