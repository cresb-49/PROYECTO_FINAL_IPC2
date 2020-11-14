package com.mycompany.proyecto_final.Controladores.ControladoresActualizacionDatos;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.proyecto_final.Entidades.Cajero;
import com.mycompany.proyecto_final.Models.ModelCajero;

@WebServlet("/ActualizarDatosCajero")
public class ControladorActualizarCajero extends HttpServlet {
    private ModelCajero modelCajero = new ModelCajero();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        }else{

            String codigoEntidad = req.getParameter("codigoEntidad");
            if (!codigoEntidad.equals("101")) {
                try {
                    Cajero cajero = modelCajero.ObtenerCajero(codigoEntidad);
                    if (cajero != null) {
                        System.out.println("Cajero recuperado: " + cajero.toString());
                        req.setAttribute("entidad", cajero);
                        req.setAttribute("success", 0);
                        req.getSession().setAttribute("codigoEntidad", cajero.getCodigo());
                        req.getRequestDispatcher("/ActualizacionDatos/ActualizarCajero.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("noData", 0);
                        req.setAttribute("success", 0);
                        req.getRequestDispatcher("/ActualizacionDatos/ActualizarCajero.jsp").forward(req, resp);
                    }
    
                } catch (SQLException e) {
                    System.out.println("Error en GET ActualizarCajero: " + e.getMessage());
                    req.setAttribute("success", 2);
                    req.setAttribute("errores", e.getMessage());
                    req.getRequestDispatcher("/ActualizacionDatos/ActualizarCajero.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("noData", 0);
                req.setAttribute("success", 0);
                req.getRequestDispatcher("/ActualizacionDatos/ActualizarCajero.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        }else{
            Long codigoEntidad = (Long) req.getSession().getAttribute("codigoEntidad");
            req.getSession().removeAttribute("codigoEntidad");
    
            if (codigoEntidad == null) {
                req.setAttribute("success", 2);
                req.setAttribute("errores", "Debe de seleccionar un cliente para modificar");
                req.getRequestDispatcher("/ActualizacionDatos/ActualizarCajero.jsp").forward(req, resp);
            }
    
            String nombre = null;
            String dpi = null;
            String sexo = null;
            String direccion = null;
            String turno = null;
    
            nombre = req.getParameter("nombreEntidad");
            sexo = req.getParameter("sexo");
            direccion = req.getParameter("direccion");
            dpi = req.getParameter("numeroDPI");
            turno = req.getParameter("TipoTurno");
    
            Cajero cajero = new Cajero(codigoEntidad, null, dpi, nombre, sexo, direccion, turno);
    
            System.out.println("Cajero a modificar: " + cajero.toString());
    
            this.ActualizarCajero(cajero, req, resp);
        }


    }

    private void ActualizarCajero(Cajero cliente, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            modelCajero.ModificarCajero(cliente);
            req.setAttribute("success", 1);
            req.getRequestDispatcher("/ActualizacionDatos/ActualizarCajero.jsp").forward(req, resp);
        } catch (SQLException e) {
            req.setAttribute("success", 2);
            req.setAttribute("errores", e.getMessage());
            req.getRequestDispatcher("/ActualizacionDatos/ActualizarCajero.jsp").forward(req, resp);
        }
    }
}
