package com.mycompany.proyecto_final.LecturaXML;

import com.mycompany.proyecto_final.Entidades.Archivo;
import com.mycompany.proyecto_final.Entidades.Cliente;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author carlo
 */
public class AsignarDocumentos {

    private List<Archivo> Archivos;

    public AsignarDocumentos(List<Archivo> archivos) {
        this.Archivos = archivos;
    }

    public void asignarFotocopia(List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            String nombre = cliente.getFotocopiaDPI().getNombre();
            Archivo arch = this.buscarArchivoPorNombre(nombre);
            if (arch != null) {
                InputStream datos = arch.getDatos();
                cliente.setFotocopiaDPI(new Archivo(nombre, datos));
            }
        }
    }

    public Archivo buscarArchivoPorNombre(String nombre) {

        for (Archivo arch : Archivos) {
            if (arch.getNombre().equals(nombre)) {
                return arch;
            }
        }
        return null;
    }
}
