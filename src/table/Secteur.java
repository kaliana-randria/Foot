package table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Secteur {
    private String idSecteur;
    private String nom;
    
    public Secteur() {}
    public Secteur(String idSecteur, String nom) {
        this.idSecteur = idSecteur;
        this.nom = nom;
    }
    
    public String getIdSecteur() {
        return idSecteur;
    }
    public void setIdSecteur(String idSecteur) {
        this.idSecteur = idSecteur;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String makaIdSecteur(Connection c, String nom) throws SQLException {
        String id_secteur=null;
        String requete = "SELECT id_Secteur FROM Secteur WHERE nom = ?";
        try (PreparedStatement stmt = c.prepareStatement(requete)) {
            stmt.setString(1, nom);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    id_secteur=rs.getString("id_Secteur");
                }
                rs.close(); 
            }
            stmt.close();
        }
        return id_secteur;
    }

    public String makaNomSecteur(Connection c, String idSecteur) throws SQLException {
        String nom=null;
        String requete = "SELECT Nom FROM Secteur WHERE id_Secteur = ?";
        try (PreparedStatement stmt = c.prepareStatement(requete)) {
            stmt.setString(1, idSecteur);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nom=rs.getString("Nom");
                }
                rs.close(); 
            }
            stmt.close();
        }
        return nom;
    }
}
