package com.example.labactivitieselective1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;
import java.util.Locale;

public class MachineProblem4Result extends AppCompatActivity {
    private TextView tvID, tvName, tvPosition, tvCivil, tvDays, tvBasicPay, tvSSS, tvTax, tvNetPay;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_machine_problem4_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvID = findViewById(R.id.tvID);
        tvName = findViewById(R.id.tvName);
        tvPosition = findViewById(R.id.tvPosition);
        tvCivil = findViewById(R.id.tvCivil);
        tvDays = findViewById(R.id.tvDays);
        tvBasicPay = findViewById(R.id.tvBasicPay);
        tvSSS = findViewById(R.id.tvSSS);
        tvTax = findViewById(R.id.tvTax);
        tvNetPay = findViewById(R.id.tvNetPay);


        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
        Intent intent = getIntent();
        String employeeID = intent.getStringExtra("EmployeeID");

        loadEmployeeData(employeeID);


    }

    private void loadEmployeeData(String employeeID) {
        PayrollDatabaseHelper dbHelper = new PayrollDatabaseHelper(this);
        Cursor cursor = dbHelper.getEmployeeDataByID(employeeID);

        if (cursor != null && cursor.moveToFirst()) {
            // Make sure all the column names exist
            tvID.setText(cursor.getString(cursor.getColumnIndexOrThrow("employee_id")));
            tvName.setText(cursor.getString(cursor.getColumnIndexOrThrow("employee_name")));
            tvPosition.setText(cursor.getString(cursor.getColumnIndexOrThrow("position_code")));
            tvCivil.setText(cursor.getString(cursor.getColumnIndexOrThrow("civil_status")));
            tvDays.setText(cursor.getString(cursor.getColumnIndexOrThrow("days_worked")));

            // Format and display currency values
            tvBasicPay.setText(formatCurrency(cursor.getDouble(cursor.getColumnIndexOrThrow("basic_pay"))));
            tvSSS.setText(formatCurrency(cursor.getDouble(cursor.getColumnIndexOrThrow("sss_contribution"))));
            tvTax.setText(formatCurrency(cursor.getDouble(cursor.getColumnIndexOrThrow("withholding_tax"))));
            tvNetPay.setText(formatCurrency(cursor.getDouble(cursor.getColumnIndexOrThrow("net_pay"))));
        } else {
            Toast.makeText(this, "Employee data not found", Toast.LENGTH_SHORT).show();
        }

        if (cursor != null) {
            cursor.close();
        }
    }

    private String formatCurrency(double amount) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "PH"));
        return currencyFormat.format(amount);
    }
}