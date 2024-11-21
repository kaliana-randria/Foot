package fenetre;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.time.LocalTime;
import javax.swing.*;
import java.util.List;

import table.Placement;

public class Dessin extends JPanel {
    private boolean feuActif;
    private int dureeInitiale;
    private int dureeOriginale;
    private JLabel timerLabel;
    private JLabel secteurLabel;
    private LocalTime debut;
    private LocalTime fin;
    private int direction;
    private JLabel positionPlacement;
    private JButton boutonPassage;

    public Dessin(Connection c, String secteur, String idSecteur, int duree, int debut, int fin, String feu, int direction, String placement) throws Exception{
        
        this.dureeOriginale = duree;
        this.debut = LocalTime.of(debut / 60, debut % 60);
        this.fin = LocalTime.of(fin / 60, fin % 60);
        this.direction = direction;

        if (feu.equals("F1")) {
            this.feuActif = true;
            this.dureeInitiale = duree;
        } else {
            this.feuActif = false;
            this.dureeInitiale = 30;
        }

        setLayout(new BorderLayout());

        JPanel titreCentre = new JPanel(new FlowLayout(FlowLayout.CENTER));

        secteurLabel = new JLabel("Secteur " + secteur + " (" + idSecteur + ") ", SwingConstants.CENTER);
        secteurLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titreCentre.add(secteurLabel);

        timerLabel = new JLabel("  Timer : " + this.dureeInitiale + "s", SwingConstants.LEFT);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titreCentre.add(timerLabel);
        
        Placement P=new Placement();
        List<String> placementsMemeDirection = P.makaPlacementMitovyDirection(c, idSecteur, direction);
        StringBuilder placementsDirection = new StringBuilder();
        for (int i = 0; i < placementsMemeDirection.size(); i++) {
            String placementMitovy = placementsMemeDirection.get(i);
            if (!placementMitovy.equals(placement)) {
                placementsDirection.append(placementMitovy + "  ");
            }
        }
        
        if (direction == 1) {
            positionPlacement = new JLabel(" Placement " + placement + " direction horizontale avec: " + placementsDirection.toString(), SwingConstants.CENTER);
            positionPlacement.setFont(new Font("Arial", Font.ROMAN_BASELINE, 15));
            titreCentre.add(positionPlacement);
        } else {
            positionPlacement = new JLabel(" Placement " + placement + " direction verticale avec: " + placementsDirection.toString(), SwingConstants.CENTER);
            positionPlacement.setFont(new Font("Arial", Font.ROMAN_BASELINE, 15));
            titreCentre.add(positionPlacement);
        }

        boutonPassage = new JButton("Passage");
        boutonPassage.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        titreCentre.add(boutonPassage);

        add(titreCentre, BorderLayout.NORTH);

        Timer timer = new Timer(1000, new TimerListener());
        timer.start();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            LocalTime heureZao = LocalTime.now();

            if (heureZao.isAfter(debut) && heureZao.isBefore(fin)) {
                dureeInitiale--;

                if (dureeInitiale <= 0) {
                    feuActif = !feuActif;
                    if (feuActif) {
                        dureeInitiale = dureeOriginale;
                    } else {
                        dureeInitiale = 30;
                    }
                }
 
                timerLabel.setText("  Timer : " + dureeInitiale + "s");
                repaint();
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.GRAY);
        int bordure1 = 20;
        int bordure2 = 20;
        g2d.fillRoundRect(100, 450, 800, 100, bordure1, bordure2);
        g2d.fillRoundRect(450, 100, 100, 800, bordure1, bordure2);

        g2d.setColor(Color.WHITE);
        for (int i = 120; i < 850; i += 60) {
            g2d.fillRect(i, 495, 45, 10);
        }
        for (int i = 120; i < 850; i += 60) {
            g2d.fillRect(495, i, 10, 45);
        }

        this.saryFeuCouleur(g2d);
    }

    private void saryFeuCouleur(Graphics2D g2d) {
        int diametre = 30;
        Color couleurHorizontale;
        Color couleurVerticale;

        if (direction==1) { 
            if (feuActif) {
                couleurHorizontale = Color.GREEN;
                if (dureeInitiale <= 5) {
                    couleurHorizontale = Color.YELLOW;
                }
                couleurVerticale = Color.RED;
            } else {
                couleurHorizontale = Color.RED;
                couleurVerticale = Color.GREEN;
                if (dureeInitiale <= 5) {
                    couleurVerticale = Color.YELLOW;
                }
            }
        } else { 
            if (feuActif) {
                couleurVerticale = Color.GREEN;
                if (dureeInitiale <= 5) {
                    couleurVerticale = Color.YELLOW;
                }
                couleurHorizontale = Color.RED;
            } else {
                couleurVerticale = Color.RED;
                couleurHorizontale = Color.GREEN;
                if (dureeInitiale <= 5) {
                    couleurHorizontale = Color.YELLOW;
                }
            }
        }

        g2d.setColor(couleurHorizontale);
        g2d.fillOval(120, 410, diametre, diametre); 
        g2d.fillOval(850, 560, diametre, diametre); 

        g2d.setColor(couleurVerticale);
        g2d.fillOval(560, 120, diametre, diametre); 
        g2d.fillOval(410, 850, diametre, diametre); 
    }
}
