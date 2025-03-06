package dao;

import util.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeDAO {

    // Dar like a un artículo
    public boolean darLike(String nombreUsuario, int articuloId) {
        String sql = "INSERT INTO likes (nombre_usuario, articulo_id) VALUES (?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreUsuario);
            pstmt.setInt(2, articuloId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtener número de likes de un artículo
    public int contarLikes(int articuloId) {
        String sql = "SELECT COUNT(*) FROM likes WHERE articulo_id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, articuloId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Verificar si un usuario ya dio like
    public boolean usuarioHaDadoLike(String nombreUsuario, int articuloId) {
        String sql = "SELECT * FROM likes WHERE nombre_usuario = ? AND articulo_id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreUsuario);
            pstmt.setInt(2, articuloId);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}