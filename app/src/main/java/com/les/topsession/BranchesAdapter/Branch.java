package com.les.topsession.BranchesAdapter;

public class Branch {
    String address, place, active, clock;

    public Branch(String address, String place, String active, String clock) {
        this.address = address;
        this.place = place;
        this.active = active;
        this.clock = clock;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }
}
