package main.models;


import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AccountTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Name", "Balance"};
    private final List<Account> accounts;

    public AccountTableModel(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public int getRowCount() {
        return accounts.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Account account = accounts.get(rowIndex);
        switch (columnIndex) {
            case 0: return account.getId();
            case 1: return account.getName();
            case 2: return account.getBalance();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
