package Vistas.AdminDashboard;
import Modelo.Usuarios;
import javax.swing.*;

import java.awt.*;

public class AdminDashboard extends JFrame {

        private JPanel adminPanel;
        private JButton manageUsersButton;
        private JButton manageDocumentsButton;
        private JButton manageLoansButton;

        public AdminDashboard(Usuarios usuario) {

            adminPanel = new JPanel();
            adminPanel.setLayout(new BorderLayout());
            manageUsersButton = new JButton("Administrar Usuarios");
            manageDocumentsButton = new JButton("Administrar biblioteca");
            manageLoansButton = new JButton("Prestamos");

            adminPanel.add(manageUsersButton, BorderLayout.NORTH);
            adminPanel.add(manageDocumentsButton, BorderLayout.CENTER);
            adminPanel.add(manageLoansButton, BorderLayout.SOUTH);
            setContentPane(adminPanel);

            setTitle("Admin Dashboard");
            setContentPane(adminPanel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(null);

            manageUsersButton.addActionListener(e -> new ManageUsersForm().setVisible(true));
            manageDocumentsButton.addActionListener(e -> new ManageDocumentsForm().setVisible(true));
            manageLoansButton.addActionListener(e -> new ManageLoansForm().setVisible(true));
        }
    }

