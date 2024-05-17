package Vistas;
import Modelo.UsuarioService;
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

        // Inicializar el panel principal y el layout
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());

        // Configuración de GridBagConstraints para el layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Inicializar y agregar el mensaje de bienvenida
        JLabel welcomeLabel = new JLabel("Bienvenido a la Biblioteca Virtual. Por favor inicie sesión.");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        loginPanel.add(welcomeLabel, gbc);

        // Inicializar y agregar la etiqueta y el campo de texto para el email
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

        // Inicializar y agregar la etiqueta y el campo de texto para la contraseña
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(passwordField, gbc);

        // Inicializar y agregar el botón de login
        loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 5, 5, 5);
        loginPanel.add(loginButton, gbc);

        // Agregar el panel principal al frame
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
                    Usuarios usuario = UsuarioService.login(email, password);
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
