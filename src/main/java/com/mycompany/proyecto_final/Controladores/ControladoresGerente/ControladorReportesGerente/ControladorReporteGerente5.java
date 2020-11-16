package com.mycompany.proyecto_final.Controladores.ControladoresGerente.ControladorReportesGerente;

import com.mycompany.proyecto_final.Entidades.Cliente;
import com.mycompany.proyecto_final.Entidades.Transaccion;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.EntidadesEspeciales.ClienteConDineroTotal;
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

@WebServlet("/ControladorReporteGerente5")
public class ControladorReporteGerente5 extends HttpServlet {

    private ModelCliente modelCliente = new ModelCliente();
    private ModelTransaccion modelTransaccion = new ModelTransaccion();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        } else {
            try {
                String codecliente = req.getParameter("codigoEntidad");

                resp.setContentType("application/pdf");
                resp.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=Historial_transacciones_Cliente_" +codecliente+ ".pdf");
    
                Cliente cliente = modelCliente.ObtenerCliente(codecliente);
                List<Transaccion> transacciones = modelTransaccion.transacionesCliente(codecliente);

                InputStream logoBanco = new FileInputStream(req.getServletContext().getRealPath("/Resources/Imagenes/LogoBilleton.png"));
                JRBeanCollectionDataSource itemsJRBen = new JRBeanCollectionDataSource(transacciones);
                Map<String,Object> parametros = new HashMap<String,Object>();
                parametros.put("ColllectionBeanParam",itemsJRBen);
                parametros.put("NombreCliente", cliente.getNombre());
                parametros.put("ClienteCodigo", cliente.getCodigo());
                parametros.put("logoBilleton", logoBanco);
    
                InputStream input = new FileInputStream(req.getServletContext().getRealPath("/Resources/jasperReports/Gerente/ReporteGerente5.jrxml"));
                
                JasperDesign jasperDesign = JRXmlLoader.load(input);
    
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
    
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());
    
                JasperExportManager.exportReportToPdfStream(jasperPrint, resp.getOutputStream());
    
                resp.getOutputStream().flush();
                resp.getOutputStream().close();
    
    
            } catch (Exception e) {
                req.setAttribute("success", 3);
                req.setAttribute("errores", e.getMessage());
                req.getRequestDispatcher("/Reportes/ReportesCliente/ReporteCliente1.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        } else {
            String codigoCliente = req.getParameter("codigoEntidad");
            try {
                Cliente cliente = modelCliente.ObtenerCliente(codigoCliente);
                if (cliente == null) {
                    req.setAttribute("success", 3);
                    req.setAttribute("error", "El codigo no corresponde a ningun cliente");
                    req.getRequestDispatcher("/Reportes/ReportesGerente/ReporteGerente5.jsp").forward(req, resp);
                } else {
                    List<Transaccion> transacciones = modelTransaccion.transacionesCliente(codigoCliente);
                    if (transacciones.isEmpty()) {
                        req.setAttribute("success", 3);
                        req.setAttribute("error", "No hay transacciones regustrdas para el cliente ingresado");
                        req.getRequestDispatcher("/Reportes/ReportesGerente/ReporteGerente5.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("success", 1);
                        req.setAttribute("codigoEntidad", codigoCliente);
                        req.setAttribute("transacciones", transacciones);
                        req.getRequestDispatcher("/Reportes/ReportesGerente/ReporteGerente5.jsp").forward(req, resp);
                    }
                }
            } catch (Exception e) {
                req.setAttribute("success", 2);
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/Reportes/ReportesGerente/ReporteGerente5.jsp").forward(req, resp);
            }

        }
    }
}
