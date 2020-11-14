package com.mycompany.proyecto_final.Controladores.ControladorCajero;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.proyecto_final.Entidades.Cajero;
import com.mycompany.proyecto_final.Entidades.Transaccion;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelCajero;
import com.mycompany.proyecto_final.Models.ModelTransaccion;
import com.mycompany.proyecto_final.Trabajadores.TiemposDeTrabajo;

@WebServlet("/SolicitudReporteCajero")
public class ControladorSolicitudReporteCajero extends HttpServlet {
    private ModelCajero modelCajero = new ModelCajero();
    private ModelTransaccion modelTransaccion = new ModelTransaccion();
    private TiemposDeTrabajo tiempoTrabajo = new TiemposDeTrabajo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        } else {
            try {
                String reporte = req.getParameter("reporte");
                switch (reporte) {
                    case "1":
                        req.getRequestDispatcher("/ControladorReporteCajero1").forward(req, resp);
                        break;
                    case "2":
                        req.getRequestDispatcher("/ControladorReporteCajero2").forward(req, resp);
                        break;
                    default:
                        req.getRequestDispatcher("/Perfiles/PerfilCajero.jsp").forward(req, resp);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        } else {

        }
    }
}
