package servlets;

import dao.UsuarioDAO;
import modelo.Usuario;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/perfil")
public class PerfilServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Mostrar el formulario de perfil con los datos actuales
        request.getRequestDispatcher("perfil.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        Date fechaNacimiento = Date.valueOf(request.getParameter("fechaNacimiento"));
        String email = request.getParameter("email");

        // Actualizar los datos del usuario
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setEmail(email);

        UsuarioDAO dao = new UsuarioDAO();
        if (dao.actualizarUsuario(usuario)) {
            session.setAttribute("usuario", usuario); // Actualizar la sesión
            request.setAttribute("success", "Perfil actualizado correctamente.");
        } else {
            request.setAttribute("error", "Error al actualizar el perfil.");
        }

        request.getRequestDispatcher("perfil.jsp").forward(request, response);
    }
}