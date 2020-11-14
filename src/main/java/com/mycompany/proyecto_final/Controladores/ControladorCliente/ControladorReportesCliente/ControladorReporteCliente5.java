package com.mycompany.proyecto_final.Controladores.ControladorCliente.ControladorReportesCliente;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.HttpHeaders;

import com.mycompany.proyecto_final.AsociarCuenta.SolicitudAsociasion;
import com.mycompany.proyecto_final.Entidades.Cliente;
import com.mycompany.proyecto_final.Entidades.CuentaBancaria;
import com.mycompany.proyecto_final.Entidades.Transaccion;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelCliente;
import com.mycompany.proyecto_final.Models.ModelCuentaBancaria;
import com.mycompany.proyecto_final.Models.ModelSolicitudAsociacion;
import com.mycompany.proyecto_final.Models.ModelTransaccion;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@WebServlet("/ControladorReporte5")
public class ControladorReporteCliente5 extends HttpServlet {

    private ModelSolicitudAsociacion modelSolicitud = new ModelSolicitudAsociacion();
    private ModelCliente modelCliente = new ModelCliente();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        }else{
            UsuarioDeSistema uSistema = (UsuarioDeSistema) req.getSession().getAttribute("USER");
            try {
                List<SolicitudAsociasion> solicitudes = modelSolicitud.BuscarSolicitudSolicitante(uSistema.getCodigo().toString());
                if (solicitudes.isEmpty()) {
                    req.setAttribute("success", 5);
                    req.setAttribute("info", "No tiene ah realizado ninguna solicitud de asociacion");
                    req.getRequestDispatcher("/Reportes/ReportesCliente/ReporteCliente5.jsp").forward(req, resp);
                } else {
                    req.setAttribute("success", 0);
                    req.setAttribute("solicitudes", solicitudes);
                    req.getRequestDispatcher("/Reportes/ReportesCliente/ReporteCliente5.jsp").forward(req, resp);
                }
    
            } catch (Exception e) {
                req.setAttribute("success", 3);
                req.setAttribute("errores", e.getMessage());
                req.getRequestDispatcher("/Reportes/ReportesCliente/ReporteCliente5.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        }else{
            try {
                UsuarioDeSistema uSistema = (UsuarioDeSistema) req.getSession().getAttribute("USER");
                List<SolicitudAsociasion> solicitudes = modelSolicitud.BuscarSolicitudSolicitante(uSistema.getCodigo().toString());
    
                resp.setContentType("application/pdf");
                resp.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=Hitorial_Solictudes_Realizadas_Cliente_" + uSistema.getCodigo().toString()+".pdf");
    
                Cliente cliente = modelCliente.ObtenerCliente(uSistema.getCodigo().toString());
    
                InputStream logoBanco = new FileInputStream(req.getServletContext().getRealPath("/Resources/Imagenes/LogoBilleton.png"));
    
                JRBeanCollectionDataSource itemsJRBen = new JRBeanCollectionDataSource(solicitudes);
    
                Map<String, Object> parametros = new HashMap<String, Object>();
                parametros.put("ColllectionBeanParam", itemsJRBen);
                parametros.put("NombreCliente", cliente.getNombre());
                parametros.put("ClienteCodigo", cliente.getCodigo());
                parametros.put("logoBilleton", logoBanco);
    
                InputStream input = new FileInputStream(
                        req.getServletContext().getRealPath("/Resources/jasperReports/Cliente/ReporteCliente5.jrxml"));
    
                JasperDesign jasperDesign = JRXmlLoader.load(input);
    
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
    
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());
    
                JasperExportManager.exportReportToPdfStream(jasperPrint, resp.getOutputStream());
    
                resp.getOutputStream().flush();
                resp.getOutputStream().close();
    
            } catch (Exception e) {
                req.setAttribute("success", 3);
                req.setAttribute("errores", e.getMessage());
                req.getRequestDispatcher("/Reportes/ReportesCliente/ReporteCliente5.jsp").forward(req, resp);
            }
        }
    }
}
