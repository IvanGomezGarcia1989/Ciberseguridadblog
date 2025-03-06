package servlets;

import dao.LikeDAO;
import modelo.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {
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

        LikeDAO dao = new LikeDAO();
        if (!dao.usuarioHaDadoLike(usuario.getNombreUsuario(), articuloId)) {
            dao.darLike(usuario.getNombreUsuario(), articuloId);
        }
        response.sendRedirect("listarArticulos");
    }
}