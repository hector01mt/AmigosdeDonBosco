package Vistas.AlumnoDashboard;

import Modelo.Usuarios;
import Vistas.DocumentProcess.SearchDocumentsForm;
import Vistas.LoginForm;

import javax.swing.*;
import java.awt.*;

public class AlumnoDashboard extends JFrame {

    private JPanel alumnoPanel;
    private JButton searchDocumentsButton;
    private JButton regresarButton;
    private JLabel welcomeLabel;


    public AlumnoDashboard(Usuarios usuario) {

        alumnoPanel = new JPanel();
        alumnoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        welcomeLabel = new JLabel("Bienvenido, " + usuario.getNombreUsuario() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        alumnoPanel.add(welcomeLabel, gbc);

        searchDocumentsButton = new JButton("Buscar en la biblioteca");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        alumnoPanel.add(searchDocumentsButton, gbc);

        regresarButton = new JButton("Regresar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        alumnoPanel.add(regresarButton, gbc);

        setContentPane(alumnoPanel);

        setTitle("Alumno Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        searchDocumentsButton.addActionListener(e -> new SearchDocumentsForm(usuario).setVisible(true));
        regresarButton.addActionListener(e -> {
            new LoginForm().setVisible(true);
            dispose();
        });


    }
}
