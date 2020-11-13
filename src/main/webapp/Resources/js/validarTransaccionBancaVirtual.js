const exprecionesRegulares = {
    nDpi: /^[0-9]{13}$/,
    numeroEntero:/^[0-9]+$/,
    numeroDecimal: /^([0-9]+[\.][0-9]+|[0-9]+)$/,
};

function validarBancaVirtual(){
    var codigoCuenta = document.getElementById("codigoCuentaOrigen").value;
    var monto = document.getElementById("monto").value;
    var cuentaPropia = document.getElementById("codeCuentaPropia").value;
    var cuentaAsocia = document.getElementById("codeCuentaAsociada").value;

    var errores = "";

    if(codigoCuenta==="Seleccionar" || monto =="" || (cuentaPropia === "Seleccionar" && cuentaAsocia === "Seleccionar")){
        if((cuentaPropia === "Seleccionar" && cuentaAsocia === "Seleccionar")){
            alert("Debe de seleccionar una cuenta de destino");
            return false;
        }
        if(codigoCuenta === "Seleccionar"){
            alert("Debe de seleccionar una para debitar la cantidad");
            return false;
        }else{
            alert("Debe de introducir un monto de debito");
        }
    }else{

        if(exprecionesRegulares.numeroEntero.test(cuentaPropia) === true && exprecionesRegulares.numeroEntero.test(cuentaAsocia) === true){
            errores = errores +"\n- Solo debe de seleccionara una cuenta de destino";
        }
        if(cuentaPropia===codigoCuenta){
            errores = errores +"\n- La cuenta de destino y cuenta de retiro deben ser diferentes";
        }
        if(!exprecionesRegulares.numeroDecimal.test(monto)){
            errores = errores +"\n- La cantidad a debitar no es correcta";
        }
        if(!exprecionesRegulares.numeroEntero.test(codigoCuenta)){
            errores = errores +"\n- El numero de la cuenta a debitar solo debe de contener digitos";
        }
    }

    if (errores.length > 0) {
        alert(errores);
        return false;
    }

    return true;
}