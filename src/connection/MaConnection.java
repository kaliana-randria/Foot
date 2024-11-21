package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnection {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(String url, String user, String mdp) {
        try {
            this.connection = DriverManager.getConnection(url, user, mdp);
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            this.connection = null;
        }
    }

    public void closeConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
                System.out.println("Connexion bien fermee.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
