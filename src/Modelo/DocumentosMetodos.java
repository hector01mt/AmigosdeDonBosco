package Modelo;

import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DocumentosMetodos {

    public static void agregarDocumento(Documentos documento) throws SQLException {
        String sql = "INSERT INTO Documentos (tipo_documento, titulo, autor, editorial, anio_publicacion, ubicacion_fisica, cantidad_ejemplares, ejemplares_disponibles, ejemplares_prestados) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, documento.getTipoDocumento());
            statement.setString(2, documento.getTitulo());
            statement.setString(3, documento.getAutor());
            statement.setString(4, documento.getEditorial());
            statement.setInt(5, documento.getAnioPublicacion());
            statement.setString(6, documento.getUbicacionFisica());
            statement.setInt(7, documento.getCantidadEjemplares());
            statement.setInt(8, documento.getEjemplaresDisponibles());
            statement.setInt(9, documento.getEjemplaresPrestados());
            statement.executeUpdate();
        }
    }

    public static List<Documentos> getAllDocumentos() throws SQLException {
        List<Documentos> documentos = new ArrayList<>();
        String sql = "SELECT * FROM Documentos";

        try (Connection connection = Conexion.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Documentos documento = new Documentos();
                documento.setIdDocumento(rs.getInt("id_documento"));
                documento.setTipoDocumento(rs.getString("tipo_documento"));
                documento.setTitulo(rs.getString("titulo"));
                documento.setAutor(rs.getString("autor"));
                documento.setEditorial(rs.getString("editorial"));
                documento.setAnioPublicacion(rs.getInt("anio_publicacion"));
                documento.setUbicacionFisica(rs.getString("ubicacion_fisica"));
                documento.setCantidadEjemplares(rs.getInt("cantidad_ejemplares"));
                documento.setEjemplaresDisponibles(rs.getInt("ejemplares_disponibles"));
                documento.setEjemplaresPrestados(rs.getInt("ejemplares_prestados"));
                documentos.add(documento);
            }
        }

        return documentos;
    }

    public static void actualizarDocumento(Documentos documento) throws SQLException {
        String sql = "UPDATE Documentos SET tipo_documento = ?, titulo = ?, autor = ?, editorial = ?, anio_publicacion = ?, ubicacion_fisica = ?, cantidad_ejemplares = ?, ejemplares_disponibles = ?, ejemplares_prestados = ? WHERE id_documento = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, documento.getTipoDocumento());
            statement.setString(2, documento.getTitulo());
            statement.setString(3, documento.getAutor());
            statement.setString(4, documento.getEditorial());
            statement.setInt(5, documento.getAnioPublicacion());
            statement.setString(6, documento.getUbicacionFisica());
            statement.setInt(7, documento.getCantidadEjemplares());
            statement.setInt(8, documento.getEjemplaresDisponibles());
            statement.setInt(9, documento.getEjemplaresPrestados());
            statement.setInt(10, documento.getIdDocumento());
            statement.executeUpdate();
        }
    }

    public static void eliminarDocumento(int idDocumento) throws SQLException {
        String sql = "DELETE FROM Documentos WHERE id_documento = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idDocumento);
            statement.executeUpdate();
        }
    }

}
