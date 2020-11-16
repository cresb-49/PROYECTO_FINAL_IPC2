package com.mycompany.proyecto_final.Controladores.ControladoresGerente.ControladorReportesGerente;

import com.mycompany.proyecto_final.Entidades.Cliente;
import com.mycompany.proyecto_final.Entidades.Transaccion;
import com.mycompany.proyecto_final.Models.ModelCliente;
import com.mycompany.proyecto_final.Models.ModelTransaccion;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@WebServlet("/ControladorReporteGerente1")
public class ControladorReporteGerente1 extends HttpServlet {

    private ModelCliente modelCliente = new ModelCliente();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        } else {
            try {
                String monto = req.getParameter("limiteMonetario");
                if (monto != null) {
                    List<Cliente> clientes = modelCliente.clientesTransaccionesMayores(monto);

                    if (clientes.isEmpty()) {
                        req.setAttribute("success", 2);
                        req.setAttribute("error", "Ningun resultado en la busqueda segun el limite introducido");
                        req.getRequestDispatcher("/Reportes/ReportesGerente/ReporteGerente1.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("clientes", clientes);
                        req.setAttribute("success", 1);
                        req.setAttribute("limiteMonetario", monto);
                        req.getRequestDispatcher("/Reportes/ReportesGerente/ReporteGerente1.jsp").forward(req, resp);
                    }
                } else {
                    req.setAttribute("success", 3);
                    req.setAttribute("error", "Monto de busqueda incorecto");
                    req.getRequestDispatcher("/Reportes/ReportesGerente/ReporteGerente1.jsp").forward(req, resp);
                }
            } catch (SQLException ex) {
                req.setAttribute("success", 2);
                req.setAttribute("error", ex.getMessage());
                req.getRequestDispatcher("/Reportes/ReportesGerente/ReporteGerente1.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        } else {
            try {
                String monto = req.getParameter("limiteMonetario");
                List<Cliente> clientes = modelCliente.clientesTransaccionesMayores(monto);
                
                resp.setContentType("application/pdf");
                resp.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Clientes_con_transacciones_mayores_a_"+monto+".pdf");

                InputStream logoBanco = new FileInputStream(req.getServletContext().getRealPath("/Resources/Imagenes/LogoBilleton.png"));
                JRBeanCollectionDataSource itemsJRBen = new JRBeanCollectionDataSource(clientes);
                Map<String, Object> parametros = new HashMap<String, Object>();
                parametros.put("ColllectionBeanParam", itemsJRBen);
                parametros.put("limiteTransaccion", monto);
                parametros.put("logoBilleton", logoBanco);

                InputStream input = new FileInputStream(req.getServletContext().getRealPath("/Resources/jasperReports/Gerente/ReporteGerente1.jrxml"));

                JasperDesign jasperDesign = JRXmlLoader.load(input);

                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());

                JasperExportManager.exportReportToPdfStream(jasperPrint, resp.getOutputStream());

                resp.getOutputStream().flush();
                resp.getOutputStream().close();

            } catch (Exception e) {
                req.setAttribute("success", 3);
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/Reportes/ReportesGerente/ReporteGerente1.jsp").forward(req, resp);
            }
        }
    }

}
