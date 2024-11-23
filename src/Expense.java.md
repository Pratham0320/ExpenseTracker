# Expense Class Documentation

[TOC]

## 1. Introduction

This document provides internal documentation for the `Expense` class.  The `Expense` class is designed to represent a single expense item, storing its description, amount, and category.


## 2. Class Overview

| Feature          | Description                                                                 |
|-----------------|-----------------------------------------------------------------------------|
| **Name**         | `Expense`                                                                    |
| **Purpose**      | Represents a single expense with its description, amount, and category.     |
| **Data Members** | `description` (String), `amount` (double), `category` (String)              |
| **Methods**      | Constructor, `getDescription()`, `getAmount()`, `getCategory()`, `toString()` |


## 3. Class Members

### 3.1. Data Members

| Data Member    | Type      | Description                                      |
|----------------|-----------|--------------------------------------------------|
| `description`  | `String`  | A textual description of the expense.             |
| `amount`       | `double`  | The monetary amount of the expense.              |
| `category`     | `String`  | The category to which the expense belongs (e.g., "Food", "Rent"). |


### 3.2. Constructor

The constructor initializes a new `Expense` object.

```java
public Expense(String description, double amount, String category) {
    this.description = description;
    this.amount = amount;
    this.category = category;
}
```

This constructor takes three arguments:

*   `description`: A string describing the expense.
*   `amount`: A double representing the expense amount.
*   `category`: A string representing the category of the expense.

The constructor assigns these values to the corresponding instance variables.


### 3.3. Getter Methods

The class includes getter methods to access the private instance variables.  These methods provide controlled access to the expense data.

```java
public String getDescription() {
    return description;
}

public double getAmount() {
    return amount;
}

public String getCategory() {
    return category;
}
```

These methods simply return the value of the respective private member variable.


### 3.4. `toString()` Method

The `toString()` method provides a string representation of the `Expense` object.

```java
public String toString() {
    return String.format("Description: %s, Amount: %.2f, Category: %s", description, amount, category);
}
```

This method uses `String.format()` to create a formatted string that displays the description, amount (formatted to two decimal places), and category of the expense.  This formatted string is particularly useful for debugging and printing expense information.


## 4. Example Usage

```java
Expense expense1 = new Expense("Groceries", 50.50, "Food");
System.out.println(expense1); // Output: Description: Groceries, Amount: 50.50, Category: Food
```

This example shows how to create an `Expense` object and print its string representation using the `toString()` method.
