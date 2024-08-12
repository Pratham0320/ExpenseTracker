import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class ExpenseManager {
    private static final String FILE_NAME = "expenses.txt";
    public static void saveExpenses(List<Expense> expenses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Expense expense : expenses) {
                writer.write(expense.getDescription() + "," + expense.getAmount() + "," + expense.getCategory());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Expense> loadExpenses() {
        List<Expense> expenses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {  // Ensure there are three parts (description, amount, category)
                    String description = parts[0];
                    double amount = Double.parseDouble(parts[1]);
                    String category = parts[2];
                    expenses.add(new Expense(description, amount, category));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return expenses;
    }
}
