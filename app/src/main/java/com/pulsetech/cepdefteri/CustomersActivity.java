package com.pulsetech.cepdefteri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class CustomersActivity extends AppCompatActivity {

    MaterialButton saveCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customers);


        saveCustomer = findViewById(R.id.materialSaveCustomer);


        saveCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomersActivity.this, AddCustomersActivity.class));

            }
        });
    }
}