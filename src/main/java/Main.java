import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:\n1. Add Entry\n2. Load from File\n3. Show Monthly Summary\n4. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter type (income/expense): ");
                    String type = sc.nextLine();
                    System.out.println("Enter category (e.g., salary, rent, food): ");
                    String category = sc.nextLine();
                    System.out.println("Enter amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    LocalDate date = LocalDate.parse(sc.nextLine());
                    tracker.addTransaction(new Transaction(type, category, amount, date));
                    System.out.println("Transaction added.");
                    break;
                case 2:
                    System.out.println("Enter file path to load data: ");
                    String filePath = sc.nextLine();
                    tracker.loadFromFile(filePath);
                    break;
                case 3:
                    System.out.println("Enter month (1-12): ");
                    int month = sc.nextInt();
                    System.out.println("Enter year: ");
                    int year = sc.nextInt();
                    tracker.showMonthlySummary(month, year);
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}