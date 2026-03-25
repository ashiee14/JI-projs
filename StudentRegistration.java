import java.sql.*;
import java.util.Scanner;

public class StudentRegistration {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                    "system",
                    "samaina_14"
            );

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Marks: ");
            int marks = sc.nextInt();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO SYSTEM.Students (name, email, marks) VALUES (?, ?, ?)"
            );

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, marks);

            ps.executeUpdate();

            System.out.println("Student Registered!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}