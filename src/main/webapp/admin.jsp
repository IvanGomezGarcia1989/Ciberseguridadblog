<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel de Administración</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="header.jsp" %> <!-- Menú de navegación -->
    <div class="container mt-5">
        <h1 class="text-center">Panel de Administración</h1>
        
        <!-- Lista de Usuarios -->
        <h3 class="mt-4">Usuarios</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Nombre de Usuario</th>
                    <th>Rol</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${usuarios}" var="usuario">
                    <tr>
                        <td>${usuario.nombreUsuario}</td>
                        <td>${usuario.rol}</td>
                        <td>
                            <c:if test="${not usuario.esAdmin()}">
                                <a href="cambiarRol?nombreUsuario=${usuario.nombreUsuario}&rol=admin" class="btn btn-outline-warning btn-sm">Hacer Admin</a>
                                <a href="eliminarUsuario?nombreUsuario=${usuario.nombreUsuario}" class="btn btn-outline-danger btn-sm">Eliminar</a>
                            </c:if>
                            
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>