import java.sql.*;
import java.util.Scanner;

public class LoginOracle {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String user = sc.nextLine();

        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@//localhost:1521/XEPDB1",
                "system",
                "samaina_14"
        );

        PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?"
        );

        ps.setString(1, user);
        ps.setString(2, pass);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid login!");
        }
    }
}