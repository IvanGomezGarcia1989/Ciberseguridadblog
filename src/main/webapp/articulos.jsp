<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Artículos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp" %>
    <div class="container mt-5 col-6">
        <h1 class="text-center">Artículos Publicados</h1>
        <c:if test="${not empty usuario}">
        	<a href="publicar.jsp" class="btn btn-outline-success mb-3">Nuevo Artículo</a>
		</c:if>
        
		<c:forEach items="${articulos}" var="articulo">
		    <div class="card mb-3">
		        <!-- Botones de Editar y Eliminar -->
		        <div class="card-header">
		            <c:if test="${usuario.nombreUsuario == articulo.nombreUsuario}">
		                <div class="d-flex justify-content-start">
		                    <a href="editarArticulo?id=${articulo.id}" class="btn btn-outline-warning btn-sm me-2">Editar</a>
		                    <a href="eliminarArticulo?id=${articulo.id}" class="btn btn-outline-danger btn-sm">Eliminar</a>
		                </div>
		            </c:if>
		        </div>
		        
		        <!-- Contenido del artículo -->
		        <div class="card-body">
		            <h5 class="card-title">${articulo.titulo}</h5>
		            <p class="card-text">${articulo.contenido}</p>
		            <c:if test="${not empty articulo.imagen}">
		                <img src="${articulo.imagen}" class="img-fluid mb-3" alt="Imagen del artículo">
		            </c:if>
		            <p class="text-muted">Publicado por: ${articulo.nombreUsuario} | ${articulo.fechaPublicacion}</p>
		            
		            <!-- Likes -->
		            <div class="mb-3">
		                <form action="like" method="post">
		                    <input type="hidden" name="articuloId" value="${articulo.id}">
		                    <button type="submit" class="btn btn-outline-primary">
		                        Likes: ${articulo.likes}
		                    </button>
		                </form>
		            </div>
		            
		            <!-- Comentarios -->
		            <div class="mt-3">
		                <h6>Comentarios:</h6>
		                <c:forEach items="${articulo.comentarios}" var="comentario">
		                    <div class="card mb-2">
		                        <div class="card-body">
		                            <p class="card-text">${comentario.contenido}</p>
		                            <p class="text-muted">${comentario.nombreUsuario} | ${comentario.fechaComentario}</p>
		                        </div>
		                    </div>
		                </c:forEach>
		                
		                <!-- Formulario de comentario -->
		                <form action="comentar" method="post">
		                    <input type="hidden" name="articuloId" value="${articulo.id}">
		                    <div class="mb-3">
		                        <textarea class="form-control" name="contenido" placeholder="Escribe un comentario..." required></textarea>
		                    </div>
		                    <button type="submit" class="btn btn-outline-secondary">Comentar</button>
		                </form>
		            </div>
		        </div>
		    </div>
		</c:forEach>
    </div>
</body>
</html>