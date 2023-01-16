package com.example.YouOweMeProject.Model;

import java.util.ArrayList;

public class Expenses {
    ArrayList<Expense> expenses;

    public Expenses() {
    }

    public Expenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }
}
