<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Artículo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Editar Artículo</h1>
        
        <!-- Mensaje de error -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <form action="editarArticulo" method="post">
            <input type="hidden" name="id" value="${articulo.id}">
            <div class="mb-3">
                <label for="titulo" class="form-label">Título</label>
                <input type="text" class="form-control" id="titulo" name="titulo" value="${articulo.titulo}" required>
            </div>
            <div class="mb-3">
                <label for="contenido" class="form-label">Contenido</label>
                <textarea class="form-control" id="contenido" name="contenido" rows="5" required>${articulo.contenido}</textarea>
            </div>
            <div class="mb-3">
                <label for="imagen" class="form-label">URL de la Imagen (opcional)</label>
                <input type="text" class="form-control" id="imagen" name="imagen" value="${articulo.imagen}">
            </div>
            <button type="submit" class="btn btn-outline-primary">Guardar Cambios</button>
        </form>
    </div>
</body>
</html>