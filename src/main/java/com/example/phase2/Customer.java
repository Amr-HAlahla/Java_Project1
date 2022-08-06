package com.example.phase2;

import java.util.ArrayList;
public class Customer implements Comparable<Customer> {


    protected String id;
    protected String name;
    protected String address;
    protected String plan;
    protected String mobile;

    protected ArrayList<String> requests;
    protected ArrayList<String> rented;

    public Customer() {

        this.name = "";
        this.address = "";
        this.plan = "";
        this.id ="";
        this.mobile="";
        requests = new ArrayList<String>();
        rented = new ArrayList<String>();

    }

    public Customer(String id, String name, String address, String plan, String mobile) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.plan = plan;
        this.mobile =mobile;
        requests = new ArrayList<String>();
        rented = new ArrayList<String>();

    }

    public Customer(String name) {

        this.name = name;
        requests = new ArrayList<String>();
        rented = new ArrayList<String>();
    }

    public void setId(String id) {
            this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public String getPlan() {
        return plan;
    }

    public String getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", plan='" + plan + '\'' +
                ", mobile='" + mobile + '\'' +
                ", requests=" + requests +
                ", rented=" + rented +
                '}';
    }

    @Override
    public int compareTo(Customer o) {
        return this.getId().compareTo(o.getId());
    }
}
