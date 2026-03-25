import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.*;
public class quizz {
    public static void main(String[] args) throws Exception {

        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@//localhost:1521/XEPDB1",
                "system",
                "samaina_14"
        );

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT * FROM QUESTIONS ORDER BY DBMS_RANDOM.VALUE FETCH FIRST 2 ROWS ONLY"
        );

        Scanner sc = new Scanner(System.in);
        int score = 0;

        while(rs.next()) {

            System.out.println("\n" + rs.getString("question"));

            System.out.println("1. " + rs.getString("option1"));
            System.out.println("2. " + rs.getString("option2"));
            System.out.println("3. " + rs.getString("option3"));
            System.out.println("4. " + rs.getString("option4"));

            ExecutorService ex = Executors.newSingleThreadExecutor();

            Future<Integer> future = ex.submit(() -> {
                System.out.print("Enter your answer (10 sec): ");
                return sc.nextInt();
            });

            int ans = -1;

            try {
                ans = future.get(10, TimeUnit.SECONDS); // ⏱ 10 sec timer
            } catch (TimeoutException e) {
                System.out.println("\nTime's up! Moving to next question...");
                future.cancel(true);
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }

            ex.shutdownNow();
            int correct = rs.getInt("correct_option");  // 👈 get correct answer

            if(ans == correct) {
                score++;
            } else {
                System.out.println("Wrong! Correct answer is option " + correct);
            }
        }

        System.out.println("\nYour Score: " + score);
        PreparedStatement ps2 = con.prepareStatement(
                "INSERT INTO RESULTS (username, score) VALUES (?, ?)"
        );

        ps2.setString(1, "aishani");  // later you can use logged-in user
        ps2.setInt(2, score);

        ps2.executeUpdate();

        System.out.println("Score saved to database!");
    }
}