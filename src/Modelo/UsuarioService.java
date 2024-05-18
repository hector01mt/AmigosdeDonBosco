package Modelo;
import java.sql.*;

public class UsuarioService {

    public static Usuarios login(String email, String password) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE email = ? AND contraseña = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Usuarios(
                        resultSet.getInt("id_usuario"),
                        resultSet.getString("nombre_usuario"),
                        resultSet.getString("email"),
                        resultSet.getString("contraseña"),
                        resultSet.getString("tipo_usuario")
                );
            } else {
                return null;
            }
        }
    }

}
