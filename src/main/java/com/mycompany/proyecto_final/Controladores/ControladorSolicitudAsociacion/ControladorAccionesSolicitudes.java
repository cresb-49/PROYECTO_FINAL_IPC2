package com.mycompany.proyecto_final.Controladores.ControladorSolicitudAsociacion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.proyecto_final.AsociarCuenta.SolicitudAsociasion;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelSolicitudAsociacion;

@WebServlet("/AccionesSolicitudes")
public class ControladorAccionesSolicitudes extends HttpServlet {
    private ModelSolicitudAsociacion modelSolicitud = new ModelSolicitudAsociacion();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        }else{
            UsuarioDeSistema uSistema = (UsuarioDeSistema) req.getSession().getAttribute("USER");
            System.out.println("Usuario solicitante: " + uSistema.getCodigo());
            try {
                List<SolicitudAsociasion> solicitudes = modelSolicitud.BuscarSolicitudPropietario(uSistema.getCodigo().toString());
                if (solicitudes.isEmpty()) {
                    req.setAttribute("success", 3);
                    req.getRequestDispatcher("/Solicitudes/RecepcionDeSolicitudes.jsp").forward(req, resp);
                } else {
                    req.setAttribute("success", 0);
                    req.setAttribute("solicitudes", solicitudes);
                    req.getRequestDispatcher("/Solicitudes/RecepcionDeSolicitudes.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                req.setAttribute("success", 1);
                req.setAttribute("errores", e.getMessage());
                req.getRequestDispatcher("/Solicitudes/RecepcionDeSolicitudes.jsp").forward(req, resp);
                e.printStackTrace();
            }
        }
    }
}
