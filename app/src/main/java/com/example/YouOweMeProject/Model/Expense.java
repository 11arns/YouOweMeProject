package com.example.YouOweMeProject.Model;

public class Expense {
    Float amount;
    String nameOfExpense, chosenName, type;

    public Expense() {
    }

    public Expense(Float amount, String nameOfExpense, String chosenName, String type) {
        this.amount = amount;
        this.nameOfExpense = nameOfExpense;
        this.chosenName = chosenName;
        this.type = type;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameOfExpense() {
        return nameOfExpense;
    }

    public void setNameOfExpense(String nameOfExpense) {
        this.nameOfExpense = nameOfExpense;
    }

    public String getChosenName() {
        return chosenName;
    }

    public void setChosenName(String chosenName) {
        this.chosenName = chosenName;
    }
}
