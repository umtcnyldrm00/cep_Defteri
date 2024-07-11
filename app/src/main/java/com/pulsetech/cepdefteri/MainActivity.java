package com.pulsetech.cepdefteri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    CardView cardViewCustomers, cardViewSuppliers, cardViewProducts, cardViewSales, cardViewBuying,
            cardViewCosts, cardViewCompute, cardViewStocks, cardViewReports;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        cardViewCustomers = findViewById(R.id.viewCustomers);
        cardViewSuppliers = findViewById(R.id.viewSuppliers);
        cardViewProducts = findViewById(R.id.viewProducts);
        cardViewSales = findViewById(R.id.viewSales);
        cardViewBuying = findViewById(R.id.viewBuying);
        cardViewCosts = findViewById(R.id.viewCosts);
        cardViewCompute = findViewById(R.id.viewCompute);
        cardViewStocks = findViewById(R.id.viewStocks);
        cardViewReports = findViewById(R.id.viewReports);




        cardViewCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomersActivity.class));
            }
        });


    }
}