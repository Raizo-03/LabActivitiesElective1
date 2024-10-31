package com.example.labactivitieselective1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PAYROLLSUMMARYDETAILS extends AppCompatActivity {
    private TextView tvID, tvName, tvPosition, tvCivil, tvDays, tvBasicPay, tvSSS, tvTax, tvNetPay;
    private Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payrollsummarydetails);
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

        // Retrieve extras from the intent
        String employeeID = getIntent().getStringExtra("EmployeeID");
        String employeeName = getIntent().getStringExtra("EmployeeName");
        String positionCode = getIntent().getStringExtra("PositionCode");
        String civilStatus = getIntent().getStringExtra("CivilStatus");
        String daysWorked = getIntent().getStringExtra("DaysWorked");
        double basicPay = getIntent().getDoubleExtra("BasicPay", 0.0);
        double sssContribution = getIntent().getDoubleExtra("SSSContribution", 0.0);
        double withholdingTax = getIntent().getDoubleExtra("WithholdingTax", 0.0);
        double netPay = getIntent().getDoubleExtra("NetPay", 0.0);

        // Set the values to the TextViews
        tvID.setText(employeeID);
        tvName.setText(employeeName);
        tvPosition.setText(positionCode);
        tvCivil.setText(civilStatus);
        tvDays.setText(daysWorked);
        tvBasicPay.setText(formatCurrency(basicPay));
        tvSSS.setText(formatCurrency(sssContribution));
        tvTax.setText(formatCurrency(withholdingTax));
        tvNetPay.setText(formatCurrency(netPay));

        // Back button functionality
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }

    // Helper method to format the currency
    private String formatCurrency(double amount) {
        return String.format("Php %, .2f", amount);
    }
}