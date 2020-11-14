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

@WebServlet("/ControladorReporteCajero2")
public class ControladorReporteCajero2 extends HttpServlet {
    private ModelCajero modelCajero = new ModelCajero();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object usuarioDeSistema = req.getSession().getAttribute("USER");
        if (usuarioDeSistema == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        } else {
            try {
                String generar = req.getParameter("generar");
                Cajero cajero = modelCajero.ObtenerCajero(((UsuarioDeSistema) usuarioDeSistema).getCodigo().toString());

            } catch (Exception e) {
                
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
