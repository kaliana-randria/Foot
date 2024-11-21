package fenetre;

import java.awt.BorderLayout;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {

    public Fenetre(Connection connection) {
        super("Feu de signalisation");
        setSize(1000, 1000); 
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        // inputPanel.setLayout(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(200, 20, 20, 20)); 

        InputDeroulement input = new InputDeroulement(connection, this);
        inputPanel.add(input, BorderLayout.CENTER);

        // Dessin Dessin = new Dessin();
        // add(Dessin, BorderLayout.CENTER);

        add(inputPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}