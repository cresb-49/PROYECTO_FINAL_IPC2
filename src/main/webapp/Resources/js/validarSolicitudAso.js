const exprecionesRegulares = {
    nDpi: /^[0-9]{13}$/,
    numeroEntero:/^[0-9]+$/,
    numeroDecimal: /^([0-9]+[\.][0-9]+|[0-9]+)$/,
};

function validarAso(){
    var cuenta = document.getElementById("numeroCuenta").value;
    var DPI = document.getElementById("numeroDPI").value;
    var nombre = document.getElementById("nombreCliente").value;

    var errores = "";

    if (cuenta === "" || DPI === "" || nombre === "") {
        alert("Todos los campos son obligatorios");
        return false;
    } else {
        if (!exprecionesRegulares.numeroEntero.test(cuenta)) {
            errores = errores + "\n- El numero de cuenta solo debe tener digitos";
        }
        if (!exprecionesRegulares.nDpi.test(DPI)) {
            errores = errores + "\n- El numero de DPI debe tener 13 digitos";
        }
    }

    if (errores.length > 0) {
        alert(errores);
        return false;
    }

    return true;
}