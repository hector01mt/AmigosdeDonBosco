package Vistas.AdminDashboard;
import Modelo.Usuarios;
import Modelo.UsuariosMetodos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ManageUsersForm extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField passwordField;
    private JComboBox<String> userTypeComboBox;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTable usersTable;
    private JLabel nombre;
    private JLabel correoElectronico;
    private JLabel contrasena;
    private JLabel tipoUsuario;
    private JPanel mainPanel;
    private DefaultTableModel tableModel;


    public ManageUsersForm() {

        mainPanel = new JPanel();
        setContentPane(mainPanel);
        setTitle("Administrar Usuarios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        nombre = new JLabel("Nombre Usuario:");
        correoElectronico = new JLabel("Correo Electrónico:");
        contrasena = new JLabel("Contraseña:");
        tipoUsuario = new JLabel("Tipo de Usuario:");

        nameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JTextField(20);
        userTypeComboBox = new JComboBox<>(new String[]{"Administrador", "Profesor", "Alumno"});

        addButton = new JButton("Agregar");
        editButton = new JButton("Actualizar");
        deleteButton = new JButton("Eliminar");

        String[] columnNames = {"ID", "Nombre", "Correo electrónico", "Tipo de Usuario"};
        tableModel = new DefaultTableModel(columnNames, 0);
        usersTable.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(usersTable);

        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nombre)
                                .addComponent(correoElectronico)
                                .addComponent(contrasena)
                                .addComponent(tipoUsuario)
                                .addComponent(addButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameField)
                                .addComponent(emailField)
                                .addComponent(passwordField)
                                .addComponent(userTypeComboBox)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(editButton)
                                        .addComponent(deleteButton)))
                        .addComponent(scrollPane)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nombre)
                                .addComponent(nameField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(correoElectronico)
                                .addComponent(emailField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(contrasena)
                                .addComponent(passwordField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(tipoUsuario)
                                .addComponent(userTypeComboBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addButton)
                                .addComponent(editButton)
                                .addComponent(deleteButton))
                        .addComponent(scrollPane)
        );

        loadUsers();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String userType = (String) userTypeComboBox.getSelectedItem();

                Usuarios usuario = new Usuarios();
                usuario.setNombreUsuario(name);
                usuario.setEmail(email);
                usuario.setTipoUsuario(userType);
                usuario.setContrasena("default");

                try {
                    UsuariosMetodos.agregarUsuario(usuario);
                    loadUsers();
                    clearFields();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al agregar usuario.");
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = usersTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    String name = nameField.getText();
                    String email = emailField.getText();
                    String userType = (String) userTypeComboBox.getSelectedItem();

                    Usuarios usuario = new Usuarios();
                    usuario.setIdUsuario(id);
                    usuario.setNombreUsuario(name);
                    usuario.setEmail(email);
                    usuario.setTipoUsuario(userType);

                    try {
                        UsuariosMetodos.actualizarUsuario(usuario);
                        loadUsers();
                        clearFields();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al editar usuario.");
                    }
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = usersTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);

                    try {
                        UsuariosMetodos.eliminarUsuario(id);
                        loadUsers();
                        clearFields();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al eliminar usuario.");
                    }
                }
            }
        });

        usersTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = usersTable.getSelectedRow();
            if (selectedRow != -1) {
                String name = (String) tableModel.getValueAt(selectedRow, 1);
                String email = (String) tableModel.getValueAt(selectedRow, 2);
                String userType = (String) tableModel.getValueAt(selectedRow, 3);

                nameField.setText(name);
                emailField.setText(email);
                userTypeComboBox.setSelectedItem(userType);
            }
        });
    }

    private void loadUsers() {
        try {
            List<Usuarios> usuarios = UsuariosMetodos.getAllUsuarios();
            tableModel.setRowCount(0);
            for (Usuarios usuario : usuarios) {
                tableModel.addRow(new Object[]{usuario.getIdUsuario(), usuario.getNombreUsuario(), usuario.getEmail(), usuario.getTipoUsuario()});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los usuarios.");
        }
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        userTypeComboBox.setSelectedIndex(0);
    }

}
