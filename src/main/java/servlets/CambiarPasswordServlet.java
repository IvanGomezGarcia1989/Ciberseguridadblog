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

@WebServlet("/cambiarPassword")
public class CambiarPasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String passwordActual = request.getParameter("passwordActual");
        String nuevaPassword = request.getParameter("nuevaPassword");
        String confirmarPassword = request.getParameter("confirmarPassword");

        // Validar que las contraseñas coincidan
        if (!nuevaPassword.equals(confirmarPassword)) {
            request.setAttribute("error", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("perfil.jsp").forward(request, response);
            return;
        }

        // Validar la contraseña actual
        UsuarioDAO dao = new UsuarioDAO();
        if (!dao.validarPassword(usuario.getNombreUsuario(), passwordActual)) {
            request.setAttribute("error", "Contraseña actual incorrecta.");
            request.getRequestDispatcher("perfil.jsp").forward(request, response);
            return;
        }

        // Actualizar la contraseña
        if (dao.cambiarPassword(usuario.getNombreUsuario(), nuevaPassword)) {
            request.setAttribute("success", "Contraseña actualizada correctamente.");
        } else {
            request.setAttribute("error", "Error al cambiar la contraseña.");
        }

        request.getRequestDispatcher("perfil.jsp").forward(request, response);
    }
}