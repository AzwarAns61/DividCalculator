package com.example.dividend_calculator_application;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText etInvestment, etRate, etMonths;
    private TextView tvMonthly, tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etInvestment = findViewById(R.id.etInvestment);
        etRate = findViewById(R.id.etRate);
        etMonths = findViewById(R.id.etMonths);
        tvMonthly = findViewById(R.id.tvMonthly);
        tvTotal = findViewById(R.id.tvTotal);
        Button btnCalculate = findViewById(R.id.btnCalculate);

        // Calculate button click listener
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDividend();
            }
        });
    }

    private void calculateDividend() {
        try {
            // Get input values
            double investment = Double.parseDouble(etInvestment.getText().toString());
            double rate = Double.parseDouble(etRate.getText().toString());
            int months = Integer.parseInt(etMonths.getText().toString());

            // Validate months
            if(months < 1 || months > 12) {
                Toast.makeText(this, "Months must be between 1-12", Toast.LENGTH_SHORT).show();
                return;
            }

            // Calculate dividends
            double monthlyDividend = (rate / 100 / 12) * investment;
            double totalDividend = monthlyDividend * months;

            // Format results as currency
            DecimalFormat df = new DecimalFormat("RM #,##0.00");
            tvMonthly.setText(df.format(monthlyDividend));
            tvTotal.setText(df.format(totalDividend));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
        }
    }

    // Menu creation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    // Menu item selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}