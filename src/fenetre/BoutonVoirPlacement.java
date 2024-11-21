package fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;
import javax.swing.*;

import table.Placement;
import table.Secteur;

public class BoutonVoirPlacement {
    public BoutonVoirPlacement(JButton bouton, JTextField secteurField, Connection connection, JPanel resultPanel) {
        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (connection == null || connection.isClosed()) {
                        JOptionPane.showMessageDialog(null, "La connexion est fermee.");
                    }

                    resultPanel.removeAll();

                    Placement placement = new Placement();
                    Secteur secteur = new Secteur();

                    String nomSecteur = secteurField.getText();
                    String idSecteur = secteur.makaIdSecteur(connection, nomSecteur);

                    List<String> tabP = placement.makaIdPlacementBySecteur(connection, idSecteur);
                    for (int i=0; i<tabP.size(); i++) {
                        JLabel jLabel = new JLabel(tabP.get(i));
                        resultPanel.add(jLabel);
                    }

                    resultPanel.revalidate();
                    resultPanel.repaint();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
                }
            }
        });
    }
}
