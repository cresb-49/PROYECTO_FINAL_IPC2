package com.mycompany.proyecto_final.Controladores.ControladoresGerente;

import java.io.IOException;
import java.sql.SQLException;

import javax.enterprise.inject.Model;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.proyecto_final.Entidades.Gerente;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelGerente;
import com.mycompany.proyecto_final.Trabajadores.TiemposDeTrabajo;

@WebServlet("/ActualizarDatos")
public class ControladorActualizacionDatos extends HttpServlet {

    private ModelGerente modelGerente = new ModelGerente();
    private TiemposDeTrabajo tiempoTrabajo = new TiemposDeTrabajo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        }else{
            try {
                UsuarioDeSistema usuarioDeSistema = (UsuarioDeSistema) req.getSession().getAttribute("USER");
                Gerente gerente = modelGerente.ObtenerGerente(usuarioDeSistema.getCodigo().toString());
                String tipoRegistro = req.getParameter("actualizar");
                if (!tiempoTrabajo.validarAcceso(gerente.getTurno())) {
                    tipoRegistro = "10";
                }
                switch (tipoRegistro) {
                    case "1":
                        req.setAttribute("success", 0);
                        req.getRequestDispatcher("/ActualizacionDatos/ActualizarCliente.jsp").forward(req, resp);
                        break;
                    case "2":
                        req.setAttribute("success", 0);
                        req.getRequestDispatcher("/ActualizacionDatos/ActualizarCajero.jsp").forward(req, resp);
                        break;
                    case "3":
                        req.setAttribute("success", 0);
                        req.getRequestDispatcher("/ActualizarDatosGerente").forward(req, resp);
                        break;
                    case "10":
                        req.setAttribute("success", 0);
                        req.getRequestDispatcher("/Perfiles/PerfilGerente.jsp").forward(req, resp);
                        break;
                    default:
                        req.getRequestDispatcher("/Perfiles/PerfilGerente.jsp").forward(req, resp);
                        break;
                }
    
            } catch (Exception e) {
                System.out.println("Error Actualizar datos: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
