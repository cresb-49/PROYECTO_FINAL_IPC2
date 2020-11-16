package com.mycompany.proyecto_final.Controladores.ControladoresGerente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ControladorSolicitudGerente")
public class ControladorSolicitudReportes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        } else {
            try {
                String reporte = req.getParameter("reporte");
                System.out.println("Reporte: " + reporte);
                switch (reporte) {
                    case "1":
                        req.setAttribute("success", 0);
                        req.getRequestDispatcher("/Reportes/ReportesGerente/ReporteGerente1.jsp").forward(req, resp);
                        break;
                    case "2":
                        req.setAttribute("success", 0);
                        req.getRequestDispatcher("/Reportes/ReportesGerente/ReporteGerente2.jsp").forward(req, resp);
                        break;
                    case "3":
                        req.getRequestDispatcher("/ControladorReporteGerente3").forward(req, resp);
                        break;
                    case "4":
                        break;
                    case "5":
                        req.setAttribute("success", 0);
                        req.getRequestDispatcher("/Reportes/ReportesGerente/ReporteGerente5.jsp").forward(req, resp);
                        break;
                    case "6":
                        break;
                    default:
                        req.getRequestDispatcher("/Perfiles/PerfilGerente.jsp").forward(req, resp);
                        break;
                }
            } catch (Exception e) {
                req.getRequestDispatcher("/Perfiles/PerfilGerente.jsp").forward(req, resp);
                System.out.println("Error controlador solicitud reporte gerente: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
