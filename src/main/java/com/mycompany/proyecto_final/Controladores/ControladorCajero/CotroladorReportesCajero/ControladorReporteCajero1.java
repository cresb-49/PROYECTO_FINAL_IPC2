package com.mycompany.proyecto_final.Controladores.ControladorCajero.CotroladorReportesCajero;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.html.HtmlBody;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.HttpHeaders;

import com.mycompany.proyecto_final.ApoyoCajero.BalanceTransacciones;
import com.mycompany.proyecto_final.Entidades.Cajero;
import com.mycompany.proyecto_final.Entidades.Transaccion;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelCajero;
import com.mycompany.proyecto_final.Models.ModelTransaccion;
import com.mycompany.proyecto_final.Trabajadores.TiemposDeTrabajo;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@WebServlet("/ControladorReporteCajero1")
public class ControladorReporteCajero1 extends HttpServlet {
    private ModelCajero modelCajero = new ModelCajero();
    private ModelTransaccion modelTransaccion = new ModelTransaccion();
    private TiemposDeTrabajo tiempoTrabajo = new TiemposDeTrabajo();
    private BalanceTransacciones balance = new BalanceTransacciones();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object usuarioDeSistema = req.getSession().getAttribute("USER");
        if (usuarioDeSistema == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        } else {
            try {
                String generar = req.getParameter("generar");
                java.time.LocalDate today = java.time.LocalDate.now();
                Cajero cajero = modelCajero.ObtenerCajero(((UsuarioDeSistema) usuarioDeSistema).getCodigo().toString());
                List<Transaccion> transaciones = modelTransaccion.transaccionesPorCajeroIntervaloTiempo(
                        cajero.getCodigo().toString(), tiempoTrabajo.obtenerTiempoInicio(cajero.getTurno()),
                        tiempoTrabajo.obtenerTiempoFin(cajero.getTurno()),today.toString());

                if (transaciones.isEmpty()) {
                    req.setAttribute("success", 5);
                    req.setAttribute("info", "No realizo ninguna transaccion en su turno de dia de hoy");
                    req.getRequestDispatcher("/Reportes/ReportesCajero/ReportesCajero1.jsp").forward(req, resp);
                } else {
                    balance.setTransaciones(transaciones);
                    if (generar != null) {
                        this.GenerarReporte(req, resp, cajero, transaciones);
                    } else {
                        req.setAttribute("transacciones", transaciones);
                        req.setAttribute("success", 0);
                        req.setAttribute("balance", this.balance.obtenerBalance());
                        req.getRequestDispatcher("/Reportes/ReportesCajero/ReportesCajero1.jsp").forward(req, resp);
                    }
                }
            } catch (Exception e) {
                req.setAttribute("success", 3);
                req.setAttribute("errores", e.getMessage());
                req.getRequestDispatcher("/Reportes/ReportesCajero/ReportesCajero1.jsp").forward(req, resp);
            }
        }
    }

    private void GenerarReporte(HttpServletRequest req, HttpServletResponse resp, Cajero cajero,List<Transaccion> transacciones) throws ServletException, IOException {

        try {
            resp.setContentType("application/pdf");
            resp.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=Depositos_realizados_durante_turno_cajero_" + cajero.getCodigo().toString()+".pdf");

            InputStream logoBanco = new FileInputStream(req.getServletContext().getRealPath("/Resources/Imagenes/LogoBilleton.png"));
            JRBeanCollectionDataSource itemsJRBen = new JRBeanCollectionDataSource(transacciones);
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("ColllectionBeanParam", itemsJRBen);

            parametros.put("fechaTransacciones", transacciones.get(0).getFechaTransaccion());
            parametros.put("nombreCajero", cajero.getNombre());
            parametros.put("codigoCajero", cajero.getCodigo());
            parametros.put("tipoTurno", cajero.getTurno());
            parametros.put("horarioInicio", this.tiempoTrabajo.obtenerTiempoInicio(cajero.getTurno()));
            parametros.put("horarioFin", this.tiempoTrabajo.obtenerTiempoFin(cajero.getTurno()));
            parametros.put("balance", this.balance.obtenerBalance());
            parametros.put("logoBilleton", logoBanco);

            InputStream input = new FileInputStream(req.getServletContext().getRealPath("/Resources/jasperReports/Cajero/ReporteCajero1.jrxml"));

            JasperDesign jasperDesign = JRXmlLoader.load(input);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());

            JasperExportManager.exportReportToPdfStream(jasperPrint, resp.getOutputStream());

            resp.getOutputStream().flush();
            resp.getOutputStream().close();
        } catch (Exception e) {
            req.setAttribute("success", 3);
            req.setAttribute("errores", e.getMessage());
            req.getRequestDispatcher("/Reportes/ReportesCajero/ReportesCajero1.jsp").forward(req, resp);
        }

    }
}
