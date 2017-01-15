package com.example.sunny.sqlliteexample;

public class Contact {

    // private variables
    int _id;
    String _name;
    String _phone_number;

    // Empty Constructor
    public Contact() {

    }

    // constructor
    public Contact(int id, String name, String _phone_number) {
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
    }

    // constructor
    public Contact(String name, String _phone_number) {
        this._name = name;
        this._phone_number = _phone_number;
    }

    // getting the id
    public int getID() {
        return this._id;
    }

    // setting the id
    public void setID(int id) {
        this._id = id;
    }

    // getting the name
    public String getNAME() {
        return this._name;
    }

    // setting the name
    public void setName(String name) {
        this._name = name;
    }

    // getting the phone number
    public String getPhoneNumber() {
        return this._phone_number;
    }

    // setting the phone number
    public void setPhoneNumber(String phone_number) {
        this._phone_number = phone_number;
    }

}
