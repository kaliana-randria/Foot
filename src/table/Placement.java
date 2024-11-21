package table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Placement {
    private String id_Placement;
    private String feu;
    private String secteur;
    private int direction;
    

    public Placement() {}
    public Placement(String id_Placement, String feu, String secteur, int direction) {
        this.id_Placement = id_Placement;
        this.feu = feu;
        this.secteur = secteur;
        this.direction = direction;
    }
    
    public String getId_Placement() {
        return id_Placement;
    }
    public void setId_Placement(String id_Placement) {
        this.id_Placement = id_Placement;
    }
    public String getFeu() {
        return feu;
    }
    public void setFeu(String feu) {
        this.feu = feu;
    }
    public String getSecteur() {
        return secteur;
    }
    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }
    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public List<String> makaIdPlacementBySecteur(Connection c, String secteur) throws SQLException {
        List<String> tab = new ArrayList<>();
        String requete = "SELECT id_Placement FROM Placement WHERE Secteur = ?";
        try (PreparedStatement stmt = c.prepareStatement(requete)) {
            stmt.setString(1, secteur);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tab.add(rs.getString("id_Placement"));
                }
                rs.close(); 
            }
            stmt.close();
        }
        return tab;
    }

    public String getSecteurByPlacement(Connection c, String idPlacement) throws SQLException {
        String secteur = null;
        String requete = "SELECT Secteur FROM Placement WHERE id_Placement = ?";
        try (PreparedStatement stmt = c.prepareStatement(requete)) {
            stmt.setString(1, idPlacement);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    secteur = rs.getString("Secteur");
                }
                rs.close(); 
            }
            stmt.close();
        }
        return secteur;
    }

    public String getFeuByPlacement(Connection c, String idPlacement) throws SQLException {
        String feu = null;
        String requete = "SELECT Feu FROM Placement WHERE id_Placement = ?";
        try (PreparedStatement stmt = c.prepareStatement(requete)) {
            stmt.setString(1, idPlacement);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    feu = rs.getString("feu");
                }
                rs.close(); 
            }
            stmt.close();
        }
        return feu;
    }

    public int getDirectionByPlacement(Connection c, String idPlacement) throws SQLException {
        int direction = 0;
        String requete = "SELECT Direction FROM Placement WHERE id_Placement = ?";
        try (PreparedStatement stmt = c.prepareStatement(requete)) {
            stmt.setString(1, idPlacement);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    direction = rs.getInt("direction");
                }
                rs.close(); 
            }
            stmt.close();
        }
        return direction;
    }

    public  List<String> makaPlacementMitovyDirection(Connection c, String idSecteur, int direction) throws SQLException {
        List<String> tab = new ArrayList<>();
        String requete = "SELECT id_Placement FROM Placement WHERE Direction = ? AND Secteur = ?";
        try (PreparedStatement stmt = c.prepareStatement(requete)) {
            stmt.setInt(1, direction);
            stmt.setString(2, idSecteur);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tab.add(rs.getString("id_Placement"));
                }
                rs.close(); 
            }
            stmt.close();
        }
        return tab;
    }

}
