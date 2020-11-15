package com.mycompany.proyecto_final.RegistroXML;

import com.mycompany.proyecto_final.Entidades.Cliente;
import com.mycompany.proyecto_final.Entidades.CuentaBancaria;
import com.mycompany.proyecto_final.Models.ModelCliente;
import com.mycompany.proyecto_final.Models.ModelCuentaBancaria;
import com.mycompany.proyecto_final.RegistroXML.ValidacionDatos.ValidarCuentaBancaria;
import java.util.ArrayList;
import java.util.List;

public class RegistroCuentaBancariaXML {

    private ValidarCuentaBancaria validar = new ValidarCuentaBancaria();
    private ModelCuentaBancaria modelCuentaBancaria = new ModelCuentaBancaria();
    private ModelCliente modelCliente;
    private List<String> errores;

    public RegistroCuentaBancariaXML(ModelCliente modelCliente, List<String> errores) {
        this.modelCliente = modelCliente;
        this.errores = errores;
    }

    public void realizarRegistro(CuentaBancaria cuentaBancaria) {
        String respuesta = this.validar.validarCuentaBancaria(cuentaBancaria);
        if (respuesta.isEmpty()) {
            try {
                Cliente cliente = modelCliente.ObtenerCliente(cuentaBancaria.getIdCliente().toString());
                if(cliente == null){
                    errores.add("- Cuenta Bancaria con codigo: " + cuentaBancaria.getCodigo().toString() + " no se puede registrar porque el Cliente: "+cuentaBancaria.getIdCliente()+" no existe");
                }else{
                    CuentaBancaria cuenta = modelCuentaBancaria.BuscarCuenta(cuentaBancaria.getCodigo().toString());
                    if(cuenta == null){
                        modelCuentaBancaria.RegistrarCuenta(cuentaBancaria);
                    }else{
                        errores.add("- Cuenta Bancaria con codigo: " + cuentaBancaria.getCodigo().toString() + " no se puede registrar porque ya existe una cuenta registrada con ese codigo");
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error registro Cuenta Bencaria: " + ex.getMessage());
                errores.add("- Cuenta Bancaria con codigo: " + cuentaBancaria.getCodigo().toString() + " " + ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            errores.add("- Cuenta Bancaria con codigo: " + cuentaBancaria.getCodigo().toString() + " tiene los siguintes errores:" + respuesta);
        }
    }

}
