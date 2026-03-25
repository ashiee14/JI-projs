import java.sql.*;

public class DBTestOracle {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE",
                    "system",
                    "samaina_14"
            );

            System.out.println("Connected to Oracle!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}