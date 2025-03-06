package servlets;

import dao.ArticuloDAO;
import dao.ComentarioDAO;
import dao.LikeDAO;
import modelo.Articulo;
import modelo.Comentario;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listarArticulos")
public class ListarArticulosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArticuloDAO articuloDAO = new ArticuloDAO();
        ComentarioDAO comentarioDAO = new ComentarioDAO();
        LikeDAO likeDAO = new LikeDAO();

        // Obtener todos los artículos
        List<Articulo> articulos = articuloDAO.listarArticulos();

        // Para cada artículo, obtener sus comentarios y likes
        for (Articulo articulo : articulos) {
            // Cargar comentarios del artículo
            List<Comentario> comentarios = comentarioDAO.listarComentariosPorArticulo(articulo.getId());
            articulo.setComentarios(comentarios); // Necesitas agregar este método en la clase Articulo

            // Cargar likes del artículo
            int totalLikes = likeDAO.contarLikes(articulo.getId());
            articulo.setLikes(totalLikes); // Necesitas agregar este método en la clase Articulo
        }

        // Pasar la lista de artículos a la página JSP
        request.setAttribute("articulos", articulos);
        request.getRequestDispatcher("articulos.jsp").forward(request, response);
    }
}