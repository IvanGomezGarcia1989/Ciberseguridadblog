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

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        Date fechaNacimiento = Date.valueOf(request.getParameter("fechaNacimiento"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setRol("usuario"); // Por defecto, el rol es "usuario"

        UsuarioDAO dao = new UsuarioDAO();
        if (dao.registrarUsuario(usuario)) {
            response.sendRedirect("login.jsp");
        } else {
            response.sendRedirect("registro.jsp?error=1");
        }
    }
}