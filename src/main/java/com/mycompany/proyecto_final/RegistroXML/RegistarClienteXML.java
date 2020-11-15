package com.mycompany.proyecto_final.RegistroXML;

import com.mycompany.proyecto_final.Entidades.Cliente;
import com.mycompany.proyecto_final.Entidades.CuentaBancaria;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelCliente;
import com.mycompany.proyecto_final.Models.ModelFotocopiaDPF;
import com.mycompany.proyecto_final.Models.ModelUsuarioSistema;
import com.mycompany.proyecto_final.RegistroXML.ValidacionDatos.ValidarCliente;
import java.util.ArrayList;
import java.util.List;

public class RegistarClienteXML {
    
    private ModelFotocopiaDPF modelFotocopiaDPF = new ModelFotocopiaDPF();
    private ModelUsuarioSistema modelUsuario = new ModelUsuarioSistema();
    private List<String> errores = new ArrayList<>();
    private ModelCliente modelCliente = new ModelCliente();
    private ValidarCliente validarClinte = new ValidarCliente();
    
    private RegistroCuentaBancariaXML bancariaXML = new RegistroCuentaBancariaXML(modelCliente,errores);
    
    public RegistarClienteXML() {
    }

    public void realizarRegistro(Cliente cliente) {
        String respuesta = this.validarClinte.validarCliente(cliente);
        if (respuesta.isEmpty()) {
            UsuarioDeSistema user;
            try {
                user=modelUsuario.buscarUsuario(cliente.getCodigo());
                if(user==null){
                    modelUsuario.RegistroUsuarioSistemaExportado((UsuarioDeSistema)cliente);
                    modelCliente.RegistroCliente(cliente);
                    modelFotocopiaDPF.AgregarFotocopia(cliente);
                    for(CuentaBancaria cuenta:cliente.getCuentas()){
                        this.bancariaXML.realizarRegistro(cuenta);
                    }
                }else{
                    errores.add("- Cliente con codigo: " + cliente.getCodigo().toString() + " no puede ser registrado debido a que existe otra entidad registrada con ese codigo");
                }
            } catch (Exception ex) {
                System.out.println("Error registro Cliente: "+ex.getMessage());
                errores.add("- Cliente con codigo: " + cliente.getCodigo().toString() + " "+ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            errores.add("- Cliente con codigo: " + cliente.getCodigo().toString() + " tiene los siguintes errores:" + respuesta);
        }
    }

    public List<String> getErrores() {
        return errores;
    }
    
    
}
