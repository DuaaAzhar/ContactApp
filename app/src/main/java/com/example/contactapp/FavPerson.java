package com.example.contactapp;

public class FavPerson {
    String Name,Number, Email,Address, DOB;

    public String getName() {
        return Name;
    }

    public String getNumber() {
        return Number;
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

    public FavPerson(String name, String number, String email, String address, String DOB) {
        this.Name = name;
        Email = email;
        Address = address;
        this.DOB = DOB;
        Number = number;
    }

    @Override
    public String toString() {
        return "FavPerson{" +
                "Name='" + Name + '\'' +
                ", Number='" + Number + '\'' +
                ", Email='" + Email + '\'' +
                ", Address='" + Address + '\'' +
                ", DOB='" + DOB + '\'' +
                '}';
    }
}
