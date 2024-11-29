package main;

import main.models.Income;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class IncomeTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Source", "Amount", "Date"};
    private final List<Income> incomes;

    public IncomeTableModel(List<Income> incomes) {
        this.incomes = incomes;
    }

    @Override
    public int getRowCount() {
        return incomes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Income income = incomes.get(rowIndex);
        switch (columnIndex) {
            case 0: return income.getId();
            case 1: return income.getSource();
            case 2: return income.getAmount();
            case 3: return income.getDate();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
