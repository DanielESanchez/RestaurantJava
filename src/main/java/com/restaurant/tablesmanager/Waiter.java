package com.restaurant.tablesmanager;

public class Waiter extends Employee{
    private String job;
    private String  hireDate;
    private boolean isWorking;
    private float salary;

    public Waiter(){
        super();
    }
    public String getJob() {
        return job;
    }

    public String getHireDate() {
        return hireDate;
    }

    public boolean getIsWorking() {
        return isWorking;
    }

    public float getSalary() {
        return salary;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }
}
