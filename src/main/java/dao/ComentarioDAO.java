package dao;

import modelo.Comentario;
import util.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO {

    // Crear un comentario
    public boolean crearComentario(Comentario comentario) {
        String sql = "INSERT INTO comentarios (contenido, nombre_usuario, articulo_id) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, comentario.getContenido());
            pstmt.setString(2, comentario.getNombreUsuario());
            pstmt.setInt(3, comentario.getArticuloId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Comentario> listarComentariosPorArticulo(int articuloId) {
        List<Comentario> comentarios = new ArrayList<>();
        String sql = "SELECT * FROM comentarios WHERE articulo_id = ? ORDER BY fecha_comentario DESC";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, articuloId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Comentario comentario = new Comentario();
                comentario.setId(rs.getInt("id"));
                comentario.setContenido(rs.getString("contenido"));
                comentario.setFechaComentario(rs.getTimestamp("fecha_comentario"));
                comentario.setNombreUsuario(rs.getString("nombre_usuario"));
                comentario.setArticuloId(rs.getInt("articulo_id"));
                comentarios.add(comentario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarios;
    }
}