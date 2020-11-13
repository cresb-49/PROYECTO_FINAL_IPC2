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

import com.mycompany.proyecto_final.Entidades.Cliente;
import com.mycompany.proyecto_final.Entidades.CuentaBancaria;
import com.mycompany.proyecto_final.Entidades.Transaccion;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelCliente;
import com.mycompany.proyecto_final.Models.ModelCuentaBancaria;
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

@WebServlet("/ControladorReporte1")
public class ControladorReporteCliente1 extends HttpServlet {
    private ModelCuentaBancaria modelCuenta = new ModelCuentaBancaria();
    private ModelTransaccion modelTransaccion = new ModelTransaccion();
    private ModelCliente modelCliente = new ModelCliente();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codigoCuenta = req.getParameter("codigoCuenta");
        if (codigoCuenta == null) {

            UsuarioDeSistema uSistema = (UsuarioDeSistema) req.getSession().getAttribute("USER");
            try {
                List<CuentaBancaria> cuentas = modelCuenta.BuscarCuentas(uSistema.getCodigo().toString());
                req.setAttribute("success", 0);
                req.setAttribute("cuentas", cuentas);
                req.getRequestDispatcher("/Reportes/ReportesCliente/ReporteCliente1.jsp").forward(req, resp);
            } catch (SQLException e) {
                req.setAttribute("success", 3);
                req.setAttribute("errores", e.getMessage());
                req.getRequestDispatcher("/Reportes/ReportesCliente/ReporteCliente1.jsp").forward(req, resp);
            }
        } else {
            try {
                List<Transaccion> transacciones = modelTransaccion.Ultimas15Transacciones(codigoCuenta);
                if (transacciones.isEmpty()) {
                    req.setAttribute("success", 5);
                    req.setAttribute("info", "Esta cuenta no ah tenido transacciones");
                    req.getRequestDispatcher("/Reportes/ReportesCliente/ReporteCliente1.jsp").forward(req, resp);
                } else {
                    req.setAttribute("success", 1);
                    req.setAttribute("codeCuenta", codigoCuenta);
                    req.setAttribute("transacciones", transacciones);
                    req.getRequestDispatcher("/Reportes/ReportesCliente/ReporteCliente1.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                req.setAttribute("success", 3);
                req.setAttribute("errores", e.getMessage());
                req.getRequestDispatcher("/Reportes/ReportesCliente/ReporteCliente1.jsp").forward(req, resp);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codigoCuenta = req.getParameter("codCuenta");

        try {
            UsuarioDeSistema uSistema = (UsuarioDeSistema) req.getSession().getAttribute("USER");
            List<Transaccion> transacciones = modelTransaccion.Ultimas15Transacciones(codigoCuenta);
            // System.out.println(transacciones.toString());

            resp.setContentType("application/pdf");
            resp.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=15Transacciones_mas_grandes_cuenta_" + codigoCuenta + ".pdf");

            Cliente cliente = modelCliente.ObtenerCliente(uSistema.getCodigo().toString());

            InputStream logoBanco = new FileInputStream(req.getServletContext().getRealPath("/Resources/Imagenes/LogoBilleton.png"));
            JRBeanCollectionDataSource itemsJRBen = new JRBeanCollectionDataSource(transacciones);
            Map<String,Object> parametros = new HashMap<String,Object>();
            parametros.put("ColllectionBeanParam",itemsJRBen);
            parametros.put("NombreCliente", cliente.getNombre());
            parametros.put("ClienteCodigo", cliente.getCodigo());
            parametros.put("codeCuenta", Long.valueOf(codigoCuenta));
            parametros.put("logoBilleton", logoBanco);

            InputStream input = new FileInputStream(req.getServletContext().getRealPath("/Resources/jasperReports/Cliente/ReporteCliente1.jrxml"));
            
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
