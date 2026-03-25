import java.sql.*;
import java.util.Scanner;

public class ApplyCourse {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                    "system",
                    "samaina_14"
            );

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Student ID: ");
            int studentId = sc.nextInt();

            System.out.print("Enter Course ID: ");
            int courseId = sc.nextInt();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO SYSTEM.APPLICATIONS (student_id, course_id) VALUES (?, ?)"
            );

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            int rows = ps.executeUpdate();
            System.out.println("Rows inserted: " + rows);
            System.out.println("Application Submitted!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}