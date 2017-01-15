package com.example.sunny.sqlliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "contactsManager";

    // contacts table name
    public static final String TABLE_CONTACTS = "contacts";

    // contacts table column name
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // creating tables
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE" + TABLE_CONTACTS + "(" + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + "TEXT," + KEY_PH_NO + "TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // upgrading database

    @Override
    public void onCreate(SQLiteDatabase db) {
        // drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CONTACTS);

        // create table again
        onCreate(db);
    }

    // Adding new Contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getNAME()); // contact name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); // contact phone number
    }

    // Getting Single Contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{
                KEY_ID, KEY_NAME, KEY_PH_NO
        }, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return contact;

    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();

        // select all query
        String selectQuery = "select * from " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                // adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        // return contactlist
        return contactList;
    }


    public int getContactsCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }


    // updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getNAME());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // update the row
        return db.update(TABLE_CONTACTS, values, KEY_ID + "=?", new String[]{String.valueOf(contact.getID())});
    }


    // delete the contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + "=?", new String[]{String.valueOf(contact.getID())});
        db.close();
    }
}
