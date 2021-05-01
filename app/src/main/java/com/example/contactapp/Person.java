package com.example.contactapp;

public class Person {
    public Person(String name, String number, String email, String address, String DOB) {
        this.name = name;
        Email = email;
        Address = address;
        this.DOB = DOB;
        Number = number;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return Email;
    }

    public String getAddress() {
        return Address;
    }

    public String getDOB() {
        return DOB;
    }

    public String getNumber() {
        return Number;
    }

    String name, Email,Address, DOB,Number;


}
