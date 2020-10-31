package com.example.customerlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;

public class CustomerDbHelper extends SQLiteOpenHelper {
    private Context mContext;

    public CustomerDbHelper(@Nullable Context context) {
        super(context, DbCustomerContract.DATABASE_NAME, null, 1);
        this.mContext = mContext;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DbCustomerContract.TABLE_NAME + "(" + DbCustomerContract.COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                DbCustomerContract.COLUMN_CUSTOMER_NAME + " TEXT, " +DbCustomerContract.COLUMN_CUSTOMER_AGE + " TEXT, " + DbCustomerContract.COLUMN_CUSTOMER_IMAGE_URL +
                " TEXT );";
       try {
            db.execSQL(query);
       }catch (SQLException e){
           Toast.makeText(mContext, "Table didn't created", Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<CustomerModel> getAll(){
        ArrayList<CustomerModel> customers = new ArrayList<>();
        String query = "SELECT * FROM " + DbCustomerContract.TABLE_NAME + ";";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                CustomerModel customer;
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String imageUrl = cursor.getString(3);
                int id = cursor.getInt(0);
                if (!imageUrl.equals("")){
                    customer = new CustomerModel(name, age, imageUrl);
                    customer.setId(id);
                }else {
                    customer = new CustomerModel( name, age);
                    customer.setId(id);
                }
                customers.add(customer);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return customers;
    }

    public boolean addCustomer(CustomerModel customer){
        ContentValues cv = new ContentValues();
        cv.put(DbCustomerContract.COLUMN_CUSTOMER_NAME, customer.getName());
        cv.put(DbCustomerContract.COLUMN_CUSTOMER_AGE, customer.getAge());
        cv.put(DbCustomerContract.COLUMN_CUSTOMER_IMAGE_URL, customer.getImageUrl());

        SQLiteDatabase db = getWritableDatabase();
        if (db.insert(DbCustomerContract.TABLE_NAME, null, cv) == -1){//error occurred
            Toast.makeText(mContext, "Error adding " + customer.getName(), Toast.LENGTH_SHORT).show();
        }

        db.close();
        return true;
    }

    public CustomerModel getCustomer(int customer_id){
        CustomerModel customer;
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + DbCustomerContract.TABLE_NAME +
                " WHERE " + DbCustomerContract.COLUMN_CUSTOMER_ID +
                "="+ customer_id + ";";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String imageUrl = cursor.getString(3);
            customer = new CustomerModel(name, age, imageUrl);
            return customer;
        }

        return null;
    }

    public boolean editCustomer(int customer_id, String newName, int newAge, String newImageUrl){
        CustomerModel customer;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DbCustomerContract.COLUMN_CUSTOMER_NAME, newName);
        cv.put(DbCustomerContract.COLUMN_CUSTOMER_AGE, newAge);
        cv.put(DbCustomerContract.COLUMN_CUSTOMER_IMAGE_URL, newImageUrl);
        int rowUpdated = db.update(DbCustomerContract.TABLE_NAME,cv, DbCustomerContract.COLUMN_CUSTOMER_ID+ "="+ customer_id,null);
        if (rowUpdated ==1){
            return true;
        }
        return false;
    }
}
