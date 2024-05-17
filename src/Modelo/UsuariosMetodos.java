package Modelo;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosMetodos {

    public static List<Usuarios> getAllUsuarios() throws SQLException {
        List<Usuarios> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection connection = Conexion.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public static void agregarUsuario(Usuarios usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre_usuario, email, contraseña, tipo_usuario) VALUES (?, ?, ?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, usuario.getNombreUsuario());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getContrasena());
            statement.setString(4, usuario.getTipoUsuario());
            statement.executeUpdate();
        }
    }

    public static void actualizarUsuario(Usuarios usuario) throws SQLException {
        String query = "UPDATE usuarios SET nombre_usuario = ?, email = ?, tipo_usuario = ? WHERE id_usuario = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, usuario.getNombreUsuario());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getTipoUsuario());
            statement.setInt(4, usuario.getIdUsuario());
            statement.executeUpdate();
        }
    }

    public static void eliminarUsuario(int id) throws SQLException {
        String query = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }




}
