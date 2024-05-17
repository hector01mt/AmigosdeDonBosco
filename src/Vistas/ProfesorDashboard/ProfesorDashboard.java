package Vistas.ProfesorDashboard;

import Modelo.Usuarios;

import javax.swing.*;
import java.awt.*;

public class ProfesorDashboard extends JFrame {

    private JPanel profesorPanel;
    private JButton searchDocumentsButton;
    private JButton borrowedDocumentsButton;

    public ProfesorDashboard(Usuarios usuario) {

        profesorPanel = new JPanel();
        profesorPanel.setLayout(new BorderLayout());
        searchDocumentsButton = new JButton("Buscar en la biblioteca");
        borrowedDocumentsButton = new JButton("Solicitar prestamo de libro");

        profesorPanel.add(searchDocumentsButton, BorderLayout.NORTH);
        profesorPanel.add(borrowedDocumentsButton, BorderLayout.CENTER);
        setContentPane(profesorPanel);

        setTitle("Profesor Dashboard");
        setContentPane(profesorPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        //searchDocumentsButton.addActionListener(e -> new SearchDocumentsForm().setVisible(true));
        //borrowedDocumentsButton.addActionListener(e -> new BorrowedDocumentsForm().setVisible(true));
    }

}
