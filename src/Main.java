// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== ONLINE QUIZ APPLICATION =====");
        System.out.print("Enter Your Name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            name = "Anonymous";
        }

        User user = new User(name);
        Quiz quiz = new Quiz(user);

        while (true) {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Start Quiz");
            System.out.println("2. View Leaderboard");
            System.out.println("3. Exit");
            System.out.print("\nEnter Choice: ");

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        quiz.startQuiz();
                        break;
                    case 2:
                        ScoreManager.showLeaderboard();
                        break;
                    case 3:
                        System.out.println("\nThank You for playing! Goodbye 👋");
                        sc.close();
                        System.exit(0);
                    default:
                        System.out.println("\nInvalid Choice! Please enter 1, 2, or 3.");
                }
            } else {
                sc.nextLine();
                System.out.println("\nInvalid input! Please enter a number.");
            }
        }
    }
}