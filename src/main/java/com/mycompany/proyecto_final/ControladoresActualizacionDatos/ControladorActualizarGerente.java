package com.mycompany.proyecto_final.ControladoresActualizacionDatos;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.proyecto_final.Conversiones.ConversionesVariables;
import com.mycompany.proyecto_final.Entidades.Gerente;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelGerente;

@WebServlet("/ActualizarDatosGerente")
public class ControladorActualizarGerente extends HttpServlet {
    
    private ModelGerente modelGerente = new ModelGerente();
    private ConversionesVariables conv = new ConversionesVariables();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        UsuarioDeSistema gerenteObtenido = (UsuarioDeSistema) req.getSession().getAttribute("USER");

        try {
            Gerente gerente = modelGerente.ObtenerGerente(gerenteObtenido.getCodigo().toString());
            if (gerente != null) {
                System.out.println("Gerente recuperado: " + gerente.toString());
                req.setAttribute("entidad", gerente);
                req.setAttribute("success", 0);
                req.getSession().setAttribute("codigoEntidad", gerente.getCodigo());
                req.getRequestDispatcher("/ActualizacionDatos/ActualizacionGerente.jsp").forward(req, resp);
            } else {
                req.setAttribute("noData", 0);
                req.setAttribute("success", 0);
                req.getRequestDispatcher("/ActualizacionDatos/ActualizacionGerente.jsp").forward(req, resp);
            }

        } catch (SQLException e) {
            System.out.println("Error en GET ActualizarGerente: " + e.getMessage());
            req.setAttribute("success", 2);
            req.setAttribute("errores", e.getMessage());
            req.getRequestDispatcher("/ActualizacionDatos/ActualizacionGerente.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long codigoEntidad = (Long) req.getSession().getAttribute("codigoEntidad");
        req.getSession().removeAttribute("codigoEntidad");

        if (codigoEntidad == null) {
            req.setAttribute("success", 2);
            req.setAttribute("errores", "Debe de seleccionar un gerente para modificar");
            req.getRequestDispatcher("/ActualizacionDatos/ActualizacionGerente.jsp").forward(req, resp);
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
        Gerente gerente = new Gerente(codigoEntidad, nombre, turno, dpi, direccion, sexo, null);
        System.out.println("Gerente a modificar: " + gerente.toString());

        this.ActualizarGerente(gerente, req, resp);

    }

    private void ActualizarGerente(Gerente gerente, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            modelGerente.ModificarGerente(gerente);
            req.setAttribute("success", 1);
            req.getRequestDispatcher("/ActualizacionDatos/ActualizacionGerente.jsp").forward(req, resp);
        } catch (SQLException e) {
            req.setAttribute("success", 2);
            req.setAttribute("errores", e.getMessage());
            req.getRequestDispatcher("/ActualizacionDatos/ActualizacionGerente.jsp").forward(req, resp);
        }
    }
}
