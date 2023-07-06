<%@ page import="com.cybrixsystems.backjsp.Model.Ticket" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cybrixsystems.backjsp.Model.Sale" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	if(session.getAttribute("email") == null){
		response.sendRedirect("index.jsp");
	}
%>
<!DOCTYPE html>
<html lang="es">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Integrador Front</title>
	<link rel="icon" href="Assets/Brand/HeadIcon2.png">
	<link rel="stylesheet" href="Vendor/bootstrap-5.3.0-alpha3-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>

<body>

<style>
	#menuProfile{
		color: white;
		background-color: #212529;
	}
	.dropdown-item{
		color: #ffc107;
		background-color: #212529;
	}

	.dropdown-item:hover{
		color: white;
		background-color: #212529;
	}

	/*<!-- Modal Nav Style -->*/
	.nav-pills .nav-link.active{
		color: black;
		background-color: #ffc107;
	}
	.nav-pills .nav-link{
		color: #ffc107;
		background-color: black;
	}
	.nav-pills .nav-link:hover{
		color: white;
	}
	.nav-pills .nav-link.active:hover{
		color: black;
		background-color: #ffc107;
	}

	/*<!-- Modal Style -->*/
	.d-flex .btn:hover{
		color: white;
	}
    .modal-content{
        padding: 26px;
    }

    .bg-black {
        background: #000;
    }

    .skill-block {
        width: 30%;
    }

    @media (min-width: 991px) and (max-width:1200px) {
        .skill-block {
            padding: 32px !important;
        }
    }

    @media (min-width: 1200px) {
        .skill-block {
            padding: 56px !important;
        }
    }

</style>

<jsp:include page="header.jsp"/>

<% if (session.getAttribute("email") != null && session.getAttribute("loginOn") != null) {%>
    <div class="alert alert-success alert-dismissible fade show" role = "alert" >
            Login Exitoso !!!
        <button type = "button" class="btn-close" data-bs-dismiss= "alert" aria-label= "Close" ></button >
    </div >
<% } session.setAttribute("loginOn",null); %>

<% if (session.getAttribute("email") != null && session.getAttribute("ticketBuy") != null) {%>
<div class="alert alert-info alert-dismissible fade show" role = "alert" >
    Compra realizada con exito, revise sus compras y tickets en el panel correspondiente !!!
    <button type = "button" class="btn-close" data-bs-dismiss= "alert" aria-label= "Close" ></button >
</div >
<% } session.setAttribute("ticketBuy",null); %>

<div class="UserPanel">
    <div class="container-fluid">
        <div class="row">

            <div class="col-sm-auto bg-light sticky-top">
                <div class="d-flex flex-sm-column flex-row flex-nowrap bg-light align-items-center sticky-top">
                    <a class="d-block p-3 link-dark text-decoration-none" title="" data-bs-toggle="tooltip" data-bs-placement="right" data-bs-original-title="Icon-only">
                        <img src="${pageContext.request.contextPath}/Assets/Personal Logos Pack/Codo a Codo Edit.gif" class="img-thumbnail" style="height: 65px;">
                    </a>

                    <ul class="nav nav-pills nav-flush flex-sm-column flex-row flex-nowrap mb-auto mx-auto text-center align-items-center"
                        role="tablist" id="pills-tabHor" aria-orientation="vertical">
                        <li class="nav-item">
                            <a href="#" class="nav-link active py-3 px-2" type="button" role="tab" id="ownerData-tab"
                               data-bs-toggle="pill" data-bs-target="#ownerData" aria-controls="ownerData"
                               aria-selected="true"    data-bs-placement="right">
                                <i class="bi bi-person-badge-fill fs-1"></i>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="nav-link py-3 px-2" type="button" role="tab" id="petList-tab"
                               data-bs-toggle="pill" data-bs-target="#petList" aria-controls="petList"
                               aria-selected="false"    data-bs-placement="right">
                                <i class="bi bi-ticket-detailed fs-1"></i>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="nav-link py-3 px-2" type="button" role="tab" id="bookings-tab"
                               data-bs-toggle="pill" data-bs-target="#bookings" aria-controls="bookings"
                               aria-selected="false"    data-bs-placement="right" >
                                <i class="bi bi-credit-card-2-front fs-1"></i>
                            </a>
                        </li>

                    </ul>
                </div>
            </div>

            <div class="col-sm p-3 min-vh-100">
                <!-- content -->
                <!--USER DATA CONTENT-->
                <div class="tab-content" id="pills-tabHorContent">
                    <div class="tab-pane fade show active" id="ownerData" role="tabpanel" aria-labelledby="ownerData-tab" tabindex="0">
                        <div class="container mt-5 mb-5">
                            <div class="row no-gutters">
                                <div class="col-md-4 col-lg-4">
                                    <img src="${pageContext.request.contextPath}/Assets/Personal Logos Pack/Codo a Codo Edit.gif" style="width: 100%; height: 100%;">
                                </div>
                                <div class="col-md-8 col-lg-8">
                                    <div class="d-flex flex-column">
                                        <div class="d-flex flex-row justify-content-between align-items-center p-5 bg-dark text-white">
                                            <h3 class="display-5">${requestScope.get("user").getName()} </h3>
                                            <h3 class="display-5">${requestScope.get("user").getSurname()} </h3>
                                        </div>
                                        <div class="p-3 bg-black text-white">
                                            <h6>DNI: ${requestScope.get("user").getDni()}</h6>
                                            <h6>EMAIL: ${requestScope.get("user").getEmail()}</h6>
                                        </div>
                                        <div class="d-flex flex-row text-white">
                                            <div class="p-4 bg-primary text-center skill-block">
                                                <h4>340</h4>
                                                <h6>Tickets</h6>
                                            </div>
                                            <div class="p-3 bg-success text-center skill-block">
                                                <h4>175</h4>
                                                <h6>Compras</h6>
                                            </div>
                                            <div class="p-3 bg-warning text-center skill-block">
                                                <h4>305</h4>
                                                <h6>Eventos</h6>
                                            </div>
                                            <div class="p-3 bg-danger text-center skill-block">
                                                <h4>2</h4>
                                                <h6>Devoluciones o Cancelaciones</h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--TICKET LIST CONTENT-->
                    <div class="tab-pane" id="petList" role="tabpanel" aria-labelledby="petList-tab" tabindex="0">
                        <div class="container-fluid content-row">
                            <table class="table align-middle mb-0 bg-white">
                                <thead class="bg-light">
                                <tr>
                                    <th>Nº Ticket</th>
                                    <th>Precio</th>
                                    <th>Fecha Evento</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% List<Ticket> tList = (List<Ticket>) request.getAttribute("tList");
                                    for (Ticket t : tList){
                                %>
                                <tr>
                                    <td><%= t.getTicketNum() %></td>
                                    <td><%= t.getPrice() %></td>
                                    <td><%= t.getEventDate().toString() %></td>
                                </tr>
                                <% }%>
                                </tbody>
                            </table>
                        </div> <!-- /.card-content -->
                    </div>

                    <!--SALES CONTENT-->
                    <div class="tab-pane" id="bookings" role="tabpanel" aria-labelledby="bookings-tab" tabindex="0">
                        <table class="table align-middle mb-0 bg-white">
                            <thead class="bg-light">
                            <tr>
                                <th>N" Compra</th>
                                <th>Cantidad</th>
                                <th>Total Pagado</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% List<Sale> sList = (List<Sale>) request.getAttribute("sList");
                                for (Sale s : sList){
                            %>
                            <tr>
                                <td><%= s.getSaleNum() %></td>
                                <td><%= s.getAmount() %></td>
                                <td><%= s.getFinalPrice() %></td>
                            </tr>
                            <% }%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



	<!--Footer-->
	<section>
        <jsp:include page="footer.jsp"/>
	</section>

    <script>
		const menu =  document.getElementById("menuProfile")

		//En caso de no estarlo mantiene los originales en el HTML de arriba y dispara MODAL --NO TOCAR
			const signUpItem = document.getElementById("signUpItem")
			signUpItem.addEventListener('click', (e) => {
			document.getElementById("pills-signUp-tab").click()
		})

		const loginItem = document.getElementById("loginItem")
		loginItem.addEventListener('click', (e) => {
			document.getElementById("pills-login-tab").click()
		})
	</script>
	<script src="Vendor/bootstrap-5.3.0-alpha3-dist/js/bootstrap.bundle.min.js"></script>
	<script src="Script/Functions.js"></script>
	<script src="Script/Changes.js"></script>
</body>

</html>