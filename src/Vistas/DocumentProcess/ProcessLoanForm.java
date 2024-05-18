package Vistas.DocumentProcess;

import Modelo.PrestamosMetodos;
import Modelo.Usuarios;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class ProcessLoanForm extends JFrame {
    private JPanel mainPanel;
    private JTextField idUsuarioField;
    private JTextField idDocumentoField;
    private JTextField tituloField;
    private JTextField fechaPrestamoField;
    private JTextField fechaDevolucionField;
    private JButton confirmarButton;
    private Usuarios usuario;
    private int idDocumento;
    private String titulo;

    public ProcessLoanForm(Usuarios usuario, int idDocumento, String titulo) {
        this.usuario = usuario;
        this.idDocumento = idDocumento;
        this.titulo = titulo;

        setTitle("Procesar Préstamo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("ID Usuario:"), gbc);
        idUsuarioField = new JTextField(20);
        idUsuarioField.setText(String.valueOf(usuario.getIdUsuario()));
        idUsuarioField.setEditable(false);
        gbc.gridx = 1;
        mainPanel.add(idUsuarioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("ID Documento:"), gbc);
        idDocumentoField = new JTextField(20);
        idDocumentoField.setText(String.valueOf(idDocumento));
        idDocumentoField.setEditable(false);
        gbc.gridx = 1;
        mainPanel.add(idDocumentoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Título:"), gbc);
        tituloField = new JTextField(20);
        tituloField.setText(titulo);
        tituloField.setEditable(false);
        gbc.gridx = 1;
        mainPanel.add(tituloField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Fecha Préstamo:"), gbc);
        fechaPrestamoField = new JTextField(20);
        fechaPrestamoField.setText(new java.sql.Date(new Date().getTime()).toString());
        fechaPrestamoField.setEditable(false);
        gbc.gridx = 1;
        mainPanel.add(fechaPrestamoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Fecha Devolución:"), gbc);
        fechaDevolucionField = new JTextField(20);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 14); // Fecha de devolución a 14 días en el futuro
        fechaDevolucionField.setText(new java.sql.Date(calendar.getTimeInMillis()).toString());
        fechaDevolucionField.setEditable(false);
        gbc.gridx = 1;
        mainPanel.add(fechaDevolucionField, gbc);

        confirmarButton = new JButton("Confirmar Préstamo");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(confirmarButton, gbc);

        add(mainPanel);

        confirmarButton.addActionListener(e -> confirmarPrestamo());
    }

    private void confirmarPrestamo() {
        try {
            Date fechaPrestamo = java.sql.Date.valueOf(fechaPrestamoField.getText());
            Date fechaDevolucion = java.sql.Date.valueOf(fechaDevolucionField.getText());
            PrestamosMetodos.agregarPrestamo(usuario.getIdUsuario(), idDocumento, fechaPrestamo, fechaDevolucion);
            JOptionPane.showMessageDialog(this, "Préstamo procesado exitosamente");
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al procesar el préstamo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
