package com.restaurant.tablesmanager;

public class Person {
    private String id;
    private int index;
    private int age;
    private String eyeColor;
    private String name;
    private String lastName;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private String about;
    private String registered;
    public boolean isEmployee;

    public String getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public int getAge() {
        return age;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return name + " " + lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getAbout() {
        return about;
    }

    public String getRegistered() {
        return registered;
    }

    public boolean getIsEmployee() {
        return isEmployee;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }
}
