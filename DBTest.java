import java.sql.*;

public class DBTest {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quizdb", "root", "samaina_14");

            System.out.println("Connected to database!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}