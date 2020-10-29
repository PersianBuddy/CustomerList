package com.example.customerlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList <CustomerModel> customers;
    private  RecViewCustomerAdaptor adaptor;
    private RecyclerView rcvCustomersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDummyData();
        rcvCustomersList = findViewById(R.id.rcv_customers_list);

        adaptor = new RecViewCustomerAdaptor(this);
        adaptor.setCustomers(customers);

        rcvCustomersList.setAdapter(adaptor);
        rcvCustomersList.setLayoutManager(new LinearLayoutManager(this));


    }

    private void setDummyData(){
        customers = new ArrayList<>();
        customers.add(new CustomerModel("Shahin", 24));
        customers.add(new CustomerModel("John", 25));
        customers.add(new CustomerModel("Sara", 30));
        customers.add(new CustomerModel("Bill", 77));
        customers.add(new CustomerModel("Joe", 56));
        customers.add(new CustomerModel("Joseph", 24));
        customers.add(new CustomerModel("John", 25));
        customers.add(new CustomerModel("Sara", 30));
        customers.add(new CustomerModel("Bill", 77));
        customers.add(new CustomerModel("Bill", 77));
        customers.add(new CustomerModel("Joe", 56));
        customers.add(new CustomerModel("Joseph", 24));
        customers.add(new CustomerModel("John", 25));
    }
}