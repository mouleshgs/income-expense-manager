package main;

import main.models.Account;
import main.models.AccountTableModel;
import main.utils.FileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AccountManager extends JPanel {
    private List<Account> accounts;
    private final String FILENAME = "resources/data/accounts.txt";
    private JTable accountTable;
    private AccountTableModel accountTableModel;

    public AccountManager() {
        setLayout(new BorderLayout());
        accounts = loadAccounts();
        accountTableModel = new AccountTableModel(accounts);
        accountTable = new JTable(accountTableModel);

        add(new JScrollPane(accountTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Account");
        JButton deleteButton = new JButton("Delete Account");
        JButton updateButton = new JButton("Update Account");
        JButton calculateButton = new JButton("Calculate Total Balance");
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(calculateButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addAccount());
        deleteButton.addActionListener(e -> deleteAccount());
        updateButton.addActionListener(e -> updateAccount());
        calculateButton.addActionListener(e -> calculateTotalBalance());
    }

    private List<Account> loadAccounts() {
        try {
            return FileManager.loadFromFile(FILENAME);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void saveAccounts() {
        try {
            FileManager.saveToFile(FILENAME, accounts);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to save accounts.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addAccount() {
        JTextField nameField = new JTextField();
        JTextField balanceField = new JTextField();

        Object[] message = {
                "Name:", nameField,
                "Balance:", balanceField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Account", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                double balance = Double.parseDouble(balanceField.getText());

                Account account = new Account(name, balance);
                accounts.add(account);
                saveAccounts();
                accountTableModel.fireTableDataChanged(); 
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid balance. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Failed to add account.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteAccount() {
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow >= 0) {
            accounts.remove(selectedRow);
            saveAccounts();
            accountTableModel.fireTableDataChanged();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an account to delete.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

        private void updateAccount() {
            int selectedRow = accountTable.getSelectedRow();
            if (selectedRow >= 0) {
                Account selectedAccount = accounts.get(selectedRow);

                JTextField nameField = new JTextField(selectedAccount.getName());
                JTextField balanceField = new JTextField(String.valueOf(selectedAccount.getBalance()));

                Object[] message = {
                        "Name:", nameField,
                        "Balance:", balanceField
                };

                int option = JOptionPane.showConfirmDialog(this, message, "Update Account", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        String newName = nameField.getText();
                        double newBalance = Double.parseDouble(balanceField.getText());

                        selectedAccount.setName(newName);
                        selectedAccount.setBalance(newBalance);

                        saveAccounts();
                        accountTableModel.fireTableDataChanged(); 
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid balance. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select an account to update.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }

    private void calculateTotalBalance() {
        double totalBalance = accounts.stream().mapToDouble(Account::getBalance).sum();
        JOptionPane.showMessageDialog(this, "Total Balance: " + totalBalance, "Total Balance", JOptionPane.INFORMATION_MESSAGE);
    }
}

