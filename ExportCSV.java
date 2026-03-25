import java.sql.*;
import java.io.FileWriter;

public class ExportCSV {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                    "system",
                    "samaina_14"
            );

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT a.app_id, s.name, c.course_name, a.status " +
                            "FROM SYSTEM.APPLICATIONS a " +
                            "JOIN SYSTEM.STUDENTS s ON a.student_id = s.student_id " +
                            "JOIN SYSTEM.COURSES c ON a.course_id = c.course_id"
            );

            FileWriter fw = new FileWriter("admissions.csv");

            // Header
            fw.write("App_ID,Student_Name,Course,Status\n");

            while (rs.next()) {
                fw.write(
                        rs.getInt("app_id") + "," +
                                rs.getString("name") + "," +
                                rs.getString("course_name") + "," +
                                rs.getString("status") + "\n"
                );
            }

            fw.close();

            System.out.println("CSV File Generated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}