import connection.MaConnection;
import fenetre.Fenetre;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        MaConnection myConnection = new MaConnection();
        String url = "jdbc:oracle:thin:@localhost:1521:ORCLCDB";
        String user = "C##prog";
        String mdp = "prog";

        try {
            myConnection.setConnection(url, user, mdp);
            Connection connection = myConnection.getConnection();

            if (connection == null || connection.isClosed()) {
                throw new Exception("Erreur de connexion : la connexion est fermee.");
            }

            System.out.println("Connexion ouverte et valide.");

            Fenetre fenetre = new Fenetre(connection);
            fenetre.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    myConnection.closeConnection();
                    System.exit(0); 
                }
            });
            // connection.commit();
        } catch (Exception e) {
            try {
                if (myConnection.getConnection() != null && !myConnection.getConnection().isClosed()) {
                    myConnection.getConnection().rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}