package main;

import main.models.Expense;
import main.utils.FileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager extends JPanel {
    private List<Expense> expenses;
    private final String FILENAME = "resources/data/expenses.txt";
    private JTable expenseTable;
    private ExpenseTableModel expenseTableModel;

    public ExpenseManager() {
        setLayout(new BorderLayout());
        expenses = loadExpenses();
        expenseTableModel = new ExpenseTableModel(expenses);
        expenseTable = new JTable(expenseTableModel);

        add(new JScrollPane(expenseTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Expense");
        JButton deleteButton = new JButton("Delete Expense");
        JButton updateButton = new JButton("Update Expense");
        JButton calculateButton = new JButton("Calculate Total Expense");
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(calculateButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addExpense());
        deleteButton.addActionListener(e -> deleteExpense());
        updateButton.addActionListener(e -> updateExpense());
        calculateButton.addActionListener(e -> calculateTotalExpense());


    }

    private List<Expense> loadExpenses() {
        try {
            return FileManager.loadFromFile(FILENAME);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void saveExpenses() {
        try {
            FileManager.saveToFile(FILENAME, expenses);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to save expenses.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addExpense() {
        // Input dialog for expense details
        JTextField categoryField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField dateField = new JTextField();

        Object[] message = {
                "Category:", categoryField,
                "Amount:", amountField,
                "Date (YYYY-MM-DD):", dateField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Expense", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String category = categoryField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String date = dateField.getText();

                Expense expense = new Expense(category, amount, date);
                expenses.add(expense);
                saveExpenses();
                expenseTableModel.fireTableDataChanged(); 
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Failed to add expense.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteExpense() {
        int selectedRow = expenseTable.getSelectedRow();
        if (selectedRow >= 0) {
            expenses.remove(selectedRow);
            saveExpenses();
            expenseTableModel.fireTableDataChanged(); 
        } else {
            JOptionPane.showMessageDialog(this, "Please select an expense to delete.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateExpense() {
        int selectedRow = expenseTable.getSelectedRow();
        if (selectedRow >= 0) {
            Expense selectedExpense = expenses.get(selectedRow);

            JTextField amountField = new JTextField(String.valueOf(selectedExpense.getAmount()));
            JTextField categoryField = new JTextField(selectedExpense.getCategory());
            JTextField dateField = new JTextField(selectedExpense.getDate());

            Object[] message = {
                    "Amount:", amountField,
                    "Category:", categoryField,
                    "Date (YYYY-MM-DD):", dateField
            };

            int option = JOptionPane.showConfirmDialog(this, message, "Update Expense", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    double newAmount = Double.parseDouble(amountField.getText());
                    String newSource = categoryField.getText();
                    String newDate = dateField.getText();

                    selectedExpense.setAmount(newAmount);
                    selectedExpense.setCategory(newSource);
                    selectedExpense.setDate(newDate);

                    saveExpenses();
                    expenseTableModel.fireTableDataChanged(); 
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an expense to update.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void calculateTotalExpense() {
        double totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();
        JOptionPane.showMessageDialog(this, "Total Expense: " + totalExpense, "Total Expense", JOptionPane.INFORMATION_MESSAGE);
    }

}
