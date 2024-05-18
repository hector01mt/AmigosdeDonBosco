package Vistas.AdminDashboard;
import Modelo.Documentos;
import Modelo.DocumentosMetodos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;


public class ManageDocumentsForm extends JFrame{

    private JTextField titleField;
    private JTextField authorField;
    private JTextField publisherField;
    private JTextField yearField;
    private JTextField locationField;
    private JTextField totalCopiesField;
    private JTextField availableCopiesField;
    private JTextField borrowedCopiesField;
    private JComboBox<String> documentTypeComboBox;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JTable documentsTable;
    private DefaultTableModel tableModel;
    private JPanel mainPanel;

    public ManageDocumentsForm() {
        setTitle("Administrar Documentos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(11, 2));

        inputPanel.add(new JLabel("Tipo de Documento:"));
        documentTypeComboBox = new JComboBox<>(new String[]{"Libro", "Revista", "CD", "Tesis", "Otro"});
        inputPanel.add(documentTypeComboBox);

        inputPanel.add(new JLabel("Título:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Autor:"));
        authorField = new JTextField();
        inputPanel.add(authorField);

        inputPanel.add(new JLabel("Editorial:"));
        publisherField = new JTextField();
        inputPanel.add(publisherField);

        inputPanel.add(new JLabel("Año de Publicación:"));
        yearField = new JTextField();
        inputPanel.add(yearField);

        inputPanel.add(new JLabel("Ubicación Física:"));
        locationField = new JTextField();
        inputPanel.add(locationField);

        inputPanel.add(new JLabel("Cantidad de Ejemplares:"));
        totalCopiesField = new JTextField();
        inputPanel.add(totalCopiesField);

        inputPanel.add(new JLabel("Ejemplares Disponibles:"));
        availableCopiesField = new JTextField();
        inputPanel.add(availableCopiesField);

        inputPanel.add(new JLabel("Ejemplares Prestados:"));
        borrowedCopiesField = new JTextField();
        inputPanel.add(borrowedCopiesField);

        addButton = new JButton("Agregar");
        editButton = new JButton("Actualizar");
        deleteButton = new JButton("Eliminar");
        clearButton = new JButton("Limpiar");

        inputPanel.add(addButton);
        inputPanel.add(editButton);
        inputPanel.add(deleteButton);
        inputPanel.add(clearButton);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Tipo", "Título", "Autor", "Editorial", "Año", "Ubicación", "Total", "Disponibles", "Prestados"};
        tableModel = new DefaultTableModel(columnNames, 0);
        documentsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(documentsTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(mainPanel);

        loadDocuments();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Documentos documento = new Documentos();
                documento.setTipoDocumento((String) documentTypeComboBox.getSelectedItem());
                documento.setTitulo(titleField.getText());
                documento.setAutor(authorField.getText());
                documento.setEditorial(publisherField.getText());
                documento.setAnioPublicacion(Integer.parseInt(yearField.getText()));
                documento.setUbicacionFisica(locationField.getText());
                documento.setCantidadEjemplares(Integer.parseInt(totalCopiesField.getText()));
                documento.setEjemplaresDisponibles(Integer.parseInt(availableCopiesField.getText()));
                documento.setEjemplaresPrestados(Integer.parseInt(borrowedCopiesField.getText()));

                try {
                    DocumentosMetodos.agregarDocumento(documento);
                    loadDocuments();
                    clearFields();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al agregar documento.");
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = documentsTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);

                    Documentos documento = new Documentos();
                    documento.setIdDocumento(id);
                    documento.setTipoDocumento((String) documentTypeComboBox.getSelectedItem());
                    documento.setTitulo(titleField.getText());
                    documento.setAutor(authorField.getText());
                    documento.setEditorial(publisherField.getText());
                    documento.setAnioPublicacion(Integer.parseInt(yearField.getText()));
                    documento.setUbicacionFisica(locationField.getText());
                    documento.setCantidadEjemplares(Integer.parseInt(totalCopiesField.getText()));
                    documento.setEjemplaresDisponibles(Integer.parseInt(availableCopiesField.getText()));
                    documento.setEjemplaresPrestados(Integer.parseInt(borrowedCopiesField.getText()));

                    try {
                        DocumentosMetodos.actualizarDocumento(documento);
                        loadDocuments();
                        clearFields();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al editar documento.");
                    }
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = documentsTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);

                    try {
                        DocumentosMetodos.eliminarDocumento(id);
                        loadDocuments();
                        clearFields();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al eliminar documento.");
                    }
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        documentsTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = documentsTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                String tipoDocumento = (String) tableModel.getValueAt(selectedRow, 1);
                String titulo = (String) tableModel.getValueAt(selectedRow, 2);
                String autor = (String) tableModel.getValueAt(selectedRow, 3);
                String editorial = (String) tableModel.getValueAt(selectedRow, 4);
                int anioPublicacion = (int) tableModel.getValueAt(selectedRow, 5);
                String ubicacionFisica = (String) tableModel.getValueAt(selectedRow, 6);
                int cantidadEjemplares = (int) tableModel.getValueAt(selectedRow, 7);
                int ejemplaresDisponibles = (int) tableModel.getValueAt(selectedRow, 8);
                int ejemplaresPrestados = (int) tableModel.getValueAt(selectedRow, 9);

                documentTypeComboBox.setSelectedItem(tipoDocumento);
                titleField.setText(titulo);
                authorField.setText(autor);
                publisherField.setText(editorial);
                yearField.setText(String.valueOf(anioPublicacion));
                locationField.setText(ubicacionFisica);
                totalCopiesField.setText(String.valueOf(cantidadEjemplares));
                availableCopiesField.setText(String.valueOf(ejemplaresDisponibles));
                borrowedCopiesField.setText(String.valueOf(ejemplaresPrestados));
            }
        });
    }

    private void loadDocuments() {
        try {
            List<Documentos> documentos = DocumentosMetodos.getAllDocumentos();
            tableModel.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos
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
            JOptionPane.showMessageDialog(this, "Error al cargar los documentos.");
        }
    }

    private void clearFields() {
        documentTypeComboBox.setSelectedIndex(0);
        titleField.setText("");
        authorField.setText("");
        publisherField.setText("");
        yearField.setText("");
        locationField.setText("");
        totalCopiesField.setText("");
        availableCopiesField.setText("");
        borrowedCopiesField.setText("");
    }

}
