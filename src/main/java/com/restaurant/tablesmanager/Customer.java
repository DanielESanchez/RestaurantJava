package com.restaurant.tablesmanager;

public class Customer extends Person{
    private String taxId;
    private boolean isSenior;

    public Customer(int age){
        super();
        this.setAge(age);
        this.isSenior = (this.getAge() >= StaticVariables.SENIOR_AGE) ? true: false;
    }


    public boolean getIsSenior(){
        return this.isSenior;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }
}
