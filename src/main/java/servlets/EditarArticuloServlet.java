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

@WebServlet("/editarArticulo")
public class EditarArticuloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el ID del artículo desde la URL
        int id = Integer.parseInt(request.getParameter("id"));

        // Obtener el artículo desde la base de datos
        ArticuloDAO dao = new ArticuloDAO();
        Articulo articulo = dao.obtenerArticuloPorId(id);

        // Verificar si el usuario es el autor del artículo
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !usuario.getNombreUsuario().equals(articulo.getNombreUsuario())) {
            response.sendRedirect("articulos.jsp");
            return;
        }

        // Pasar el artículo a la página JSP
        request.setAttribute("articulo", articulo);
        request.getRequestDispatcher("editar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los datos del formulario
        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String contenido = request.getParameter("contenido");
        String imagen = request.getParameter("imagen");

        // Verificar si el usuario es el autor del artículo
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        ArticuloDAO dao = new ArticuloDAO();
        Articulo articulo = dao.obtenerArticuloPorId(id);

        if (usuario == null || !usuario.getNombreUsuario().equals(articulo.getNombreUsuario())) {
            response.sendRedirect("articulos.jsp");
            return;
        }

        // Actualizar el artículo
        articulo.setTitulo(titulo);
        articulo.setContenido(contenido);
        articulo.setImagen(imagen);

        if (dao.actualizarArticulo(articulo)) {
            response.sendRedirect("listarArticulos?success=editado");
        } else {
            request.setAttribute("error", "Error al actualizar el artículo.");
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
    }
}