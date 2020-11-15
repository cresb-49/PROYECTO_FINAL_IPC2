package com.mycompany.proyecto_final.RegistroXML;

import com.mycompany.proyecto_final.Entidades.Banco;
import com.mycompany.proyecto_final.Entidades.*;
import java.util.List;

public class RegistrarXMLDB {
    private RegistrarCajeroXML registrarCajeroXML = new RegistrarCajeroXML();
    private RegistarClienteXML registarClienteXML = new RegistarClienteXML();
    private RegistrarGerenteXML registrarGerenteXML = new RegistrarGerenteXML();
    private RegistarTransaccionXML registarTransaccionXML = new RegistarTransaccionXML();
    
    private List<String> erroresCajero;
    private List<String> erroresCliente;
    private List<String> erroresGerente;
    private List<String> erroresTransacciones;
    
    
    private Banco banco;
    /**
     * CONTRUCTOR VACIO DE LA ENTIDAD
     */
    public RegistrarXMLDB(Banco banco){
        this.banco = banco;
    }
    
    public void realizarRegistro(){
        for(Gerente gerente: this.banco.getGerentes()){
            this.registrarGerenteXML.realizarRegistro(gerente);
        }
        for(Cajero cajero:this.banco.getCajeros()){
            this.registrarCajeroXML.realizarRegistro(cajero);
        }
        for(Cliente cliente: this.banco.getClientes()){
            this.registarClienteXML.realizarRegistro(cliente);
        }
        for(Transaccion tran:this.banco.getTransaciones()){
            this.registarTransaccionXML.realizarRegistro(tran);
        }
        
        
        this.erroresCajero=this.registrarCajeroXML.getErrores();
        this.erroresCliente=this.registarClienteXML.getErrores();
        this.erroresGerente = this.registrarGerenteXML.getErrores();
        this.erroresTransacciones=this.registarTransaccionXML.getErrores();
        
        
    }
    
    
    public List<String> getErroresCajero() {
        return erroresCajero;
    }

    public List<String> getErroresCliente() {
        return erroresCliente;
    }

    public List<String> getErroresGerente() {
        return erroresGerente;
    }

    public List<String> getErroresTransacciones() {
        return erroresTransacciones;
    }
    
    
    
    
    
}
