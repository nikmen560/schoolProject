import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONN = "jdbc:mysql://localhost:3307/school";


    public static void main(String[] args) {
        Connection con = null;

        try {
            con = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
            System.out.println("connected");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (con != null) {

                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
