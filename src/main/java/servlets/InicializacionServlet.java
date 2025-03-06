package servlets;

import util.ConexionBD;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/inicializacion", loadOnStartup = 1)
public class InicializacionServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ConexionBD.inicializarBaseDeDatos();
    }
}