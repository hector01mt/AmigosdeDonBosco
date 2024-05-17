package Vistas.AlumnoDashboard;

import Modelo.Usuarios;

import javax.swing.*;
import java.awt.*;

public class AlumnoDashboard extends JFrame {

    private JPanel alumnoPanel;
    private JButton searchDocumentsButton;
    private JButton borrowedDocumentsButton;

    public AlumnoDashboard(Usuarios usuario) {

        alumnoPanel = new JPanel();
        alumnoPanel.setLayout(new BorderLayout());
        searchDocumentsButton = new JButton("Buscar en la biblioteca");
        borrowedDocumentsButton = new JButton("Solicitar prestamo de libro");

        alumnoPanel.add(searchDocumentsButton, BorderLayout.NORTH);
        alumnoPanel.add(borrowedDocumentsButton, BorderLayout.CENTER);
        setContentPane(alumnoPanel);

        setTitle("Alumno Dashboard");
        setContentPane(alumnoPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

       // searchDocumentsButton.addActionListener(e -> new SearchDocumentsForm().setVisible(true));
       // borrowedDocumentsButton.addActionListener(e -> new BorrowedDocumentsForm().setVisible(true));
    }

}
