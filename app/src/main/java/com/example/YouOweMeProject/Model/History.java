package com.example.YouOweMeProject.Model;

import java.util.ArrayList;
import java.util.Date;

public class History {
    String description;
    Date date;

    public History() {
    }

    public History(String description, Date date) {
        this.description = description;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
