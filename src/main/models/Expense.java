package main.models;


public class Expense {
    private static int idCounter = 1;  
    private String id;
    private String category;
    private double amount;
    private String date;

    public Expense(String category, double amount, String date) {
        this.id = generateId();  
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    private String generateId() {
        return "EXP" + idCounter++;  
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return id + " | " + category + " | " + amount + " | " + date;
    }
}
