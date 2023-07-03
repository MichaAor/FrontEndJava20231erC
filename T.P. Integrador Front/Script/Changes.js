///             Dynamic WebApp Emulation           ///
//Ocultar pagina de compras.
window.onload = function () {
    var buyTK = document.getElementById("BuyTK");
    buyTK.style.display = "none";
};

//Cambio a Home
function changeHome() {
    var buyTK = document.getElementById("BuyTK");
    buyTK.style.display = "none";
    var home = document.getElementById("Home");
    home.style.display = "block";
}

//Cambio a pagina de compras
function changeBuyTK() {
    var buyTK = document.getElementById("Home");
    buyTK.style.display = "none";
    var home = document.getElementById("BuyTK");
    home.style.display = "block";
}
