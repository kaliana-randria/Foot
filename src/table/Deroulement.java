package table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Deroulement {
    private int debut; 
    private int fin;
    private String placement;
    private int duree;

    public Deroulement() {}
    public Deroulement(int debut, int fin, String placement, int duree) {
        this.debut = debut;
        this.fin = fin;
        this.placement = placement;
        this.duree = duree;
    }
    
    public int getDebut() {
        return debut;
    }
    public void setDebut(int debut) {
        this.debut = debut;
    }
    public int getFin() {
        return fin;
    }
    public void setFin(int fin) {
        this.fin = fin;
    }
    public String getPlacement() {
        return placement;
    }
    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    //fonction mi-inserer valeur dans table Deroulement
    public void insertionValeurDeroulement(Connection connection, int debut, int fin, String placement, int duree) throws Exception {
        if (duree < 20) {
            throw new Exception("Lasa latsakin'ny 20s ny duree");
        }
        String sql = "INSERT INTO Deroulement (id_deroulement, debut, fin, placement, duree) VALUES ('D' || seq_Deroulement.NEXTVAL, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, debut);
            pstmt.setInt(2, fin);
            pstmt.setString(3, placement);
            pstmt.setInt(4, duree);
            
            pstmt.executeUpdate();
            connection.commit(); 
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback(); 
            throw e; 
        }
    }
    
}

