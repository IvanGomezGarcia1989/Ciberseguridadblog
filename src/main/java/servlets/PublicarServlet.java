package servlets;

import dao.ArticuloDAO;
import modelo.Articulo;
import modelo.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/publicar")
public class PublicarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Verificar si el usuario está autenticado
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String titulo = request.getParameter("titulo");
        String contenido = request.getParameter("contenido");
        String imagen = request.getParameter("imagen");

        // Validación básica
        if (titulo == null || titulo.trim().isEmpty() || contenido == null || contenido.trim().isEmpty()) {
            request.setAttribute("error", "El título y el contenido son obligatorios.");
            request.getRequestDispatcher("publicar.jsp").forward(request, response);
            return;
        }

        // Crear el artículo
        Articulo articulo = new Articulo();
        articulo.setTitulo(titulo);
        articulo.setContenido(contenido);
        articulo.setNombreUsuario(usuario.getNombreUsuario());
        articulo.setImagen(imagen != null && !imagen.isEmpty() ? imagen : "imagen_default.jpg");

        ArticuloDAO dao = new ArticuloDAO();
        if (dao.crearArticulo(articulo)) {
            response.sendRedirect("listarArticulos?success=publicado");
        } else {
            request.setAttribute("error", "Error al publicar el artículo.");
            request.getRequestDispatcher("publicar.jsp").forward(request, response);
        }
    }
}