///             Resume Function             ///
//Click alert Trigger
const alertTrigger = document.getElementById("resumeBTN");

if (alertTrigger) {
    alertTrigger.addEventListener("click", () => {
        appendAlert();
    });
}

//Alert Append/Creation
function appendAlert() {
    //Creo el mensaje despues de recuperar los valores actuales del formulario.
    var message = resumeAlert();

    //Creacion de la alerta
    var alert = document.createElement("div");
    alert.classList.add("alert", "alert-primary", "alert-dismissible");
    alert.setAttribute("role", "alert");

    //Creacion icono de billete
    var icon = document.createElement("i");
    icon.classList.add("bi", "bi-cash");
    icon.innerHTML = message;

    //Creacion boton de cerrado
    var closeBtn = document.createElement("button");
    closeBtn.classList.add("btn-close");
    closeBtn.type = "button";
    closeBtn.setAttribute("data-bs-dismiss", "alert");
    closeBtn.setAttribute("aria-label", "Close");

    //Carga conjunta de icono y creacion dentro de la Alerta.
    alert.appendChild(icon);
    alert.appendChild(closeBtn);

    //Recupera Div con id
    var alertPlaceholder = document.getElementById("alertLiveResume");

    //Elimina toda alerta anterior a la que se va a crear, revisa si existe alguna en el Div
    if (alertPlaceholder.firstChild) {
        alertPlaceholder.removeChild(alertPlaceholder.firstChild);
    }

    //Carga la alerta en el div
    alertPlaceholder.appendChild(alert);

    //Duracion de la alerta, 10 segundos
    setTimeout(function () {
        alertPlaceholder.removeChild(alert);
    }, 5000);
}

//Funcion para obtener total con o sin descuento.
function resumeAlert() {
    var quantity = document.getElementById("amountTicket").value;
    var category = document.getElementById("categoryTicket").value;
    var subTotal = 200 * quantity;
    var message = " Total a Pagar: $";
    switch (category) {
        case "estudiante":
            return message + (subTotal - subTotal * 0.8);

        case "trainee":
            return message + (subTotal - subTotal * 0.5);

        case "junior":
            return message + (subTotal - subTotal * 0.15);

        default:
            return message + subTotal;
    }
}
