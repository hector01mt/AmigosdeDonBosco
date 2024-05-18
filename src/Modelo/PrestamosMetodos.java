package Modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
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
                prestamo.setDevuelto(parseBoolean(resultSet.getString("estado")));
                prestamo.setMora(resultSet.getDouble("mora_acumulada"));
                prestamos.add(prestamo);
            }
        }
        return prestamos;
    }

    private static boolean parseBoolean(String str) {
        return str != null && (str.equalsIgnoreCase("true") || str.equals("1"));
    }

    public static void agregarPrestamo(int idUsuario, int idDocumento, String fechaPrestamo, String fechaDevolucion, String estado, double moraAcumulada) throws SQLException {
        String sql = "INSERT INTO Prestamos (id_usuario, id_documento, fecha_prestamo, fecha_devolucion, estado, mora_acumulada) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);
            statement.setInt(2, idDocumento);
            statement.setString(3, fechaPrestamo);
            statement.setString(4, fechaDevolucion);
            statement.setString(5, estado);
            statement.setDouble(6, moraAcumulada);
            statement.executeUpdate();
        }
    }

    public static void actualizarPrestamo(int idPrestamo, int idUsuario, int idDocumento, String fechaPrestamo, String fechaDevolucion, String estado, double moraAcumulada) throws SQLException {
        String sql = "UPDATE Prestamos SET id_usuario=?, id_documento=?, fecha_prestamo=?, fecha_devolucion=?, estado=?, mora_acumulada=? WHERE id_prestamo=?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);
            statement.setInt(2, idDocumento);
            statement.setString(3, fechaPrestamo);
            statement.setString(4, fechaDevolucion);
            statement.setString(5, estado);
            statement.setDouble(6, moraAcumulada);
            statement.setInt(7, idPrestamo);
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


    public static void agregarPrestamo(int idUsuario, int idDocumento, Date fechaPrestamo, Date fechaDevolucion) {
    }
}
