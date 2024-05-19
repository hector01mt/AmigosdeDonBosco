package Modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class PrestamosMetodos {
    /*public static List<Prestamos> getAllPrestamos() throws SQLException {
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
    }*/

    public static List<Prestamos> getAllPrestamos() throws SQLException {
        List<Prestamos> prestamosList = new ArrayList<>();
        Connection connection = Conexion.getConnection();

        String query = "SELECT id_prestamo, id_usuario, id_documento, fecha_prestamo, fecha_devolucion, estado, mora_acumulada FROM prestamos";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Prestamos prestamo = new Prestamos();
            prestamo.setIdPrestamo(resultSet.getInt("id_prestamo"));
            prestamo.setIdUsuario(resultSet.getInt("id_usuario"));
            prestamo.setIdDocumento(resultSet.getInt("id_documento"));
            prestamo.setFechaPrestamo(resultSet.getDate("fecha_prestamo"));
            prestamo.setFechaDevolucion(resultSet.getDate("fecha_devolucion"));
            prestamo.setDevuelto(resultSet.getString("estado").equals("Devuelto"));
            prestamo.setMora(resultSet.getDouble("mora_acumulada"));

            prestamosList.add(prestamo);
        }

        resultSet.close();
        statement.close();
        connection.close();

        return prestamosList;
    }

    private static boolean parseBoolean(String str) {
        return str != null && (str.equalsIgnoreCase("true") || str.equals("1"));
    }

    /*public static void agregarPrestamo(int idUsuario, int idDocumento, String fechaPrestamo, String fechaDevolucion, String estado, double moraAcumulada) throws SQLException {
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
    }*/

    public static void agregarPrestamo(int idUsuario, int idDocumento, Date fechaPrestamo, Date fechaDevolucion) throws SQLException {
        String sql = "INSERT INTO Prestamos (id_usuario, id_documento, fecha_prestamo, fecha_devolucion, estado, mora_acumulada) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);
            statement.setInt(2, idDocumento);
            statement.setDate(3, new java.sql.Date(fechaPrestamo.getTime()));
            statement.setDate(4, new java.sql.Date(fechaDevolucion.getTime()));
            statement.setString(5, "Devuelto");
            statement.setDouble(6, 0.0);
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

    public static void incrementarMoraUsuario(int idUsuario) throws SQLException {
        String sql = "UPDATE Prestamos SET mora_acumulada = mora_acumulada + 1 WHERE id_usuario = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);
            statement.executeUpdate();
        }
    }

    public static double obtenerMoraUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT SUM(mora_acumulada) AS mora_total FROM Prestamos WHERE id_usuario = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("mora_total");
                }
            }
        }
        return 0.0;
    }

    public static void marcarComoDevuelto(int idPrestamo) throws SQLException {
        String sql = "UPDATE Prestamos SET estado = 'Devuelto' WHERE id_prestamo = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPrestamo);
            statement.executeUpdate();
        }
    }

    public static void disminuirMoraUsuario(int idUsuario) throws SQLException {
        String sql = "UPDATE Prestamos SET mora_acumulada = mora_acumulada - 1 WHERE id_usuario = ? AND mora_acumulada > 0";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);
            statement.executeUpdate();
        }
    }

    public static int obtenerIdUsuarioPorPrestamo(int idPrestamo) throws SQLException {
        String sql = "SELECT id_usuario FROM Prestamos WHERE id_prestamo = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPrestamo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id_usuario");
                } else {
                    throw new SQLException("No se encontró el préstamo con id: " + idPrestamo);
                }
            }
        }
    }

    public static int obtenerUltimoIdPrestamo() throws SQLException {
        String sql = "SELECT MAX(id_prestamo) AS ultimo_id FROM Prestamos";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("ultimo_id");
            }
        }
        return -1; // Si no se encuentra ningún préstamo, devolvemos -1
    }




    public static void agregarPrestamo(int idUsuario, int idDocumento, String fechaPrestamo, String fechaDevolucion, String estado, double moraAcumulada) {
    }


    // public static void agregarPrestamo(int idUsuario, int idDocumento, Date fechaPrestamo, Date fechaDevolucion) {
    //}
}
