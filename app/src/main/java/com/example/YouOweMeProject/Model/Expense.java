package com.example.YouOweMeProject.Model;

public class Expense {
    Float amount;
    String expenseTitle, friend, type;

    public Expense() {
    }

    public Expense(Float amount, String expenseTitle, String friend, String type) {
        this.amount = amount;
        this.expenseTitle = expenseTitle;
        this.friend = friend;
        this.type = type;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getExpenseTitle() {
        return expenseTitle;
    }

    public void setExpenseTitle(String expenseTitle) {
        this.expenseTitle = expenseTitle;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
