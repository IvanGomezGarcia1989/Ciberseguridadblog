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

@WebServlet("/eliminarArticulo")
public class EliminarArticuloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el ID del artículo desde la URL
        int id = Integer.parseInt(request.getParameter("id"));

        // Verificar si el usuario es el autor del artículo
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        ArticuloDAO dao = new ArticuloDAO();
        Articulo articulo = dao.obtenerArticuloPorId(id);

        if (usuario == null || !usuario.getNombreUsuario().equals(articulo.getNombreUsuario())) {
            response.sendRedirect("articulos.jsp");
            return;
        }

        // Eliminar el artículo
        if (dao.eliminarArticulo(id)) {
            response.sendRedirect("listarArticulos?success=eliminado");
        } else {
            request.setAttribute("error", "Error al eliminar el artículo.");
            request.getRequestDispatcher("listarArticulos").forward(request, response);
        }
    }
}