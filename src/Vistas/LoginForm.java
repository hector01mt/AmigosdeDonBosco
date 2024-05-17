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

        loginPanel = new JPanel();
        loginPanel.setLayout(new BorderLayout());
        emailField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        loginPanel.add(emailField, BorderLayout.NORTH);
        loginPanel.add(passwordField, BorderLayout.CENTER);
        loginPanel.add(loginButton, BorderLayout.SOUTH);

        setContentPane(loginPanel);

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
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
