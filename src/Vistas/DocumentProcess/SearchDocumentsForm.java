package Vistas.DocumentProcess;

import Modelo.Documentos;
import Modelo.DocumentosMetodos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class SearchDocumentsForm extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JButton processLoanButton; // Botón para procesar préstamo
    private JTable documentsTable;
    private DefaultTableModel tableModel;

    public SearchDocumentsForm() {
        setTitle("Buscar Documentos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Panel de búsqueda
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        searchField = new JTextField(20);
        searchPanel.add(searchField);

        searchButton = new JButton("Buscar");
        searchPanel.add(searchButton);

        // Botón para procesar préstamo
        processLoanButton = new JButton("Procesar Préstamo");
        searchPanel.add(processLoanButton); // Agregar el botón al panel de búsqueda

        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // Tabla de documentos
        String[] columnNames = {"ID", "Tipo", "Título", "Autor", "Editorial", "Año", "Ubicación", "Total", "Disponibles", "Prestados"};
        tableModel = new DefaultTableModel(columnNames, 0);
        documentsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(documentsTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(mainPanel);

        // Acción del botón de búsqueda
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchDocuments();
            }
        });

        // Acción del botón para procesar préstamo
        processLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processLoan();
            }
        });

        // Configurar selección de fila en la tabla
        documentsTable.getSelectionModel().addListSelectionListener(e -> {
            // Aquí puedes manejar la selección de una fila en la tabla si es necesario
        });
    }

    private void searchDocuments() {
        String searchText = searchField.getText().trim(); // Obtener el texto de búsqueda y quitar espacios en blanco al inicio y al final

        // Verificar que el campo de búsqueda no esté vacío
        if (!searchText.isEmpty()) {
            try {
                // Limpiar la tabla antes de realizar una nueva búsqueda
                tableModel.setRowCount(0);

                // Obtener la lista de documentos que coinciden con el texto de búsqueda
                List<Documentos> documentos = DocumentosMetodos.buscarDocumentos(searchText);

                // Llenar la tabla con los resultados de la búsqueda
                for (Documentos documento : documentos) {
                    tableModel.addRow(new Object[]{
                            documento.getIdDocumento(),
                            documento.getTipoDocumento(),
                            documento.getTitulo(),
                            documento.getAutor(),
                            documento.getEditorial(),
                            documento.getAnioPublicacion(),
                            documento.getUbicacionFisica(),
                            documento.getCantidadEjemplares(),
                            documento.getEjemplaresDisponibles(),
                            documento.getEjemplaresPrestados()
                    });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al buscar documentos.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un término de búsqueda.");
        }
    }


    private void processLoan() {
        // Aquí implementa la lógica para procesar el préstamo
    }



}
