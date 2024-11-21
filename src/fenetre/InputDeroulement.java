package fenetre;

import java.awt.*;
import java.sql.Connection;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InputDeroulement extends JPanel {
    private JTextField debutField;
    private JTextField finField;
    private JTextField placementField;
    private JTextField dureeField;
    private JButton bouton;
    private JButton boutonP;
    private JTextField secteurField;
    private JPanel resultPanel;

    public InputDeroulement(Connection connection, JFrame frame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20)); 

        secteurField = addField("Secteur:");
        boutonP = new JButton("Placements misy");
        boutonP.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        add(boutonP);
        
        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(10));
        add(resultPanel);
        new BoutonVoirPlacement(boutonP, secteurField, connection, resultPanel);
        
        debutField = addField("Debut:");
        finField = addField("Fin:");
        placementField = addField("Placement:");
        dureeField = addField("Duree:");
        
        bouton = new JButton("Inserer");
        bouton.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        add(bouton);

        new BoutonInserer(bouton, debutField, finField, placementField, dureeField, connection, frame);
    }

    private JTextField addField(String label) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(new EmptyBorder(5, 0, 5, 0));
        JLabel jLabel = new JLabel(label);
        JTextField textField = new JTextField(20);
        panel.add(jLabel);
        panel.add(textField);
        
        add(panel);
        add(Box.createVerticalStrut(10));
        return textField;
    }
}
