package com.mycompany.proyecto_final.RegistroXML;

import com.mycompany.proyecto_final.Entidades.Cajero;
import com.mycompany.proyecto_final.Entidades.Gerente;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelGerente;
import com.mycompany.proyecto_final.Models.ModelUsuarioSistema;
import com.mycompany.proyecto_final.RegistroXML.ValidacionDatos.ValidarTrabajador;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrarGerenteXML {
    
    private ModelGerente modelGerente = new ModelGerente();
    private ModelUsuarioSistema modelUsuario = new ModelUsuarioSistema();
    private List<String> errores = new ArrayList<>();
    private ValidarTrabajador validar = new ValidarTrabajador();

    public RegistrarGerenteXML() {
    }
    
    public void realizarRegistro(Gerente gerente) {
        String respuesta = this.validar.validarGerente(gerente);
        if (respuesta.isEmpty()) {
            UsuarioDeSistema user;
            try {
                user = modelUsuario.buscarUsuario(gerente.getCodigo());
                if (user == null) {
                    modelUsuario.RegistroUsuarioSistemaExportado((UsuarioDeSistema)gerente);
                    modelGerente.RegistroGerente(gerente);
                } else {
                    errores.add("- Gerente con codigo: " + gerente.getCodigo().toString() + " no puede ser registrado debido a que existe otra entidad registrada con ese codigo");
                }
            } catch (SQLException ex) {
                System.out.println("Error registro gerente: "+ex.getMessage());
                errores.add("- gerente con codigo: " + gerente.getCodigo().toString() + " "+ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            errores.add("- Gerente con codigo: " + gerente.getCodigo().toString() + " tiene los siguintes errores:" + respuesta);
        }
    }

    public List<String> getErrores() {
        return errores;
    }
}
