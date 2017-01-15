package com.example.sunny.sqlliteexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.*;
import android.util.Log;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

          /*
            // Crud operations performed
         */

        // Inserting Contacts
        Log.d("Insert:", "Inserting..");
        db.addContact(new Contact("sunny", "9871727979"));
        db.addContact(new Contact("sahil", "9871727979"));


        // reading all the contacts
        Log.d("Reading:", "Reading all Contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "ID:" + cn.getID() + ",Name:" + cn.getNAME() + ",phone:" + cn.getPhoneNumber();
            Log.d("Name:", log);
        }
    }
}
