package com.example.YouOweMeProject.Model;

public class User {
    String email, phone, username;
    Float totalYouOwe, totalOweYou, currentYouOwe, currentOweYou;

    public User() {
    }

    public User(String email, String phone, String username, Float totalYouOwe, Float totalOweYou, Float currentYouOwe, Float currentOweYou) {
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.totalYouOwe = totalYouOwe;
        this.totalOweYou = totalOweYou;
        this.currentYouOwe = currentYouOwe;
        this.currentOweYou = currentOweYou;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Float getTotalYouOwe() {
        return totalYouOwe;
    }

    public void setTotalYouOwe(Float totalYouOwe) {
        this.totalYouOwe = totalYouOwe;
    }

    public Float getTotalOweYou() {
        return totalOweYou;
    }

    public void setTotalOweYou(Float totalOweYou) {
        this.totalOweYou = totalOweYou;
    }

    public Float getCurrentYouOwe() {
        return currentYouOwe;
    }

    public void setCurrentYouOwe(Float currentYouOwe) {
        this.currentYouOwe = currentYouOwe;
    }

    public Float getCurrentOweYou() {
        return currentOweYou;
    }

    public void setCurrentOweYou(Float currentOweYou) {
        this.currentOweYou = currentOweYou;
    }

}
