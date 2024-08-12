import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class ExpenseTrackerGUI extends Application {
    private ObservableList<Expense> expenses = FXCollections.observableArrayList();
    private ListView<Expense> listView = new ListView<>(expenses);
    private TextField descriptionField = new TextField();
    private TextField amountField = new TextField();
    private TextField categoryField = new TextField();
    private Label totalAmountLabel = new Label("Total Amount: $0.00");
    private TextField searchField = new TextField();
    private ObservableList<Expense> filteredExpenses = FXCollections.observableArrayList();
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Expense Tracker");
        expenses.addAll(ExpenseManager.loadExpenses());
        filteredExpenses.addAll(expenses);
        listView.setItems(filteredExpenses);
        updateTotalAmount();
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        GridPane inputLayout = new GridPane();
        inputLayout.setHgap(10);
        inputLayout.setVgap(10);
        inputLayout.setPadding(new Insets(10));
        inputLayout.add(new Label("Description:"), 0, 0);
        inputLayout.add(descriptionField, 1, 0);
        inputLayout.add(new Label("Amount:"), 0, 1);
        inputLayout.add(amountField, 1, 1);
        inputLayout.add(new Label("Category:"), 0, 2);
        inputLayout.add(categoryField, 1, 2);
        Button addButton = new Button("Add Expense");
        Button editButton = new Button("Edit Expense");
        Button deleteButton = new Button("Delete Expense");
        Button saveButton = new Button("Save Expenses");
        addButton.setOnAction(e -> addExpense());
        editButton.setOnAction(e -> editExpense());
        deleteButton.setOnAction(e -> deleteExpense());
        saveButton.setOnAction(e -> saveExpenses());
        VBox buttonPanel = new VBox(10, addButton, editButton, deleteButton, saveButton);
        buttonPanel.setPadding(new Insets(10));
        searchField.setPromptText("Search by description...");
        searchField.textProperty().addListener((obs, oldText, newText) -> filterExpenses(newText));
        root.getChildren().addAll(inputLayout, searchField, listView, totalAmountLabel, buttonPanel);
        Scene scene = new Scene(root, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void addExpense() {
        String description = descriptionField.getText();
        double amount;
        String category = categoryField.getText();
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid amount", "Please enter a valid amount.");
            return;
        }
        if (description.isEmpty() || category.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Empty fields", "Description and category cannot be empty.");
            return;
        }
        Expense newExpense = new Expense(description, amount, category);
        expenses.add(newExpense);
        filteredExpenses.add(newExpense); // Add to filtered list
        descriptionField.clear();
        amountField.clear();
        categoryField.clear();
        updateTotalAmount();
    }
    private void editExpense() {
        Expense selectedExpense = listView.getSelectionModel().getSelectedItem();
        if (selectedExpense != null) {
            descriptionField.setText(selectedExpense.getDescription());
            amountField.setText(String.valueOf(selectedExpense.getAmount()));
            categoryField.setText(selectedExpense.getCategory());
            expenses.remove(selectedExpense);
            filteredExpenses.remove(selectedExpense); // Remove from filtered list
        } else {
            showAlert(Alert.AlertType.WARNING, "No selection", "Please select an expense to edit.");
        }
    }
    private void deleteExpense() {
        Expense selectedExpense = listView.getSelectionModel().getSelectedItem();
        if (selectedExpense != null) {
            expenses.remove(selectedExpense);
            filteredExpenses.remove(selectedExpense); // Remove from filtered list
            updateTotalAmount();
        } else {
            showAlert(Alert.AlertType.WARNING, "No selection", "Please select an expense to delete.");
        }
    }
    private void saveExpenses() {
        ExpenseManager.saveExpenses(expenses);
        showAlert(Alert.AlertType.INFORMATION, "Save Successful", "Expenses have been saved.");
    }
    private void filterExpenses(String query) {
        filteredExpenses.clear();
        if (query == null || query.isEmpty()) {
            filteredExpenses.addAll(expenses);
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (Expense expense : expenses) {
                if (expense.getDescription().toLowerCase().contains(lowerCaseQuery)) {
                    filteredExpenses.add(expense);
                }
            }
        }
        if (filteredExpenses.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "No Results", "No expenses match the search criteria.");
        }
    }
    private void updateTotalAmount() {
        double total = expenses.stream().mapToDouble(Expense::getAmount).sum();
        totalAmountLabel.setText(String.format("Total Amount: $%.2f", total));
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
