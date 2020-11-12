const exprecionesRegulares = {
    nDpi: /^[0-9]{13}$/,
    numeroEntero:/^[0-9]+$/,
    numeroDecimal: /^([0-9]+[\.][0-9]+|[0-9]+)$/,
};

function validarRetiroForm() {
    var cuenta = document.getElementById("numeroCuenta").value;
    var DPI = document.getElementById("numeroDPI").value;
    var monto = document.getElementById("montoDebitar").value;

    var errores = "";

    if (cuenta === "" || DPI === "" || monto === "") {
        alert("Todos los campos son obligatorios");
        return false;
    } else {
        if (!exprecionesRegulares.numeroEntero.test(cuenta)) {
            errores = errores + "\n- El numero de cuenta solo debe tener digitos";
        }
        if (!exprecionesRegulares.nDpi.test(DPI)) {
            errores = errores + "\n- El numero de DPI debe tener 13 digitos";
        }
        if (!exprecionesRegulares.numeroDecimal.test(monto)) {
            errores = errores + "\n- El monto de retiro es incorrecto";
        }
    }

    if (errores.length > 0) {
        alert(errores);
        return false;
    }

    return true;
}

function validarAbonoForm() {
    var cuenta = document.getElementById("numeroCuenta").value;
    var monto = document.getElementById("montoDepositar").value;

    var errores = "";

    if (cuenta === "" || monto === "") {
        alert("Todos los campos son obligatorios");
        return false;
    } else {
        if (!exprecionesRegulares.numeroEntero.test(cuenta)) {
            errores = errores + "\n- El numero de cuenta solo debe tener digitos";
        }
        if (!exprecionesRegulares.numeroDecimal.test(monto)) {
            errores = errores + "\n- El monto de abono es incorrecto";
        }
    }

    if (errores.length > 0) {
        alert(errores);
        return false;
    }

    return true;
}