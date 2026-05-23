// ScoreManager.java
import java.io.*;

public class ScoreManager {
    
    public static void saveScore(String name, double percentage, String grade) {
        try (FileWriter fw = new FileWriter("scores.txt", true)) {
            fw.write(String.format("%-20s | %6.2f%% | %s\n", name, percentage, grade));
        } catch (IOException e) {
            System.out.println("Error saving score: " + e.getMessage());
        }
    }

    public static void showLeaderboard() {
        System.out.println("\n===== LEADERBOARD =====");
        try (BufferedReader br = new BufferedReader(new FileReader("scores.txt"))) {
            String line;
            boolean hasScores = false;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                hasScores = true;
            }
            if (!hasScores) {
                System.out.println("No scores available yet.");
            }
        } catch (IOException e) {
            System.out.println("No Scores Available");
        }
    }
}