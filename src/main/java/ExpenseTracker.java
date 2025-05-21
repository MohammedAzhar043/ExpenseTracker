import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpenseTracker {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void loadFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String type = parts[0];
                    String category = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    LocalDate date = LocalDate.parse(parts[3]);
                    addTransaction(new Transaction(type, category, amount, date));
                }
            }
            System.out.println("Data loaded from file successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void showMonthlySummary(int month, int year) {
        List<Transaction> monthlyTransactions = transactions.stream()
            .filter(t -> t.getDate().getMonthValue() == month && t.getDate().getYear() == year)
            .collect(Collectors.toList());

        Map<String, Double> incomeSummary = new HashMap<>();
        Map<String, Double> expenseSummary = new HashMap<>();

        for (Transaction t : monthlyTransactions) {
            Map<String, Double> map = t.getType().equalsIgnoreCase("income") ? incomeSummary : expenseSummary;
            map.put(t.getCategory(), map.getOrDefault(t.getCategory(), 0.0) + t.getAmount());
        }

        System.out.println("--- Monthly Summary for " + month + "/" + year + " ---");
        System.out.println("Income:");
        incomeSummary.forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("Expenses:");
        expenseSummary.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}