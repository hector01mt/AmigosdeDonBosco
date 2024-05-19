package Vistas.DocumentProcess;

import Modelo.PrestamosMetodos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProcessReturnForm extends JFrame {

    private JPanel mainPanel;
    private JTextField idPrestamoField;
    private JButton devolverButton;

    public ProcessReturnForm() {
        setTitle("Devolver Libro");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("ID Préstamo:"), gbc);
        idPrestamoField = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(idPrestamoField, gbc);

        devolverButton = new JButton("Devolver Libro");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(devolverButton, gbc);

        add(mainPanel);

        devolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolverLibro();
            }
        });
    }

    private void devolverLibro() {
        try {
            int idPrestamo = Integer.parseInt(idPrestamoField.getText());
            PrestamosMetodos.marcarComoDevuelto(idPrestamo);
            int idUsuario = PrestamosMetodos.obtenerIdUsuarioPorPrestamo(idPrestamo); // Asumiendo que tienes un método para obtener el id del usuario por el id del préstamo
            PrestamosMetodos.disminuirMoraUsuario(idUsuario);
            JOptionPane.showMessageDialog(this, "Libro devuelto exitosamente. Mora disminuida en 1.");
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al devolver el libro", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
