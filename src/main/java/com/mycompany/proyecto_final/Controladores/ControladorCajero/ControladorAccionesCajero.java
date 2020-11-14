package com.mycompany.proyecto_final.Controladores.ControladorCajero;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.proyecto_final.Entidades.Cajero;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelCajero;
import com.mycompany.proyecto_final.Trabajadores.TiemposDeTrabajo;

@WebServlet("/AccionCajero")
public class ControladorAccionesCajero extends HttpServlet {
    private ModelCajero modelCajero = new ModelCajero();
    private TiemposDeTrabajo tiempoDeTrabajo = new TiemposDeTrabajo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        }else{

            try {
                UsuarioDeSistema uSistema = (UsuarioDeSistema) req.getSession().getAttribute("USER");
                Cajero cajero = modelCajero.ObtenerCajero(uSistema.getCodigo().toString());
                String action = req.getParameter("action");
                if(!tiempoDeTrabajo.validarAcceso(cajero.getTurno())){
                   action="10";
                }
                switch (action) {
                    case "1":
                        req.setAttribute("success", 0);
                        req.getRequestDispatcher("/Transacciones/AbonoBancario.jsp").forward(req, resp);
                        break;
                    case "2":
                        req.setAttribute("success", 0);
                        req.getRequestDispatcher("/Transacciones/RetiroBancario.jsp").forward(req, resp);
                        break;
                    case "10":
                        req.setAttribute("success", 0);
                        req.getRequestDispatcher("/Perfiles/PerfilCajero.jsp").forward(req, resp);
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
}
