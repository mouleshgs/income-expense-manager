package main;

import main.models.Expense;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ExpenseTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Category", "Amount", "Date"};
    private final List<Expense> expenses;

    public ExpenseTableModel(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public int getRowCount() {
        return expenses.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Expense expense = expenses.get(rowIndex);
        switch (columnIndex) {
            case 0: return expense.getId();
            case 1: return expense.getCategory();
            case 2: return expense.getAmount();
            case 3: return expense.getDate();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
