package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;

@WebServlet("/cambiarRol")
public class CambiarRolServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String nuevoRol = request.getParameter("rol");

        UsuarioDAO dao = new UsuarioDAO();
        if (dao.cambiarRol(nombreUsuario, nuevoRol)) {
            response.sendRedirect("admin");
        } else {
            response.sendRedirect("admin?error=1");
        }
    }
}