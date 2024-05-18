package Vistas.ProfesorDashboard;


import Modelo.Usuarios;
import Vistas.DocumentProcess.SearchDocumentsForm;
import Vistas.LoginForm;

import javax.swing.*;
import java.awt.*;

public class ProfesorDashboard extends JFrame {
    private JPanel profesorPanel;
    private JButton searchDocumentsButton;
    private JButton regresarButton;
    private JLabel welcomeLabel;


    public ProfesorDashboard(Usuarios usuario) {

        profesorPanel = new JPanel();
        profesorPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        welcomeLabel = new JLabel("Bienvenido, " + usuario.getNombreUsuario() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        profesorPanel.add(welcomeLabel, gbc);

        searchDocumentsButton = new JButton("Buscar en la biblioteca");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        profesorPanel.add(searchDocumentsButton, gbc);

        regresarButton = new JButton("Regresar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        profesorPanel.add(regresarButton, gbc);

        setContentPane(profesorPanel);

        setTitle("Profesor Dashboard");
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
