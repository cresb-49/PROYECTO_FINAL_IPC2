package com.mycompany.proyecto_final.RegistroXML;

import com.mycompany.proyecto_final.Entidades.Cajero;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelCajero;
import com.mycompany.proyecto_final.Models.ModelUsuarioSistema;
import com.mycompany.proyecto_final.RegistroXML.ErroresInformacion.ErroresEntidades;
import com.mycompany.proyecto_final.RegistroXML.ValidacionDatos.ValidarTrabajador;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrarCajeroXML {

    private ModelCajero modelCajero = new ModelCajero();
    private ModelUsuarioSistema modelUsuario = new ModelUsuarioSistema();
    private List<String> errores = new ArrayList<>();
    private ValidarTrabajador validarCajero = new ValidarTrabajador();

    public RegistrarCajeroXML() {

    }

    public void realizarRegistro(Cajero cajero) {
        String respuesta = this.validarCajero.validarCajero(cajero);
        if (respuesta.isEmpty()) {
            UsuarioDeSistema user;
            try {
                user = modelUsuario.buscarUsuario(cajero.getCodigo());
                if (user == null) {
                    modelUsuario.RegistroUsuarioSistemaExportado((UsuarioDeSistema)cajero);
                    modelCajero.RegistroCajero(cajero);
                } else {
                    errores.add("- Cajero con codigo: " + cajero.getCodigo().toString() + " no puede ser registrado debido a que existe otra entidad registrada con ese codigo");
                }
            } catch (SQLException ex) {
                System.out.println("Error registro Cajero: "+ex.getMessage());
                errores.add("- Cajero con codigo: " + cajero.getCodigo().toString() + " "+ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            errores.add("- Cajero con codigo: " + cajero.getCodigo().toString() + " tiene los siguintes errores:" + respuesta);
        }
    }

    public List<String> getErrores() {
        return errores;
    }
}
