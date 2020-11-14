package com.les.topsession.CourseAdapter;

public class Course {
    String name, lastName, textBuy, textSell;
    int arUp, arBack;

    public int getArUp() {
        return arUp;
    }

    public void setArUp(int arUp) {
        this.arUp = arUp;
    }

    public int getArBack() {
        return arBack;
    }

    public void setArBack(int arBack) {
        this.arBack = arBack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTextBuy() {
        return textBuy;
    }

    public String setTextBuy(String textBuy) {
        this.textBuy = textBuy;
        return textBuy;
    }

    public String getTextSell() {
        return textSell;
    }

    public String setTextSell(String textSell) {
        this.textSell = textSell;
        return textSell;
    }

}
