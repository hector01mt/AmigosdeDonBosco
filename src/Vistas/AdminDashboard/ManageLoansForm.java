package Vistas.AdminDashboard;
import Modelo.Prestamos;
import Modelo.PrestamosMetodos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ManageLoansForm extends JFrame{

    private JTextField idPrestamoField;
    private JTextField idUsuarioField;
    private JTextField idDocumentoField;
    private JTextField fechaPrestamoField;
    private JTextField fechaDevolucionField;
    private JTextField estadoField;
    private JTextField moraAcumuladaField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JTable loansTable;
    private DefaultTableModel tableModel;

    public ManageLoansForm() {
        setTitle("Administrar Préstamos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("ID Préstamo:"), gbc);
        gbc.gridx = 1;
        idPrestamoField = new JTextField();
        idPrestamoField.setEditable(false);
        inputPanel.add(idPrestamoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("ID Usuario:"), gbc);
        gbc.gridx = 1;
        idUsuarioField = new JTextField();
        inputPanel.add(idUsuarioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("ID Documento:"), gbc);
        gbc.gridx = 1;
        idDocumentoField = new JTextField();
        inputPanel.add(idDocumentoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Fecha Préstamo (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        fechaPrestamoField = new JTextField();
        inputPanel.add(fechaPrestamoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Fecha Devolución (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        fechaDevolucionField = new JTextField();
        inputPanel.add(fechaDevolucionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(new JLabel("Estado:"), gbc);
        gbc.gridx = 1;
        estadoField = new JTextField();
        inputPanel.add(estadoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(new JLabel("Mora Acumulada:"), gbc);
        gbc.gridx = 1;
        moraAcumuladaField = new JTextField();
        inputPanel.add(moraAcumuladaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        addButton = new JButton("Agregar");
        inputPanel.add(addButton, gbc);

        gbc.gridx = 1;
        editButton = new JButton("Modificar");
        inputPanel.add(editButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        deleteButton = new JButton("Eliminar");
        inputPanel.add(deleteButton, gbc);

        gbc.gridx = 1;
        clearButton = new JButton("Limpiar");
        inputPanel.add(clearButton, gbc);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID Préstamo", "ID Usuario", "ID Documento", "Fecha Préstamo", "Fecha Devolución", "Estado", "Mora Acumulada"};
        tableModel = new DefaultTableModel(columnNames, 0);
        loansTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(loansTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(mainPanel);

        loadLoans();

        loansTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = loansTable.getSelectedRow();
            if (selectedRow != -1) {
                idPrestamoField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                idUsuarioField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                idDocumentoField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                fechaPrestamoField.setText(tableModel.getValueAt(selectedRow, 3).toString());
                fechaDevolucionField.setText(tableModel.getValueAt(selectedRow, 4).toString());
                estadoField.setText(tableModel.getValueAt(selectedRow, 5).toString());
                moraAcumuladaField.setText(tableModel.getValueAt(selectedRow, 6).toString());
            }
        });

        addButton.addActionListener(e -> {
            try {
                int idUsuario = Integer.parseInt(idUsuarioField.getText());
                int idDocumento = Integer.parseInt(idDocumentoField.getText());
                String fechaPrestamo = fechaPrestamoField.getText();
                String fechaDevolucion = fechaDevolucionField.getText();
                String estado = estadoField.getText();
                double moraAcumulada = Double.parseDouble(moraAcumuladaField.getText());

                PrestamosMetodos.agregarPrestamo(idUsuario, idDocumento, fechaPrestamo, fechaDevolucion, estado, moraAcumulada);
                loadLoans();
                clearFields();
            } catch (NumberFormatException | SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al agregar préstamo.");
            }
        });

        editButton.addActionListener(e -> {
            try {
                int idPrestamo = Integer.parseInt(idPrestamoField.getText());
                int idUsuario = Integer.parseInt(idUsuarioField.getText());
                int idDocumento = Integer.parseInt(idDocumentoField.getText());
                String fechaPrestamo = fechaPrestamoField.getText();
                String fechaDevolucion = fechaDevolucionField.getText();
                String estado = estadoField.getText();
                double moraAcumulada = Double.parseDouble(moraAcumuladaField.getText());

                PrestamosMetodos.actualizarPrestamo(idPrestamo, idUsuario, idDocumento, fechaPrestamo, fechaDevolucion, estado, moraAcumulada);
                loadLoans();
                clearFields();
            } catch (NumberFormatException | SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al modificar préstamo.");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = loansTable.getSelectedRow();
            if (selectedRow != -1) {
                int idPrestamo = Integer.parseInt(idPrestamoField.getText());
                try {
                    PrestamosMetodos.eliminarPrestamo(idPrestamo);
                    loadLoans();
                    clearFields();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar préstamo.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un préstamo para eliminar.");
            }
        });

        clearButton.addActionListener(e -> clearFields());
    }

    private void loadLoans() {
        try {
            List<Prestamos> loans = PrestamosMetodos.getAllPrestamos();
            tableModel.setRowCount(0);
            for (Prestamos loan : loans) {
                Object[] row = {
                        loan.getIdPrestamo(),
                        loan.getIdUsuario(),
                        loan.getIdDocumento(),
                        loan.getFechaPrestamo(),
                        loan.getFechaDevolucion(),
                        loan.isDevuelto() ? "Devuelto" : "No devuelto",
                        loan.getMora()
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar préstamos.");
        }
    }

    private void clearFields() {
        idPrestamoField.setText("");
        idUsuarioField.setText("");
        idDocumentoField.setText("");
        fechaPrestamoField.setText("");
        fechaDevolucionField.setText("");
        estadoField.setText("");
        moraAcumuladaField.setText("");
    }

}


