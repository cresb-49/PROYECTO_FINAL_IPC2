package com.mycompany.proyecto_final.RegistroXML;

import com.mycompany.proyecto_final.Entidades.Cajero;
import com.mycompany.proyecto_final.Entidades.CuentaBancaria;
import com.mycompany.proyecto_final.Entidades.Transaccion;
import com.mycompany.proyecto_final.Models.ModelCajero;
import com.mycompany.proyecto_final.Models.ModelCliente;
import com.mycompany.proyecto_final.Models.ModelCuentaBancaria;
import com.mycompany.proyecto_final.Models.ModelTransaccion;
import com.mycompany.proyecto_final.RegistroXML.ValidacionDatos.ValidarTransaccion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlo
 */
public class RegistarTransaccionXML {
    private ModelTransaccion modelTransaccion = new ModelTransaccion();
    private ModelCuentaBancaria modelCuenta = new ModelCuentaBancaria();
    private ModelCajero modelCajero = new ModelCajero();
    private ValidarTransaccion validar = new ValidarTransaccion();
    private List<String> errores = new ArrayList<>();
    public RegistarTransaccionXML() {

    }

    public void realizarRegistro(Transaccion transaccion) {
        String respuesta = this.validar.validarTransaccion(transaccion);
        if (respuesta.isEmpty()) {
            try {
                Transaccion transaccion1 = modelTransaccion.BuscarTransaccion(transaccion.getCodigo().toString());
                
                if(transaccion1 == null){
                    CuentaBancaria cuenta = modelCuenta.BuscarCuenta(transaccion.getIdCuenta().toString());
                    if(cuenta==null){
                        errores.add("- Transaccion con codigo: " + transaccion.getCodigo().toString() + " no puede ser registrada debido a que la cuenta: "+transaccion.getIdCuenta()+" no existe");
                    }else{
                        Cajero cajero = modelCajero.ObtenerCajero(transaccion.getIdCajero().toString());
                        if(cajero==null){
                            errores.add("- Transaccion con codigo: " + transaccion.getCodigo().toString() + " no puede ser registrada debido a que el cajero: "+transaccion.getIdCajero()+" no existe");
                        }else{
                            modelTransaccion.RegistrarTransaccionExportada(transaccion);
                        }
                    }
                }else{
                    errores.add("- Transaccion con codigo: " + transaccion.getCodigo().toString() + " no puede ser registrada debido a que existe otra transaccion registrada con ese codigo");
                }
            } catch (Exception e) {
                System.out.println("Error registro transaccion: "+e.getMessage());
                errores.add("- Transaccion con codigo: " + transaccion.getCodigo().toString() + " "+e.getMessage());
                e.printStackTrace();
            }
        }else{
            errores.add("- Transaccion con codigo: " + transaccion.getCodigo().toString() + " tiene los siguintes errores:" + respuesta);
        }
    }

    public List<String> getErrores() {
        return errores;
    }
    
}
