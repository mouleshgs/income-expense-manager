package main.models;


public class Account {
    private static int idCounter = 1;  
    private String id;
    private String name;
    private double balance;

    public Account(String name, double balance) {
        this.id = generateId();  
        this.name = name;
        this.balance = balance;
    }

    private String generateId() {
        return "ACC" + idCounter++;  
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + balance;
    }
}
