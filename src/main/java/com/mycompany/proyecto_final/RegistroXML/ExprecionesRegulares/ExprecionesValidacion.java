
package com.mycompany.proyecto_final.RegistroXML.ExprecionesRegulares;

public class ExprecionesValidacion {

    public ExprecionesValidacion() {
    }
    /**
     * REALIZA LA VALIDACION DE UN CODIGO NUMERICO
     * @param codigo
     * @return 
     */
    public boolean validarCodigoNumerico(Long codigo) {
        if (codigo == null) {
            return false;
        } else {
            return codigo.toString().matches("^[0-9]+$");
        }
    }
    
    /**
     * REALIZA LA VALIDACION DE UN NUMERO DE DPI
     * @param DPI
     * @return 
     */
    public boolean validarDPI(String DPI) {
        if (DPI == null) {
            return false;
        } else {
            return DPI.matches("^[0-9]{13}$");
        }
    }
    /**
     * REALIZA LA VALIDACION DEL SEXO
     * @param sexo
     * @return 
     */
    public boolean validarSexo(String sexo) {
        if (sexo == null) {
            return false;
        } else {
            if (sexo.equals("Masculino") || sexo.equals("Femenino")) {
                return true;
            } else {
                return false;
            }
        }
    }
    /**
     * VALIDA EL TIPO DE TRANSACCION
     * @param tipoTransaccion
     * @return 
     */
    public boolean validarTipoTransaccion(String tipoTransaccion) {
        if (tipoTransaccion == null) {
            return false;
        } else {
            if (tipoTransaccion.equals("CREDITO") || tipoTransaccion.equals("DEBITO")) {
                return true;
            } else {
                return false;
            }
        }
    }
    /**
     * REALIZA LA VALIDACION DEL TURNO
     * @param turno
     * @return 
     */
    public boolean validarTurno(String turno) {
        if (turno == null) {
            return false;
        } else {
            if (turno.equals("MATUTINO") || turno.equals("VESPERTINO")) {
                return true;
            } else {
                return false;
            }
        }
    }
    /**
     * REALIZA EL VALIDADICACION DE UN MONTO DE DINERO
     * @param monto
     * @return 
     */
    public boolean validarCosto(Double monto) {
        if (monto == null) {
            return false;
        } else {
            return monto.toString().matches("^(([0-9]+\\.[0-9]+)|([0-9]+))$");
        }
    }
}
