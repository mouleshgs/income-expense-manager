package main;

import javax.swing.*;

public class MainApplication {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Income, Expense & Account Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Income Manager", new IncomeManager());
        tabbedPane.addTab("Expense Manager", new ExpenseManager());
        tabbedPane.addTab("Account Manager", new AccountManager());

        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}
