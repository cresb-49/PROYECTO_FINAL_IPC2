package com.mycompany.proyecto_final.Controladores.ControladorCliente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AccionCliente")
public class ControladoAccionesCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "1":
                req.getRequestDispatcher("/ControladorBancaVitual").forward(req, resp);
                break;
            case "2":
                req.setAttribute("success", 0);
                req.getRequestDispatcher("/Solicitudes/SolicitudAsociacion.jsp").forward(req, resp);
                break;
            case "3":
                req.getRequestDispatcher("/AccionesSolicitudes").forward(req, resp);
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
