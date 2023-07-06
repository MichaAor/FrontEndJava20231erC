<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand ms-5" onclick="changeHome()" href="${pageContext.request.contextPath}/">
                <img src="${pageContext.request.contextPath}/Assets/Brand/CodoACodoBrand.png" alt="Logo" width="100" height="70">
                Conf Bs As
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <% if (session.getAttribute("email") == null) {%>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="">La conferencia</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Los oradores</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">El lugar y la fecha</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Conviértete en orador</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link link-success" onclick="changeBuyTK()" href="#">Compra Tickets</a>
                    </li>
                    <li class="dropstart">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="bi bi-person-circle"></i>
                        </a>
                        <ul class="dropdown-menu text-small shadow" id="menuProfile">
                            <li><a class="dropdown-item" id="signUpItem" data-bs-toggle="modal" data-bs-target=".bs-modal" role="button">Sign Up</a></li>
                            <li><a class="dropdown-item" id="loginItem" data-bs-toggle="modal" data-bs-target=".bs-modal" role="button">Login</a></li>
                        </ul>
                    </li>
                    <% }else{ %>
                    <li class="dropstart">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="bi bi-person-circle"></i>
                        </a>
                        <ul class="dropdown-menu text-small shadow" id="">
                            <li><a class="dropdown-item" id="userPanel" role="button" href="userPanel">User Panel</a></li>
                            <li><a class="dropdown-item" id="logout" role="button" href="logout">Logout</a></li>
                        </ul>
                    </li>
                    <% }%>
                </ul>
            </div>
        </div>
    </nav>
</header>