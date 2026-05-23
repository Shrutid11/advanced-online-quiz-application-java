// Quiz.java
import java.util.*;

public class Quiz {
    private ArrayList<Question> questions = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private int score = 0;
    private User user;

    public Quiz(User user) {
        this.user = user;
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question("Which language is platform independent?", 
            new String[]{"C", "Java", "Assembly", "Python"}, 2));

        questions.add(new Question("Which keyword is used for inheritance?", 
            new String[]{"this", "super", "extends", "implements"}, 3));

        questions.add(new Question("Which SQL command retrieves data?", 
            new String[]{"SELECT", "UPDATE", "DELETE", "DROP"}, 1));

        questions.add(new Question("Which collection stores unique values?", 
            new String[]{"ArrayList", "HashSet", "Vector", "LinkedList"}, 2));

        questions.add(new Question("Who developed Java?", 
            new String[]{"Google", "Oracle", "Sun Microsystems", "Microsoft"}, 3));

        questions.add(new Question("Which package contains Scanner class?", 
            new String[]{"java.io", "java.lang", "java.util", "java.sql"}, 3));

        questions.add(new Question("Which method starts thread execution?", 
            new String[]{"execute()", "run()", "start()", "begin()"}, 3));

        questions.add(new Question("Which SQL clause filters records?", 
            new String[]{"ORDER BY", "GROUP BY", "WHERE", "HAVING"}, 3));
    }

    public void startQuiz() {
        Collections.shuffle(questions);
        score = 0;

        System.out.println("\n===== QUIZ STARTED =====");
        System.out.println("Answer by entering the option number (1-4)");

        for (Question q : questions) {
            q.displayQuestion();
            
            int answer = getValidAnswer();
            
            if (q.checkAnswer(answer)) {
                System.out.println("✅ Correct Answer!\n");
                score++;
            } else {
                System.out.println("❌ Wrong Answer!\n");
            }
        }
        
        displayResult();
    }

    private int getValidAnswer() {
        while (true) {
            System.out.print("Enter your answer (1-4): ");
            if (sc.hasNextInt()) {
                int answer = sc.nextInt();
                sc.nextLine(); // consume newline
                if (answer >= 1 && answer <= 4) {
                    return answer;
                }
            } else {
                sc.nextLine(); // consume invalid input
            }
            System.out.println("Invalid input! Please enter a number between 1 and 4.");
        }
    }

    private void displayResult() {
        int total = questions.size();
        double percentage = ((double) score / total) * 100;
        
        String grade;
        if (percentage >= 80) grade = "A";
        else if (percentage >= 60) grade = "B";
        else if (percentage >= 40) grade = "C";
        else grade = "Fail";

        System.out.println("\n===== QUIZ RESULT =====");
        System.out.println("Player Name     : " + user.getName());
        System.out.println("Total Questions : " + total);
        System.out.println("Correct Answers : " + score);
        System.out.println("Wrong Answers   : " + (total - score));
        System.out.println("Percentage      : " + String.format("%.2f", percentage) + "%");
        System.out.println("Grade           : " + grade);

        if (percentage >= 80) System.out.println("Performance : Excellent 🎉");
        else if (percentage >= 60) System.out.println("Performance : Good 👍");
        else if (percentage >= 40) System.out.println("Performance : Average");
        else System.out.println("Performance : Needs Improvement");

        ScoreManager.saveScore(user.getName(), percentage, grade);
    }
}