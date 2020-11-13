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

@WebServlet("/AceptarRechazarSolicitud")
public class ControladorAceptarRechazar extends HttpServlet {

    private ModelSolicitudAsociacion modelSolicitud = new ModelSolicitudAsociacion();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acction = req.getParameter("action");
        String idSolicitud = req.getParameter("id");
        System.out.println("Opcion a procesar: " + acction);

        try {
            SolicitudAsociasion solicitud = modelSolicitud.BuscarSolicitudID(idSolicitud);

            if (solicitud == null) {
                req.setAttribute("success", 1);
                req.setAttribute("errores", "No se ecnotro la solicitud a procesar");
                req.getRequestDispatcher("/Solicitudes/RecepcionDeSolicitudes.jsp").forward(req, resp);
            } else {
                switch (acction) {
                    case "1":
                        solicitud.setEstado(SolicitudAsociasion.ESTADO_SOLICITUD_1);
                        modelSolicitud.ActualizarSolicitud(solicitud);
                        req.setAttribute("success", 2);
                        req.setAttribute("mensaje", "Se acepto la solicitud de asociacion con exito");
                        req.getRequestDispatcher("/Solicitudes/RecepcionDeSolicitudes.jsp").forward(req, resp);
                        break;
                    case "2":
                        solicitud.setEstado(SolicitudAsociasion.ESTADO_SOLICITUD_2);
                        modelSolicitud.ActualizarSolicitud(solicitud);
                        req.setAttribute("success", 2);
                        req.setAttribute("mensaje", "Se rechazo la solicitud de asociacion con exito");
                        req.getRequestDispatcher("/Solicitudes/RecepcionDeSolicitudes.jsp").forward(req, resp);
                        break;
                    default:
                        req.setAttribute("success", 1);
                        req.setAttribute("errores", "Seleccion fuera de procesamiento");
                        req.getRequestDispatcher("/Solicitudes/RecepcionDeSolicitudes.jsp").forward(req, resp);
                        break;
                }
            }
        } catch (SQLException e) {
            req.setAttribute("success", 1);
            req.setAttribute("errores", e.getMessage());
            req.getRequestDispatcher("/Solicitudes/RecepcionDeSolicitudes.jsp").forward(req, resp);
            e.printStackTrace();
        }
    }
}
