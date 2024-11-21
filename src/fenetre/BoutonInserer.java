package fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import table.Deroulement;
import table.Placement;
import table.Secteur;

public class BoutonInserer {
    public BoutonInserer(JButton bouton, JTextField debutField, JTextField finField, JTextField placementField, JTextField dureeField, Connection connection, JFrame frame) {
        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (connection == null || connection.isClosed()) {
                        JOptionPane.showMessageDialog(null, "La connexion dans base de donnees fermee.");
                    }

                    Deroulement deroulement = new Deroulement();
                    int debut = Integer.parseInt(debutField.getText());
                    int fin = Integer.parseInt(finField.getText());
                    String placement = placementField.getText();
                    int duree = Integer.parseInt(dureeField.getText());

                    if (debut > fin) {
                        JOptionPane.showMessageDialog(null, "Le debut doit etre inferieur au fin.");
                    }
                    
                    deroulement.insertionValeurDeroulement(connection, debut, fin, placement, duree);
                    Placement P = new Placement();
                    Secteur secteur = new Secteur();
                    String idSecteur = P.getSecteurByPlacement(connection, placement);
                    String secteurNom = secteur.makaNomSecteur(connection, idSecteur);
                    String feu = P.getFeuByPlacement(connection, placement);
                    int direction = P.getDirectionByPlacement(connection, placement);
                    
                    JOptionPane.showMessageDialog(null, "Insertion reussie !");

                    Dessin dessin = new Dessin(connection, secteurNom, idSecteur, duree, debut, fin, feu, direction, placement);
                    frame.getContentPane().removeAll();
                    frame.add(dessin);
                    frame.revalidate(); 
                    frame.repaint();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
                }
            }
        });
    }
}
