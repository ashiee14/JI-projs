import java.sql.*;

public class CollegeAdmission {
    public static void main(String[] args) {
        try {
            // Oracle driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Oracle connection URL
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", // orcl/xe depending on your setup
                    "system",   // your Oracle username
                    "samaina_14"  // your Oracle password
            );

            System.out.println("Connected!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}