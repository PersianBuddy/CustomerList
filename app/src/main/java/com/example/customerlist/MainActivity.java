package com.example.customerlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList <CustomerModel> customers;
    private  RecViewCustomerAdaptor adaptor;
    private RecyclerView rcvCustomersList;
    private FloatingActionButton fbAddCustomer;
    private CustomerDbHelper dbHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new CustomerDbHelper(this);

        customers = dbHelper.getAll();
        rcvCustomersList = findViewById(R.id.rcv_customers_list);

        adaptor = new RecViewCustomerAdaptor(this);
        adaptor.setCustomers(customers);

        rcvCustomersList.setAdapter(adaptor);
        rcvCustomersList.setLayoutManager(new LinearLayoutManager(this));

        //every time user clicks plus button it creates a new dummy data
        fbAddCustomer = findViewById(R.id.fb_add_customer);
        fbAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCustomer.class);
                startActivity(intent);
            }
        });

    }

    private void setDummyData(){
        CustomerModel customer = new CustomerModel("Shahin", 24);
        dbHelper.addCustomer(customer);
    }
}