package Vistas.AdminDashboard;
import Modelo.Usuarios;
import Vistas.LoginForm;

import javax.swing.*;

import java.awt.*;

public class AdminDashboard extends JFrame {

    private JPanel adminPanel;
    private JButton manageUsersButton;
    private JButton manageDocumentsButton;
    private JButton manageLoansButton;
    private JButton regresarButton;

    public AdminDashboard(Usuarios usuario) {

        adminPanel = new JPanel();
        adminPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("¡Bienvenido al Panel de Administrador!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adminPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));

        manageUsersButton = new JButton("Administrar Usuarios");
        manageUsersButton.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(manageUsersButton);

        manageDocumentsButton = new JButton("Administrar Biblioteca");
        manageDocumentsButton.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(manageDocumentsButton);

        manageLoansButton = new JButton("Préstamos");
        manageLoansButton.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(manageLoansButton);

        regresarButton = new JButton("Regresar");
        regresarButton.setPreferredSize(new Dimension(200, 50)); // Set preferred size for consistency
        buttonPanel.add(regresarButton); // Add the regresarButton to the buttonPanel

        adminPanel.add(buttonPanel, BorderLayout.CENTER);

        setTitle("Admin Dashboard");
        setContentPane(adminPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        manageUsersButton.addActionListener(e -> new ManageUsersForm().setVisible(true));
        manageDocumentsButton.addActionListener(e -> new ManageDocumentsForm().setVisible(true));
        manageLoansButton.addActionListener(e -> new ManageLoansForm().setVisible(true));

        regresarButton.addActionListener(e -> {
            new LoginForm().setVisible(true);
            dispose();
        });
    }


    }

