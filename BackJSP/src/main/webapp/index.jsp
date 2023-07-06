<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
	</style>

	<jsp:include page="header.jsp"/>

	<% if(request.getAttribute("loginError") != null ) {
			boolean loginError = (boolean) request.getAttribute("loginError");
			if (loginError) {%>
		<div class="alert alert-danger alert-dismissible fade show" role="alert">
			Error al iniciar de sesión, reintente.
			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
	<% 	}}%>

	<% if(request.getAttribute("registerError") != null ) {
		boolean registerError = (boolean) request.getAttribute("registerError");
		if (registerError) {%>
	<div class="alert alert-danger alert-dismissible fade show" role="alert">
		Error al registrarse, reintente.
		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	</div>
	<% 	}}%>


	<% if(request.getAttribute("noRegisterError") != null ) {
		boolean nrError = (boolean) request.getAttribute("noRegisterError");
		if (nrError) {%>
	<div class="alert alert-danger alert-dismissible fade show" role="alert">
		No esta registrado para realizar la compra.
		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	</div>
	<% 	}}%>

	<% if(request.getAttribute("buyError") != null ) {
		boolean nrError = (boolean) request.getAttribute("noRegisterError");
		if (nrError) {%>
	<div class="alert alert-danger alert-dismissible fade show" role="alert">
		Error al realizar la compra, reintente.
		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	</div>
	<% 	}}%>

	<div id="Home">

		<section>
			<div class="container-fluid"
				style="background-image: url('Assets/Carousel/ba1.jpg'); background-size: cover; height: 500px; color: white;">
				<div class="d-flex justify-content-end align-items-center h-100 me-5">
					<div class="text-end w-50 me-5">
						<h2>Conf Bs As</h2>
						<p>
							Bs As llega por primera vez a Argentina. Un evento para compartir con nuestra comunidad el
							conocimiento
							y experiencia de los expertos que están creando el futuro de Internet. Ven a conocer a
							miembros
							del
							evento, a otros estudiantes de Codo a Codo y los oradores de primer nivel que tenemos para
							para
							ti.
							Te
							esperamos!
						</p>
						<button type="button" class="btn btn-outline-light">Quiero ser orador</button>
						<a type="button" class="btn btn" onclick="changeBuyTK()"
							style="background:  #2ca444; color: white;">Comprar
							tickets</a>
					</div>
				</div>
			</div>
		</section>

		<!--Oradores-->
		<section>
			<div class="container-fluid">
				<div class="text-center">
					<small>CONOCE A LOS</small>
					<h2>ORADORES</h2>
				</div>
				<div class="card-group mb-4 mx-5">
					<div class="card mx-3">
						<img src="Assets/OraCard/steve.jpg" class="card-img-top" alt="...">
						<div class="card-body">
							<span class="badge text-bg-warning">Javascript</span>
							<span class="badge text-bg-info text-light">React</span>
							<h5 class="card-title">Steve Jobs</h5>
							<p class="card-text">
								<small>
									Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
									incididunt ut
									labore
									et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
									laboris nisi
									ut
									aliquip ex ea commodo consequat.
								</small>
							</p>
						</div>
					</div>

					<div class="card mx-3">
						<img src="Assets/OraCard/bill.png" class="card-img-top" alt="...">
						<div class="card-body">
							<span class="badge text-bg-warning">Javascript</span>
							<span class="badge text-bg-info text-light">React</span>
							<h5 class="card-title">Bill Gates</h5>
							<p class="card-text">
								<small>
									Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
									incididunt ut
									labore
									et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
									laboris nisi
									ut
									aliquip ex ea commodo consequat.
								</small>
							</p>
						</div>
					</div>

					<div class="card mx-3">
						<img src="Assets/OraCard/ada.jpeg" class="card-img-top" alt="...">
						<div class="card-body">
							<span class="badge text-bg-secondary text-light">Negocios</span>
							<span class="badge text-bg-danger text-light">Startups</span>
							<h5 class="card-title">Ada Lovelace</h5>
							<p class="card-text">
								<small>
									Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
									incididunt ut
									labore
									et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
									laboris nisi
									ut
									aliquip ex ea commodo consequat.
								</small>
							</p>
						</div>
					</div>
				</div>
			</div>
		</section>

		<!--Horizontal Card-->
		<section>
			<div class="card mb-3 bg-dark">
				<div class="row g-0">
					<div class="col-md-6">
						<img src="Assets/SimpleCard/honolulu.jpg" class="img-fluid rounded-start" alt="...">
					</div>
					<div class="col-md-6 text-light">
						<div class="card-body">
							<h5 class="card-title">BS AS - Octubre</h5>
							<p class="card-text">
								<small>
									Buenos Aires es la provincia y localidad más grande del estado de
									Argentina. En los Estados Unidos. Honolulu es la más sureña de entre las principales
									ciudades estadounidenses. Aunque el nombre de Honolulu se refiere al área urbana en
									la
									costa
									sureste de la isla de Oahu, la ciudad y el condado de Honolulu han formado una
									ciudad
									condado consolidada que cubre toda la ciudad (aproximadamente 600 km? de
									superficie).
								</small>
							</p>
							<button type="button" class="btn btn-outline-light">Conoce mas</button>
						</div>
					</div>
				</div>
			</div>
		</section>

		<!--Orador Form sin action-->
		<section>
			<div class="d-flex justify-content-center">
				<form class="row g-2 w-50 was-validated" action="" method="" validated>
					<div class="text-center">
						<small>CONVIÉRTETE EN UN</small>
						<h2>ORADOR</h2>
						<p>Anótate como orador para dar una <abbr
								title="Se refiere a un tipo de presentación breve y concisa, donde el presentador tiene un tiempo limitado">charla
								ignite</abbr>. Cuéntanos de qué quieres hablar!</p>
					</div>
					<div class="form-floating mb-3 col-sm-6">
						<input type="text" class="form-control" id="floatingOraName" placeholder="Nombre" required>
						<label for="floatingOraName">Nombre</label>
					</div>
					<div class="form-floating mb-3 col-sm-6">
						<input type="text" class="form-control" id="floatingOraLastname" placeholder="Apellido" required>
						<label for="floatingOraLastname">Apellido</label>
					</div>
					<div class="form-floating col">
						<textarea class="form-control form-control-lg" placeholder="Sobre qué quieres hablar?"
							id="floatingTextarea" required></textarea>
						<label for="floatingTextarea">Sobre qué quieres hablar?"</label>
						<div class="invalid-feedback">
							<small>Recuerda incluir un titulo para tu charla.</small>
						</div>
					</div>
					<button type="submit" class="btn btn-success mb-3"
						style="background-color: #94cc3c;">Enviar</button>
				</form>
			</div>
		</section>

	</div>

	<div id="BuyTK">

		<!--Descuentos-->
		<section class="text-center mb-4">
			<h2>DESCUENTOS</h2>
			<div class="d-flex justify-content-center">

				<div class="row row-cols-1 row-cols-md-3 g-1 w-50">
					<div class="col">
						<div class="card border-primary">
							<div class="card-body">
								<h5 class="card-title">Estudiante</h5>
								<p class="card-text">
									Tiene un descuento.
								</p>
								<p class="h2">80%</p>
								<footer>
									<p class="card-text"><small class="text-body-secondary">* presentar
											documentación</small></p>
								</footer>
							</div>
						</div>
					</div>

					<div class="col">
						<div class="card border-info">
							<div class="card-body">
								<h5 class="card-title">Trainee</h5>
								<p class="card-text">Tiene un descuento.</p>
								<p class="h2">50%</p>
								<footer>
									<p class="card-text"><small class="text-body-secondary">* presentar
											documentación</small></p>
								</footer>
							</div>
						</div>
					</div>

					<div class="col">
						<div class="card border-warning">
							<div class="card-body">
								<h5 class="card-title">Junior</h5>
								<p class="card-text">Tiene un descuento.</p>
								<p class="h2">15%</p>
								<footer>
									<p class="card-text"><small class="text-body-secondary">* presentar
											documentación</small></p>
								</footer>
							</div>
						</div>
					</div>
				</div>

			</div>
		</section>

		<!--Ticker Form sin action-->
		<section>
			<div class="d-flex justify-content-center">
				<form class="row g-2 w-50 was-validated text-center" action="bTicket" method="post" validated>

					<small>VENTA</small>
					<h2>VALOR DE TICKET $200</h2>

					<div class="form-floating mb-3 col-sm-6">
						<input type="text" class="form-control" id="nameTicket" name="nameTicket" placeholder="Nombre" required>
						<label for="nameTicket">Nombre</label>
					</div>

					<div class="form-floating mb-3 col-sm-6">
						<input type="text" class="form-control" id="surnameTicket" name="surnameTicket" placeholder="Apellido" required>
						<label for="surnameTicket">Apellido</label>
					</div>

					<div class="form-floating mb-3">
						<input type="email" class="form-control" id="emailTicket" name="emailTicket" placeholder="Correo" required>
						<label for="emailTicket">Email</label>
					</div>

					<div class="form-floating mb-3 col-sm-6">
						<input type="number" class="form-control" id="floatingQuantity" name="amountTicket" placeholder="Cantidad" required>
						<label for="floatingQuantity">Cantidad</label>
					</div>

					<div class="form-floating mb-3 col-sm-6">
						<select class="form-select" id="floatingCategory" name="categoryTicket" required>
							<option value="" selected disabled>Elija una categoría</option>
							<option value="estudiante">Estudiante</option>
							<option value="trainee">Trainee</option>
							<option value="junior">Junior</option>
							<option value=" ">Ninguno de los anteriores</option>
						</select>
						<label for="floatingCategory">Categoría</label>
					</div>

					<!--ALERT RESUME-->
					<div id="alertLiveResume"></div>

					<div class="col-sm-12 mb-3">
						<div class="row gx-2">
							<div class="col-6 d-grid">
								<button type="button" class="btn" id="resumeBTN"
									style="background-color: #94cc3c; color: white;">Resumen</button>
							</div>
							<div class="col-6 d-grid">
								<button type="submit" class="btn"
										style="background-color: #94cc3c; color: white;">Comprar</button>
							</div>
						</div>
					</div>

				</form>
			</div>
		</section>

	</div>

	<!--Footer-->
	<section>
		<jsp:include page="footer.jsp"/>
	</section>

		<!-- Modal -->
		<div class="modal fade bs-modal" aria-hidden="true" id="modalLoginRegister">
			<div class="modal-dialog p-3">
				<div class="modal-content rounded-4 shadow">

				<!--NAV TABS-->
				<ul class="nav nav-pills nav-justified mb-3 gap-2" id="pills-tab" role="tablist">
					<li class="nav-item text-center">
						<a class="nav-link"  id="pills-login-tab" data-bs-toggle="pill" href="#pills-login" role="tab" aria-controls="login"
						><h6>Login</h6></a>
					</li>
					<li class="nav-item text-center">
						<a class="nav-link" id="pills-signUp-tab" data-bs-toggle="pill" href="#pills-signUp" role="tab" aria-controls="signUp"
						><h6>Sign Up</h6></a>
					</li>
				</ul>


				<div class="tab-content" id="pills-tabContent">

					<!--SIGNUP TAB-->
					<div class="tab-pane fade" id="pills-signUp" role="tabpanel" aria-labelledby="pills-signUp-tab">
					<form action="register" method="post" class="was-validated">
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="name" placeholder="Jorge" name="name" required>
								<label for="name">Nombre</label>
								<div class="invalid-feedback">
										Por favor ingrese su Nombre.
								</div>
						</div>

						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="surname" placeholder="Jorge" name="surname" required>
								<label for="surname">Apellido</label>
								<div class="invalid-feedback">
										Por favor ingrese su Apellido.
								</div>
						</div>

						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="dni" placeholder="45789123" name="dni" required>
								<label for="dni">D.N.I</label>
								<div class="invalid-feedback">
										Por favor ingrese su DNI (sin puntos).
								</div>
						</div>

						<div class="form-floating mb-3">
							<input type="email" class="form-control" id="emailReg" placeholder="email@gmail.com" name="emailReg" required>
								<label for="emailReg">Email</label>
								<div class="invalid-feedback">
										Por favor ingrese su Email.
								</div>
						</div>
						<div class="form-floating mb-3">
							<input type="password" class="form-control" id="passwordReg" placeholder="Jorge" name="passwordReg" required>
								<label for="passwordReg">Contraseña</label>
								<div class="invalid-feedback">
										Por favor ingrese su Contraseña.
								</div>
						</div>
						<div class="d-flex gap-2">
							<button type="submit" class="w-50 mb-2 btn btn-sm rounded-3 btn-warning">Registrarse</button>
							<button type="reset" class="w-50 mb-2 btn btn-sm rounded-3 btn-warning">Reset</button>
						</div>
					</form>
					</div>

					<!--LOGIN TAB-->
					<div class="tab-pane fade" id="pills-login" role="tabpanel" aria-labelledby="pills-login-tab">
					<form action="login" method="post" class="was-validated">
					<div class="form-floating mb-3">
							<input type="email" class="form-control" id="emailLog" placeholder="example@email.com" name="emailLog" required>
								<label for="emailLog">Email</label>
								<div class="invalid-feedback">
										Por favor ingrese su Email.
								</div>
						</div>
						<div class="form-floating mb-3">
							<input type="password" class="form-control" id="passwordLog" placeholder="Jorge" name="passwordLog" required>
								<label for="passwordLog">Contraseña</label>
								<div class="invalid-feedback">
										Por favor ingrese su Contraseña.
								</div>
						</div>
						<div class="d-flex gap-2">
							<button type="submit" class="w-50 mb-2 btn btn-sm rounded-3 btn-warning">Login</button>
							<button type="reset" class="w-50 mb-2 btn btn-sm rounded-3 btn-warning">Reset</button>
						</div>
					</form>
					</div>

					
				</div>
				</div>
			</div>
			</div>



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