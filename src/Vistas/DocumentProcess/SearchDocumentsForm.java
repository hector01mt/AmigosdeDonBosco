package Vistas.DocumentProcess;

import Modelo.Documentos;
import Modelo.DocumentosMetodos;
import Modelo.Usuarios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class SearchDocumentsForm extends JFrame {

    private JTextField searchField;
    private JButton searchButton;
    private JTable documentsTable;
    private JButton processLoanButton;
    private JPanel mainPanel;
    private Usuarios usuario;

    public SearchDocumentsForm(Usuarios usuario) {
        this.usuario = usuario;

        setTitle("Buscar Documentos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(20);
        searchButton = new JButton("Buscar");
        searchPanel.add(new JLabel("Buscar:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        documentsTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(documentsTable);

        processLoanButton = new JButton("Procesar Préstamo");
        processLoanButton.setEnabled(false);

        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(processLoanButton, BorderLayout.SOUTH);

        add(mainPanel);

        searchButton.addActionListener(e -> searchDocuments());

        documentsTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && documentsTable.getSelectedRow() != -1) {
                processLoanButton.setEnabled(true);
            }
        });

        processLoanButton.addActionListener(e -> {
            int selectedRow = documentsTable.getSelectedRow();
            if (selectedRow != -1) {
                int idDocumento = (int) documentsTable.getValueAt(selectedRow, 0);
                String titulo = (String) documentsTable.getValueAt(selectedRow, 2);
                ProcessLoanForm processLoanForm = new ProcessLoanForm(usuario, idDocumento, titulo);
                processLoanForm.setVisible(true);
            }
        });
    }

    private void searchDocuments() {
        DocumentosMetodos documentosMetodos = new DocumentosMetodos();
        try {
            Vector<Vector<Object>> data = documentosMetodos.buscarDocumentos(searchField.getText());
            Vector<String> columnNames = new Vector<>();
            columnNames.add("ID Documento");
            columnNames.add("Tipo Documento");
            columnNames.add("Título");
            columnNames.add("Autor");
            columnNames.add("Editorial");
            columnNames.add("Año Publicación");
            columnNames.add("Ubicación Física");
            columnNames.add("Cantidad Ejemplares");
            columnNames.add("Ejemplares Disponibles");
            columnNames.add("Ejemplares Prestados");

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            documentsTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar documentos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



}
