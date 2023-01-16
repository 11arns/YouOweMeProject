package com.example.YouOweMeProject.Model;

import java.util.ArrayList;

public class Histories {
    ArrayList<History> histories;

    public Histories() {
    }

    public Histories(ArrayList<History> histories) {
        this.histories = histories;
    }

    public ArrayList<History> getHistories() {
        return histories;
    }

    public void setHistories(ArrayList<History> histories) {
        this.histories = histories;
    }
}
