package main.models;


public class Income {
    private static int idCounter = 1;  
    private String id;
    private String source;
    private double amount;
    private String date;

    public Income(String source, double amount, String date) {
        this.id = generateId();  
        this.source = source;
        this.amount = amount;
        this.date = date;
    }

    private String generateId() {
        return "INC" + idCounter++;  
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
        return id + " | " + source + " | " + amount + " | " + date;
    }
}
