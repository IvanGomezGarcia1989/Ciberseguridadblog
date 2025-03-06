package servlets;

import dao.UsuarioDAO;
import modelo.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.obtenerUsuarioPorEmail(email);

        if (usuario != null && usuario.getPassword().equals(password)) {
            // Crear una sesión para el usuario
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);

            // Redirigir según el rol del usuario
            if (usuario.getRol().equals("admin")) {
                response.sendRedirect("listarArticulos"); // Página de administrador
            } else {
                response.sendRedirect("listarArticulos"); // Página de usuario normal
            }
        } else {
            // Credenciales incorrectas
            response.sendRedirect("login.jsp?error=1");
        }
    }
}