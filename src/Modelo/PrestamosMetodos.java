package Modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PrestamosMetodos {

    public static List<Prestamos> getAllPrestamos() throws SQLException {
        List<Prestamos> prestamos = new ArrayList<>();
        String query = "SELECT * FROM Prestamos";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Prestamos prestamo = new Prestamos();
                prestamo.setIdPrestamo(resultSet.getInt("id_prestamo"));
                prestamo.setIdUsuario(resultSet.getInt("id_usuario"));
                prestamo.setIdDocumento(resultSet.getInt("id_documento"));
                prestamo.setFechaPrestamo(resultSet.getString("fecha_prestamo"));
                prestamo.setFechaDevolucion(resultSet.getString("fecha_devolucion"));
                prestamo.setDevuelto(Boolean.parseBoolean(resultSet.getString("estado")));
                prestamo.setMora(resultSet.getDouble("mora_acumulada"));
                prestamos.add(prestamo);
            }
        }
        return prestamos;
    }

    public static void agregarPrestamo(Prestamos prestamo) throws SQLException {
        String sql = "INSERT INTO Prestamos (id_usuario, id_documento, fecha_prestamo, fecha_devolucion, estado, mora_acumulada) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, prestamo.getIdUsuario());
            statement.setInt(2, prestamo.getIdDocumento());
            statement.setString(3, prestamo.getFechaPrestamo().toString());
            statement.setString(4, prestamo.getFechaDevolucion().toString());
            statement.setString(5, String.valueOf(prestamo.isDevuelto()));
            statement.setDouble(6, prestamo.getMora());
            statement.executeUpdate();
        }
    }

    public static void actualizarPrestamo(Prestamos prestamo) throws SQLException {
        String sql = "UPDATE Prestamos SET id_usuario=?, id_documento=?, fecha_prestamo=?, fecha_devolucion=?, estado=?, mora_acumulada=? " +
                "WHERE id_prestamo=?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, prestamo.getIdUsuario());
            statement.setInt(2, prestamo.getIdDocumento());
            statement.setString(3, prestamo.getFechaPrestamo().toString());
            statement.setString(4, prestamo.getFechaDevolucion().toString());
            statement.setString(5, String.valueOf(prestamo.isDevuelto()));
            statement.setDouble(6, prestamo.getMora());
            statement.setInt(7, prestamo.getIdPrestamo());
            statement.executeUpdate();
        }
    }

    public static void eliminarPrestamo(int idPrestamo) throws SQLException {
        String sql = "DELETE FROM Prestamos WHERE id_prestamo=?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPrestamo);
            statement.executeUpdate();
        }
    }

}
