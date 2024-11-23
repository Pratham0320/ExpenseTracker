# ExpenseManager Class Documentation

[Linked Table of Contents](#table-of-contents)

## Table of Contents

<a name="table-of-contents"></a>

* [1. Overview](#overview)
* [2. Class Variables](#class-variables)
* [3. `saveExpenses` Method](#saveexpenses-method)
* [4. `loadExpenses` Method](#loadexpenses-method)
* [5. Expense Class (Implicit)](#expense-class-implicit)


## 1. Overview

The `ExpenseManager` class provides functionality for saving and loading expense data to and from a text file named "expenses.txt".  Each line in the file represents a single expense, with data fields separated by commas.  The class utilizes `BufferedWriter` and `BufferedReader` for efficient file I/O operations.  Error handling is implemented using `try-catch` blocks to manage potential `IOExceptions`.


## 2. Class Variables

| Variable Name    | Data Type     | Description                                                              |
|-----------------|-----------------|--------------------------------------------------------------------------|
| `FILE_NAME`     | `String`       | Constant string specifying the name of the expense data file ("expenses.txt"). |


## 3. `saveExpenses` Method

```java
public static void saveExpenses(List<Expense> expenses) 
```

This method writes the given list of `Expense` objects to the "expenses.txt" file.

**Algorithm:**

1. A `BufferedWriter` is created to write to the specified file.  The `try-with-resources` statement ensures the writer is automatically closed, even if exceptions occur.
2. The method iterates through each `Expense` object in the input list.
3. For each expense, the description, amount, and category are written to the file, separated by commas.
4. A new line character is added after each expense to separate entries.
5. If an `IOException` occurs during file writing, the stack trace is printed to the console.


## 4. `loadExpenses` Method

```java
public static List<Expense> loadExpenses()
```

This method reads expense data from the "expenses.txt" file and returns it as a `List<Expense>`.

**Algorithm:**

1. An empty `ArrayList` to store `Expense` objects is created.
2. A `BufferedReader` is created to read from the specified file.  The `try-with-resources` statement ensures the reader is automatically closed.
3. The method reads the file line by line using `reader.readLine()`.
4. Each line is split into parts using the comma (`,`) as a delimiter.
5. **Data Validation:** It checks if the line contains exactly three parts (description, amount, category). If not, the line is skipped.
6. If the line is valid:
    * The description (String) is extracted from `parts[0]`.
    * The amount (double) is parsed from `parts[1]` using `Double.parseDouble()`.
    * The category (String) is extracted from `parts[2]`.
    * A new `Expense` object is created using the extracted data.
    * The `Expense` object is added to the `expenses` list.
7. After processing all lines, the method returns the `expenses` list.
8. If an `IOException` occurs during file reading, the stack trace is printed to the console.


## 5. Expense Class (Implicit)

Although not explicitly defined in the provided code snippet, the existence of the `Expense` class is implied by its usage.  It is assumed to have at least the following methods:

| Method Name       | Return Type | Description                                     |
|--------------------|-------------|-------------------------------------------------|
| `getDescription()` | `String`    | Returns the description of the expense.         |
| `getAmount()`      | `double`    | Returns the amount of the expense.              |
| `getCategory()`    | `String`    | Returns the category of the expense.            |
| `Expense(description, amount, category)` | `Expense` | Constructor to create a new Expense object.  |


This documentation assumes a constructor exists for the `Expense` class that accepts a description (String), amount (double), and category (String) as parameters.  The exact implementation details of the `Expense` class are not provided in this code snippet, but the above details are inferred from usage.
