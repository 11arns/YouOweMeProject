package com.example.YouOweMeProject.FriendsList;

public class friendmodel {
    String expensename;
    String expensestatus;
    String expenseamount;

    public friendmodel(String expensename, String expensestatus, String expenseamount) {
        this.expensename = expensename;
        this.expensestatus = expensestatus;
        this.expenseamount = expenseamount;
    }

    public String getExpensename() {
        return expensename;
    }

    public String getExpensestatus() {
        return expensestatus;
    }

    public String getExpenseamount() {
        return expenseamount;
    }
}
