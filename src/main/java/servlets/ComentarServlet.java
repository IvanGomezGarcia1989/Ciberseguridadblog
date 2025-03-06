package servlets;

import dao.ComentarioDAO;
import modelo.Comentario;
import modelo.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/comentar")
public class ComentarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        int articuloId = Integer.parseInt(request.getParameter("articuloId"));
        String contenido = request.getParameter("contenido");

        Comentario comentario = new Comentario();
        comentario.setContenido(contenido);
        comentario.setNombreUsuario(usuario.getNombreUsuario());
        comentario.setArticuloId(articuloId);

        ComentarioDAO dao = new ComentarioDAO();
        if (dao.crearComentario(comentario)) {
            response.sendRedirect("listarArticulos"); // Redirige al servlet que carga los artículos
        } else {
            response.sendRedirect("articulos.jsp?error=1");
        }
    }
}