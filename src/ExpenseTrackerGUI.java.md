# Expense Tracker GUI - Internal Code Documentation

## Table of Contents

* [1. Introduction](#1-introduction)
* [2. Class Overview: `ExpenseTrackerGUI`](#2-class-overview-expensetrackergui)
    * [2.1 `start()` Method](#21-start-method)
    * [2.2 `addExpense()` Method](#22-addexpense-method)
    * [2.3 `editExpense()` Method](#23-editexpense-method)
    * [2.4 `deleteExpense()` Method](#24-deleteexpense-method)
    * [2.5 `saveExpenses()` Method](#25-saveexpenses-method)
    * [2.6 `filterExpenses()` Method](#26-filterexpenses-method)
    * [2.7 `updateTotalAmount()` Method](#27-updatetotalamount-method)
    * [2.8 `showAlert()` Method](#28-showalert-method)
* [3. External Dependencies](#3-external-dependencies)


<a name="1-introduction"></a>
## 1. Introduction

This document provides internal code documentation for the `ExpenseTrackerGUI` application.  This JavaFX application allows users to add, edit, delete, and search expenses, displaying a running total.  Data persistence is handled by the `ExpenseManager` class (not included in this documentation).


<a name="2-class-overview-expensetrackergui"></a>
## 2. Class Overview: `ExpenseTrackerGUI`

The `ExpenseTrackerGUI` class extends `javafx.application.Application` and manages the user interface and application logic for the expense tracker.


<a name="21-start-method"></a>
### 2.1 `start()` Method

This method initializes the GUI components, loads expenses from the `ExpenseManager`, and sets up event handlers for user interaction.

| Step | Description | Code Snippet |
|---|---|---|
| 1 | Sets the stage title. | `primaryStage.setTitle("Expense Tracker");` |
| 2 | Loads expenses using `ExpenseManager.loadExpenses()` and adds them to `expenses` and `filteredExpenses` lists. | `expenses.addAll(ExpenseManager.loadExpenses());` <br> `filteredExpenses.addAll(expenses);` |
| 3 | Sets the `listView` items to the `filteredExpenses`. | `listView.setItems(filteredExpenses);` |
| 4 | Updates the total amount display. | `updateTotalAmount();` |
| 5 | Creates and arranges UI elements using `VBox` and `GridPane` layouts. |  `VBox root = new VBox(10);` <br> ... (Layout creation) ... |
| 6 | Sets up action listeners for buttons (Add, Edit, Delete, Save). | `addButton.setOnAction(e -> addExpense());` <br> ... (other buttons) ... |
| 7 | Adds a search field with a listener to filter expenses. | `searchField.textProperty().addListener((obs, oldText, newText) -> filterExpenses(newText));` |
| 8 | Adds all UI elements to the root `VBox`. | `root.getChildren().addAll(inputLayout, searchField, listView, totalAmountLabel, buttonPanel);` |
| 9 | Creates and sets the scene, then shows the primary stage. | `Scene scene = new Scene(root, 400, 600);` <br> `primaryStage.setScene(scene);` <br> `primaryStage.show();` |


<a name="22-addexpense-method"></a>
### 2.2 `addExpense()` Method

This method adds a new expense to the application.  It handles input validation to ensure that the amount is a valid number and that the description and category fields are not empty.

| Step | Description |
|---|---|
| 1 | Retrieves input values from text fields. |
| 2 | Uses `Double.parseDouble()` to convert the amount to a double; handles `NumberFormatException` with an error alert. |
| 3 | Checks if description and category fields are empty; shows an error alert if so. |
| 4 | Creates a new `Expense` object. |
| 5 | Adds the new expense to both the `expenses` and `filteredExpenses` lists. |
| 6 | Clears the input fields. |
| 7 | Updates the total amount display. |


<a name="23-editexpense-method"></a>
### 2.3 `editExpense()` Method

This method allows the user to edit an existing expense. It retrieves the selected expense from the `listView`, populates the input fields with its data, removes the old expense from the lists, and awaits user input for updated values.

| Step | Description |
|---|---|
| 1 | Gets the selected expense from the `listView`. |
| 2 | If an expense is selected, populates the text fields with the expense's details. |
| 3 | Removes the selected expense from `expenses` and `filteredExpenses` lists.  This allows the user to effectively edit the existing entry. |
| 4 | If no expense is selected, shows a warning alert. |


<a name="24-deleteexpense-method"></a>
### 2.4 `deleteExpense()` Method

This method deletes a selected expense from the application.

| Step | Description |
|---|---|
| 1 | Gets the selected expense from the `listView`. |
| 2 | If an expense is selected, removes it from both the `expenses` and `filteredExpenses` ObservableLists. |
| 3 | Updates the total amount display. |
| 4 | If no expense is selected, displays a warning alert. |


<a name="25-saveexpenses-method"></a>
### 2.5 `saveExpenses()` Method

This method saves all expenses to persistent storage using the `ExpenseManager` class.

| Step | Description |
|---|---|
| 1 | Calls `ExpenseManager.saveExpenses(expenses)` to save the data. |
| 2 | Shows an information alert confirming successful save. |


<a name="26-filterexpenses-method"></a>
### 2.6 `filterExpenses()` Method

This method filters the displayed expenses based on a search query entered by the user.  It uses a case-insensitive search on the expense description.

| Step | Description |
|---|---|
| 1 | Clears the `filteredExpenses` list. |
| 2 | If the query is empty, adds all expenses back to `filteredExpenses`. |
| 3 | If the query is not empty, iterates through the `expenses` list. |
| 4 | For each expense, converts both the query and the expense description to lowercase for case-insensitive comparison. |
| 5 | If the expense description contains the query, adds the expense to `filteredExpenses`. |
| 6 | If `filteredExpenses` is empty after filtering, displays an alert indicating no results. |


<a name="27-updatetotalamount-method"></a>
### 2.7 `updateTotalAmount()` Method

This method calculates and updates the total amount displayed on the UI.  It utilizes Java Streams for efficient summation.

| Step | Description |
|---|---|
| 1 | Uses a Java Stream to efficiently sum the `amount` property of each `Expense` object in the `expenses` list. |
| 2 | Updates the `totalAmountLabel` with the formatted total amount. |


<a name="28-showalert-method"></a>
### 2.8 `showAlert()` Method

This is a helper method to display alerts to the user.

| Parameter | Description |
|---|---|
| `alertType` | The type of alert (e.g., ERROR, WARNING, INFORMATION). |
| `title` | The title of the alert dialog. |
| `message` | The message to display in the alert dialog. |


<a name="3-external-dependencies"></a>
## 3. External Dependencies

The application depends on the JavaFX library and a custom `ExpenseManager` class (not detailed here).  The `Expense` class (also not detailed here) is assumed to be a simple data structure for holding expense information.
