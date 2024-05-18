package Vistas.AdminDashboard;

import Modelo.Usuarios;
import Modelo.UsuariosMetodos;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ManageUsersForm extends JFrame {

    private JPanel mainPanel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel userTypeLabel;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeComboBox;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JTable usersTable;
    private DefaultTableModel tableModel;

    public ManageUsersForm() {
        initializeUI();
        loadUsers();
    }

    private void initializeUI() {
        mainPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        nameLabel = new JLabel("Nombre Usuario:");
        inputPanel.add(nameLabel);
        nameField = new JTextField();
        inputPanel.add(nameField);

        emailLabel = new JLabel("Correo Electrónico:");
        inputPanel.add(emailLabel);
        emailField = new JTextField();
        inputPanel.add(emailField);

        passwordLabel = new JLabel("Contraseña:");
        inputPanel.add(passwordLabel);
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        userTypeLabel = new JLabel("Tipo de Usuario:");
        inputPanel.add(userTypeLabel);
        userTypeComboBox = new JComboBox<>(new String[]{"Administrador", "Profesor", "Alumno"});
        inputPanel.add(userTypeComboBox);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        addButton = new JButton("Agregar");
        editButton = new JButton("Actualizar");
        deleteButton = new JButton("Eliminar");
        clearButton = new JButton("Limpiar Campos");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        String[] columnNames = {"ID", "Nombre", "Correo electrónico", "Tipo de Usuario"};
        tableModel = new DefaultTableModel(columnNames, 0);
        usersTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(usersTable);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        usersTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = usersTable.getSelectedRow();
                if (selectedRow != -1) {
                    nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    emailField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    userTypeComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 3).toString());
                }
            }
        });

        addButton.addActionListener(e -> addUser());
        editButton.addActionListener(e -> editUser());
        deleteButton.addActionListener(e -> deleteUser());
        clearButton.addActionListener(e -> clearFields());

        setTitle("Administrar Usuarios");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
    }

    private void loadUsers() {
        try {
            List<Usuarios> usuarios = UsuariosMetodos.getAllUsuarios();
            for (Usuarios usuario : usuarios) {
                tableModel.addRow(new Object[]{usuario.getIdUsuario(), usuario.getNombreUsuario(), usuario.getEmail(), usuario.getTipoUsuario()});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los usuarios.");
        }
    }

    private void addUser() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String userType = (String) userTypeComboBox.getSelectedItem();

        Usuarios usuario = new Usuarios();
        usuario.setNombreUsuario(name);
        usuario.setEmail(email);
        usuario.setTipoUsuario(userType);
        usuario.setContrasena(password);

        try {
            UsuariosMetodos.agregarUsuario(usuario);
            tableModel.addRow(new Object[]{usuario.getIdUsuario(), usuario.getNombreUsuario(), usuario.getEmail(), usuario.getTipoUsuario()});
            clearFields();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar usuario.");
        }
    }

    private void editUser() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String userType = (String) userTypeComboBox.getSelectedItem();

            Usuarios usuario = new Usuarios();
            usuario.setIdUsuario(id);
            usuario.setNombreUsuario(name);
            usuario.setEmail(email);
            usuario.setTipoUsuario(userType);
            usuario.setContrasena(password);

            try {
                UsuariosMetodos.actualizarUsuario(usuario);
                tableModel.setValueAt(name, selectedRow, 1);
                tableModel.setValueAt(email, selectedRow, 2);
                tableModel.setValueAt(userType, selectedRow, 3);
                clearFields();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al editar usuario.");
            }
        }
    }

    private void deleteUser() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);

            try {
                UsuariosMetodos.eliminarUsuario(id);
                tableModel.removeRow(selectedRow);
                clearFields();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al eliminar usuario.");
            }
        }
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        userTypeComboBox.setSelectedIndex(0);
    }
}