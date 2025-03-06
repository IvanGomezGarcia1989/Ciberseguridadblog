package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
	
	/*  
	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String DB_NAME = "blog_ciberseguridad";
	private static final String USER = "root"; // Cambia por tu usuario de MySQL
	private static final String PASSWORD = "admin"; // Cambia por tu contraseña de MySQL
	*/ 
	private static final String URL = "jdbc:mysql://mysql.cyberseguritypodcast.com:3306/";
    private static final String DB_NAME = "blog_ciberseguridad";
    private static final String USER = "prometeo"; // Cambia por tu usuario de MySQL
    private static final String PASSWORD = "7f60V5~bn"; // Cambia por tu contraseña de MySQL
   

    // prometeo 
    //    7f60V5~bn
    
    // e_Kx2IzEw8@qv8yq cyberseguritypdcst


    /* private static final String URL = "sql203.infinityfree.com";
    private static final String DB_NAME = "if0_38396227_prometeo2025";
    private static final String USER = "if0_38396227"; // Cambia por tu usuario de MySQL
    private static final String PASSWORD = "tMejEho1Cb"; // Cambia por tu contraseña de MySQL
 */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Registrar el driver
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de MySQL.");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
    }

    public static void inicializarBaseDeDatos() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Crear la base de datos si no existe
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);

            // Usar la base de datos
            stmt.executeUpdate("USE " + DB_NAME);

            // Crear la tabla de usuarios
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS usuarios ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "nombre_usuario VARCHAR(50) NOT NULL UNIQUE,"
                    + "nombre VARCHAR(50) NOT NULL,"
                    + "apellidos VARCHAR(100) NOT NULL,"
                    + "fecha_nacimiento DATE NOT NULL,"
                    + "email VARCHAR(100) NOT NULL UNIQUE,"
                    + "password VARCHAR(255) NOT NULL,"
                    + "rol ENUM('admin', 'usuario') DEFAULT 'usuario',"
                    + "fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")");

            // Crear la tabla de artículos
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS articulos ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "titulo VARCHAR(100) NOT NULL,"
                    + "contenido TEXT NOT NULL,"
                    + "fecha_publicacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "nombre_usuario VARCHAR(50) NOT NULL,"
                    + "imagen VARCHAR(255) DEFAULT 'imagen_default.jpg',"
                    + "FOREIGN KEY (nombre_usuario) REFERENCES usuarios(nombre_usuario)"
                    + ")");

            // Crear la tabla de comentarios
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS comentarios ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "contenido TEXT NOT NULL,"
                    + "fecha_comentario TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "nombre_usuario VARCHAR(50) NOT NULL,"
                    + "articulo_id INT NOT NULL,"
                    + "FOREIGN KEY (nombre_usuario) REFERENCES usuarios(nombre_usuario),"
                    + "FOREIGN KEY (articulo_id) REFERENCES articulos(id)"
                    + ")");

            // Crear la tabla de likes
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS likes ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "nombre_usuario VARCHAR(50) NOT NULL,"
                    + "articulo_id INT NOT NULL,"
                    + "FOREIGN KEY (nombre_usuario) REFERENCES usuarios(nombre_usuario),"
                    + "FOREIGN KEY (articulo_id) REFERENCES articulos(id),"
                    + "UNIQUE (nombre_usuario, articulo_id)"
                    + ")");

            System.out.println("Base de datos y tablas creadas correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}