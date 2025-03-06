<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="listarArticulos">Blog de Ciberseguridad</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <c:choose>
                    <c:when test="${not empty usuario}">
                        <!-- Opciones para usuarios logueados -->
                        <li class="nav-item">
                            <a class="nav-link" href="listarArticulos">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="publicar.jsp">Publicar Artículo</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="perfil">Mi Perfil</a>
                        </li>
                        <c:if test="${usuario.esAdmin()}">
						    <li class="nav-item">
						        <a class="nav-link" href="admin">Administración</a>
						    </li>
						</c:if>
                        <li class="nav-item">
                            <a class="nav-link" href="logout">Cerrar Sesión</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <!-- Opciones para usuarios no logueados -->
                        <li class="nav-item">
                            <a class="nav-link" href="listarArticulos">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="login.jsp">Iniciar Sesión</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="registro.jsp">Registrarse</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>