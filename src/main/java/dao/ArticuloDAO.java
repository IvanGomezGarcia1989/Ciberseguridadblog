package dao;

import modelo.Articulo;
import util.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDAO {

    // Crear un nuevo artículo
    public boolean crearArticulo(Articulo articulo) {
        String sql = "INSERT INTO articulos (titulo, contenido, nombre_usuario, imagen) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, articulo.getTitulo());
            pstmt.setString(2, articulo.getContenido());
            pstmt.setString(3, articulo.getNombreUsuario());
            pstmt.setString(4, articulo.getImagen());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Listar todos los artículos
    public List<Articulo> listarArticulos() {
        List<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT * FROM articulos ORDER BY fecha_publicacion DESC";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Articulo articulo = new Articulo();
                articulo.setId(rs.getInt("id"));
                articulo.setTitulo(rs.getString("titulo"));
                articulo.setContenido(rs.getString("contenido"));
                articulo.setFechaPublicacion(rs.getTimestamp("fecha_publicacion"));
                articulo.setNombreUsuario(rs.getString("nombre_usuario"));
                articulo.setImagen(rs.getString("imagen"));
                articulos.add(articulo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articulos;
    }

    // Obtener un artículo por su ID
    public Articulo obtenerArticuloPorId(int id) {
        String sql = "SELECT * FROM articulos WHERE id = ?";
        Articulo articulo = null;

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                articulo = new Articulo();
                articulo.setId(rs.getInt("id"));
                articulo.setTitulo(rs.getString("titulo"));
                articulo.setContenido(rs.getString("contenido"));
                articulo.setFechaPublicacion(rs.getTimestamp("fecha_publicacion"));
                articulo.setNombreUsuario(rs.getString("nombre_usuario"));
                articulo.setImagen(rs.getString("imagen"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articulo;
    }
    public boolean actualizarArticulo(Articulo articulo) {
        String sql = "UPDATE articulos SET titulo = ?, contenido = ?, imagen = ? WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, articulo.getTitulo());
            pstmt.setString(2, articulo.getContenido());
            pstmt.setString(3, articulo.getImagen());
            pstmt.setInt(4, articulo.getId());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarArticulo(int id) {
        String sql = "DELETE FROM articulos WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}