package com.mycompany.proyecto_final.Controladores.ControladorSolicitudAsociacion;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.proyecto_final.AsociarCuenta.SolicitudAsociasion;
import com.mycompany.proyecto_final.Entidades.Cliente;
import com.mycompany.proyecto_final.Entidades.CuentaBancaria;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelCliente;
import com.mycompany.proyecto_final.Models.ModelCuentaBancaria;
import com.mycompany.proyecto_final.Models.ModelSolicitudAsociacion;

@WebServlet("/ControladorSolicitud")
public class ControladorSolicitudAsociacion extends HttpServlet {
    private ModelCuentaBancaria modelCuentaBancaria = new ModelCuentaBancaria();
    private ModelCliente modelCliente = new ModelCliente();
    private ModelSolicitudAsociacion modelSolicitud = new ModelSolicitudAsociacion();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        }else{

            String nombreCliente = req.getParameter("nombreCliente");
            String numeroCuenta = req.getParameter("numeroCuenta");
            String ndpi = req.getParameter("numeroDPI");
            UsuarioDeSistema uSistema = (UsuarioDeSistema) req.getSession().getAttribute("USER");
    
            System.out.println("Datos recuperados: nombre=" + nombreCliente + ", Cuenta=" + numeroCuenta + ", DPI=" + ndpi);
    
            try {
                CuentaBancaria cuenta = modelCuentaBancaria.BuscarCuenta(numeroCuenta);
                if (cuenta != null) {
                    Cliente cliente = modelCliente.ObtenerCliente(cuenta.getIdCliente().toString());
                    if (cliente.getDpi().equals(ndpi)) {
                        SolicitudAsociasion solicitud = modelSolicitud.BuscarSolicitud(cuenta.getCodigo().toString(), uSistema.getCodigo().toString());
                        if (solicitud == null) {
                            solicitud = new SolicitudAsociasion(null, uSistema.getCodigo(), cliente.getCodigo(),
                                    cuenta.getCodigo(), SolicitudAsociasion.ESTADO_SOLICITUD_3, 1);
                            Long code = modelSolicitud.RegistarSolicitud(solicitud);
                            if (code == -1) {
                                req.setAttribute("success", 1);
                                req.setAttribute("errores", "No se pudo realizar la solicitud");
                                req.getRequestDispatcher("/Solicitudes/SolicitudAsociacion.jsp").forward(req, resp);
                            } else {
                                req.setAttribute("success", 2);
                                req.setAttribute("intentos", "Intentos disponibles: 2");
                                req.getRequestDispatcher("/Solicitudes/SolicitudAsociacion.jsp").forward(req, resp);
                            }
                        } else {
                            System.out.println("Solicitud encontrada: "+solicitud.toString());
                            if (solicitud.getEstado().equals(SolicitudAsociasion.ESTADO_SOLICITUD_1)) {
                                req.setAttribute("success", 1);
                                req.setAttribute("errores","La cuenta ya esta asociada, no es necesario realizar nuevamente el procedimiento");
                                req.getRequestDispatcher("/Solicitudes/SolicitudAsociacion.jsp").forward(req, resp);
                            } else if (solicitud.getEstado().equals(SolicitudAsociasion.ESTADO_SOLICITUD_3)) {
                                req.setAttribute("success", 1);
                                req.setAttribute("errores","La solicitud esta en espera, no puede realizar esta accion por el momento");
                                req.getRequestDispatcher("/Solicitudes/SolicitudAsociacion.jsp").forward(req, resp);
                            } else {
    
                                if (solicitud.getIntento() == 3) {
                                    req.setAttribute("success", 1);
                                    req.setAttribute("errores","Ya no puede realizar una solicitud a esta cuenta, los intentos estan limitados a 3");
                                    req.getRequestDispatcher("/Solicitudes/SolicitudAsociacion.jsp").forward(req, resp);
                                } else {
                                    solicitud.setIntento(solicitud.getIntento() + 1);
                                    solicitud.setEstado(SolicitudAsociasion.ESTADO_SOLICITUD_3);
                                    modelSolicitud.ActualizarSolicitud(solicitud);
                                    req.setAttribute("success", 2);
                                    req.setAttribute("intentos","Intentos disponibles: "+String.valueOf(3-solicitud.getIntento()));
                                    req.getRequestDispatcher("/Solicitudes/SolicitudAsociacion.jsp").forward(req, resp);
    
                                }
                            }
                        }
                    } else {
                        req.setAttribute("success", 1);
                        req.setAttribute("errores", "El numero de DPI no es del propietario de la cuenta");
                        req.getRequestDispatcher("/Solicitudes/SolicitudAsociacion.jsp").forward(req, resp);
                    }
                } else {
                    req.setAttribute("success", 1);
                    req.setAttribute("errores", "No existe un cuenta con el numero ingresado");
                    req.getRequestDispatcher("/Solicitudes/SolicitudAsociacion.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                System.out.println("Error en post controlador solicitud: " + e.getMessage());
                req.setAttribute("success", 1);
                req.setAttribute("errores", e.getMessage());
                req.getRequestDispatcher("/Solicitudes/SolicitudAsociacion.jsp").forward(req, resp);
                e.printStackTrace();
            }
        }

    }

}
