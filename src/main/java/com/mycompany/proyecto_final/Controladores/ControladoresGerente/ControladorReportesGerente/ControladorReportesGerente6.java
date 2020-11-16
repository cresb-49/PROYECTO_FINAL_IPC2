package com.mycompany.proyecto_final.Controladores.ControladoresGerente.ControladorReportesGerente;

import com.mycompany.proyecto_final.Entidades.Cajero;
import com.mycompany.proyecto_final.Entidades.Cliente;
import com.mycompany.proyecto_final.Entidades.Transaccion;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.EntidadesEspeciales.CajeroConNumeroDeTransacciones;
import com.mycompany.proyecto_final.EntidadesEspeciales.ClienteConDineroTotal;
import com.mycompany.proyecto_final.Models.ModelCajero;
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

@WebServlet("/ControladorReporteGerente6")
public class ControladorReportesGerente6 extends HttpServlet {
    private ModelCajero modelCajero = new ModelCajero();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        } else {
            String fechaMaxima = req.getParameter("fechaMaxima");
            String fechaMinima = req.getParameter("fechaMinima");
            try {
                CajeroConNumeroDeTransacciones cajero = modelCajero.obtnerCajeroMasTransaccionesIntervalo(fechaMinima,fechaMaxima);    


                resp.setContentType("application/pdf");
                resp.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=Cajero con mas transacciones entre "+fechaMinima+" y "+fechaMaxima+".pdf");
    
                InputStream logoBanco = new FileInputStream(req.getServletContext().getRealPath("/Resources/Imagenes/LogoBilleton.png"));
                Map<String,Object> parametros = new HashMap<String,Object>();
                
                parametros.put("nombreCajero",cajero.getCajero().getNombre());
                parametros.put("codigoCajero",cajero.getCajero().getCodigo());
                parametros.put("tipoTurno",cajero.getCajero().getTurno());
                parametros.put("dpi",cajero.getCajero().getDpi());
                parametros.put("sexo",cajero.getCajero().getSexo());
                parametros.put("direccion",cajero.getCajero().getDireccion());
                parametros.put("cantidadTransacciones",cajero.getCantidad());
                parametros.put("fechaInicio",fechaMinima);
                parametros.put("fechaFin",fechaMaxima);
                parametros.put("logoBilleton", logoBanco);
    
                InputStream input = new FileInputStream(req.getServletContext().getRealPath("/Resources/jasperReports/Gerente/ReporteGerente6.jrxml"));
                
                JasperDesign jasperDesign = JRXmlLoader.load(input);
    
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
    
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());
    
                JasperExportManager.exportReportToPdfStream(jasperPrint, resp.getOutputStream());
    
                resp.getOutputStream().flush();
                resp.getOutputStream().close();


            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("success", 3);
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/Reportes/ReportesGerente/ReporteGerente6.jsp").forward(req, resp);
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        } else {
            String fechaMaxima = req.getParameter("fechaMaxima");
            String fechaMinima = req.getParameter("fechaMinima");
            try {
                System.out.println("fecha minima: "+fechaMinima+" fecha maxima: "+fechaMaxima);
                CajeroConNumeroDeTransacciones cajero = modelCajero.obtnerCajeroMasTransaccionesIntervalo(fechaMinima,fechaMaxima);
                if(cajero!=null){
                    System.out.println(cajero.toString());
                    req.setAttribute("success", 1);
                    req.setAttribute("cajero", cajero);
                    req.setAttribute("fechaMaxima", fechaMaxima);
                    req.setAttribute("fechaMinima", fechaMinima);
                    req.getRequestDispatcher("/Reportes/ReportesGerente/ReporteGerente6.jsp").forward(req, resp);
                }else{
                    req.setAttribute("success", 3);
                    req.setAttribute("error", "No hay transacciones realizadas en ese intervalo de tiempo");
                    req.getRequestDispatcher("/Reportes/ReportesGerente/ReporteGerente6.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("success", 2);
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/Reportes/ReportesGerente/ReporteGerente6.jsp").forward(req, resp);
            }
        }
    }
}
