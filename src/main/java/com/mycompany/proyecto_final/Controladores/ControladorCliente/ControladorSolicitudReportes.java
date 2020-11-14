package com.mycompany.proyecto_final.Controladores.ControladorCliente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SolicitudReporte")
public class ControladorSolicitudReportes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reportes = req.getParameter("reporte");
        switch (reportes) {
            case "1":
                req.getRequestDispatcher("/ControladorReporte1").forward(req, resp);
                break;
            case "2":
                req.getRequestDispatcher("/ControladorReporte2").forward(req, resp);
                break;
            case "3":
                req.getRequestDispatcher("/ControladorReporte3").forward(req, resp);
                break;
            case "4":
                req.getRequestDispatcher("/ControladorReporte4").forward(req, resp);
                break;
            case "5":
                req.getRequestDispatcher("/ControladorReporte5").forward(req, resp);
                break;
            default:
                req.getRequestDispatcher("/Perfiles/PerfilCliente.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
