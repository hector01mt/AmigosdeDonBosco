package Vistas;
import Modelo.UsuarioLogin;
import Modelo.Usuarios;
import Vistas.AdminDashboard.AdminDashboard;
import Vistas.AlumnoDashboard.AlumnoDashboard;
import Vistas.ProfesorDashboard.ProfesorDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
public class LoginForm extends JFrame{ 

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel loginPanel;

    public LoginForm() {

        loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel welcomeLabel = new JLabel("Bienvenido a la Biblioteca Virtual. Por favor inicie sesión.");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        loginPanel.add(welcomeLabel, gbc);

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        loginPanel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(passwordField, gbc);

        loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 5, 5, 5);
        loginPanel.add(loginButton, gbc);

        add(loginPanel, BorderLayout.CENTER);

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    Usuarios usuario = UsuarioLogin.login(email, password);
                    if (usuario != null) {
                        JOptionPane.showMessageDialog(null, "Login exitoso. Tipo de usuario: " + usuario.getTipoUsuario());
                        switch (usuario.getTipoUsuario()) {
                            case "Administrador":
                                new AdminDashboard(usuario).setVisible(true);
                                break;
                            case "Profesor":
                                new ProfesorDashboard(usuario).setVisible(true);
                                break;
                            case "Alumno":
                                new AlumnoDashboard(usuario).setVisible(true);
                                break;
                        }
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Email o contraseña incorrectos.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos.");
                }
            }
        });

}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

}
