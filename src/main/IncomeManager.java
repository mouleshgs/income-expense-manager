package main;

import main.models.Income;
import main.utils.FileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class IncomeManager extends JPanel {
    private List<Income> incomes;
    private final String FILENAME = "resources/data/incomes.txt";
    private JTable incomeTable;
    private IncomeTableModel incomeTableModel;

    public IncomeManager() {
        setLayout(new BorderLayout());
        incomes = loadIncomes();
        incomeTableModel = new IncomeTableModel(incomes);
        incomeTable = new JTable(incomeTableModel);

        add(new JScrollPane(incomeTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Income");
        JButton deleteButton = new JButton("Delete Income");
        JButton updateButton = new JButton("Update Income");
        JButton calculateButton = new JButton("Calculate Total Income");
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(calculateButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addIncome());
        deleteButton.addActionListener(e -> deleteIncome());
        updateButton.addActionListener(e -> updateIncome());
        calculateButton.addActionListener(e -> calculateTotalIncome());

    }

    private List<Income> loadIncomes() {
        try {
            return FileManager.loadFromFile(FILENAME);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void saveIncomes() {
        try {
            FileManager.saveToFile(FILENAME, incomes);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to save incomes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addIncome() {
        JTextField sourceField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField dateField = new JTextField();

        Object[] message = {
                "Source:", sourceField,
                "Amount:", amountField,
                "Date (YYYY-MM-DD):", dateField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Income", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String source = sourceField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String date = dateField.getText();

                Income income = new Income(source, amount, date);
                incomes.add(income);
                saveIncomes();
                incomeTableModel.fireTableDataChanged(); 
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Failed to add income.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateIncome() {
        int selectedRow = incomeTable.getSelectedRow();
        if (selectedRow >= 0) {
            Income selectedIncome = incomes.get(selectedRow);

            JTextField amountField = new JTextField(String.valueOf(selectedIncome.getAmount()));
            JTextField sourceField = new JTextField(selectedIncome.getSource());
            JTextField dateField = new JTextField(selectedIncome.getDate());

            Object[] message = {
                    "Amount:", amountField,
                    "Source:", sourceField,
                    "Date (YYYY-MM-DD):", dateField
            };

            int option = JOptionPane.showConfirmDialog(this, message, "Update Income", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    double newAmount = Double.parseDouble(amountField.getText());
                    String newSource = sourceField.getText();
                    String newDate = dateField.getText();

                    selectedIncome.setAmount(newAmount);
                    selectedIncome.setSource(newSource);
                    selectedIncome.setDate(newDate);

                    saveIncomes();
                    incomeTableModel.fireTableDataChanged(); 
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an income to update.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteIncome() {
        int selectedRow = incomeTable.getSelectedRow();
        if (selectedRow >= 0) {
            incomes.remove(selectedRow);
            saveIncomes();
            incomeTableModel.fireTableDataChanged(); 
        } else {
            JOptionPane.showMessageDialog(this, "Please select an income to delete.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void calculateTotalIncome() {
        double totalIncome = incomes.stream().mapToDouble(Income::getAmount).sum();
        JOptionPane.showMessageDialog(this, "Total Income: " + totalIncome, "Total Income", JOptionPane.INFORMATION_MESSAGE);
    }
}
