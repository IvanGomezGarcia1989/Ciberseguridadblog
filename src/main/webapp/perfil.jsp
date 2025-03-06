<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mi Perfil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp" %>
    <div class="container mt-5">
        <h1 class="text-center">Mi Perfil</h1>
        
        <!-- Mensajes de éxito/error -->
        <c:if test="${not empty success}">
            <div class="alert alert-success">${success}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <!-- Formulario de edición de perfil -->
        <form action="perfil" method="post">
            <div class="mb-3">
                <label class="form-label">Nombre de usuario</label>
                <input type="text" class="form-control" value="${usuario.nombreUsuario}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">Nombre</label>
                <input type="text" class="form-control" name="nombre" value="${usuario.nombre}" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Apellidos</label>
                <input type="text" class="form-control" name="apellidos" value="${usuario.apellidos}" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Fecha de nacimiento</label>
                <input type="date" class="form-control" name="fechaNacimiento" value="${usuario.fechaNacimiento}" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Correo electrónico</label>
                <input type="email" class="form-control" name="email" value="${usuario.email}" required>
            </div>
            <button type="submit" class="btn btn-outline-primary">Guardar Cambios</button>
        </form>

        <!-- Formulario para cambiar contraseña -->
        <h3 class="mt-5">Cambiar Contraseña</h3>
        <form action="cambiarPassword" method="post">
            <div class="mb-3">
                <label class="form-label">Contraseña Actual</label>
                <input type="password" class="form-control" name="passwordActual" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Nueva Contraseña</label>
                <input type="password" class="form-control" name="nuevaPassword" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Confirmar Nueva Contraseña</label>
                <input type="password" class="form-control" name="confirmarPassword" required>
            </div>
            <button type="submit" class="btn btn-outline-warning">Cambiar Contraseña</button>
        </form>
    </div>
</body>
</html>