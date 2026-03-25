import java.sql.*;

public class MeritSystem {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                    "system",
                    "samaina_14"
            );

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT a.app_id, s.marks, c.cutoff " +
                            "FROM SYSTEM.APPLICATIONS a " +
                            "JOIN SYSTEM.STUDENTS s ON a.student_id = s.student_id " +
                            "JOIN SYSTEM.COURSES c ON a.course_id = c.course_id"
            );

            while (rs.next()) {
                int appId = rs.getInt("app_id");
                int marks = rs.getInt("marks");
                int cutoff = rs.getInt("cutoff");

                String status;
                if (marks >= cutoff) {
                    status = "Approved";
                } else {
                    status = "Rejected";
                }

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE SYSTEM.APPLICATIONS SET status=? WHERE app_id=?"
                );

                ps.setString(1, status);
                ps.setInt(2, appId);
                ps.executeUpdate();
            }

            System.out.println("Merit Process Completed!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}