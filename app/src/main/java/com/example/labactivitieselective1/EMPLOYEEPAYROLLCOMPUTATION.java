package com.example.labactivitieselective1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EMPLOYEEPAYROLLCOMPUTATION extends AppCompatActivity {
    private Spinner employeeID, positionCode, daysWorked;
    private TextView employeeName;
    private double positionValue, daysWorkedValue;
    private RadioGroup civilStatus;
    private RadioButton single, married, widowed;

    private double BasicPay, taxRate, SSSRate, SSSContribution, WithholdingTax, NetPay;
    private Button compute, clear;

    myCustomAdapter myCustomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employeepayrollcomputation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    employeeID = findViewById(R.id.spinnerEmployeeID);
    employeeName = findViewById(R.id.tvName);

    //Data using Hashmap
        HashMap<String, String> employeeData = new HashMap<>();
        employeeData.put("EMP01", "Eduardo Buscato");
        employeeData.put("EMP02", "Krissa Beringuel");
        employeeData.put("EMP03", "April Faustino");
        employeeData.put("EMP04", "John Harvey Hingco");


        // Populate the Spinner with Employee IDs
        List<String> employeeIds = new ArrayList<>(employeeData.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, employeeIds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employeeID.setAdapter(adapter);

        //Retrieves values from spinner and sets the employee name
        employeeID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedId = parent.getItemAtPosition(position).toString();
                String employeeN = employeeData.get(selectedId);
                employeeName.setText(employeeN);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                employeeName.setText("");
            }
        });

    positionCode = findViewById(R.id.spinnerPosition);
        HashMap<String, Double> positionData = new HashMap<>();
        positionData.put("A", 500.0);
        positionData.put("B", 400.0);
        positionData.put("C", 300.0);
    //Populates position code spinner
        List<String> positionCodes = new ArrayList<>(positionData.keySet());
        ArrayAdapter<String> positionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, positionCodes);
        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        positionCode.setAdapter(positionAdapter);

        //Retrieves values from spinner and sets the values for Rate/Day
        positionCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedId = parent.getItemAtPosition(position).toString();
                positionValue = positionData.get(selectedId);  // Get the corresponding value
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                positionValue = 0.0; // Set to null or a default value if desired
            }
        });
        civilStatus = findViewById(R.id.radioGroupTax);
        single = findViewById(R.id.rbSingle);
        married = findViewById(R.id.rbMarried);
        widowed = findViewById(R.id.rbWidowed);
        civilStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                if (selectedRadioButton != null) {
                    taxRate = Double.parseDouble(selectedRadioButton.getTag().toString());
                }
            }
        });
        daysWorked = findViewById(R.id.spinnerDaysWorked);

        // Array of days worked
        String[] daysArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18",
                "19", "20", "21", "22", "23", "24", "25", "26",
                "27", "28", "29", "30"};
        myCustomAdapter customm = new myCustomAdapter(
                this,
                daysArray
        );

        // Set up the adapter for daysWorked spinner
/*        ArrayAdapter<String> daysWorkedAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, daysArray);
        daysWorkedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Change made here*/
        daysWorked.setAdapter(customm);

        // Set an item selected listener
        daysWorked.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                daysWorkedValue = Double.parseDouble(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        compute = findViewById(R.id.btnCompute);
        clear = findViewById(R.id.btnClear);

        compute.setOnClickListener(v -> {
            computePayroll();
        });



    }
    private void computePayroll() {
        // Calculate BasicPay
        BasicPay = daysWorkedValue * positionValue;

        // Determine SSS Rate
        if (BasicPay >= 10000) {
            SSSRate = 0.07;  // 7%
        } else if (BasicPay >= 5000 && BasicPay <= 9999) {
            SSSRate = 0.05;  // 5%
        } else if (BasicPay >= 1000 && BasicPay <= 4999) {
            SSSRate = 0.03;  // 3%
        } else {
            SSSRate = 0.01;  // 1%
        }

        // Calculate SSS Contribution
        SSSContribution = BasicPay * SSSRate;

        // Calculate Withholding Tax
        WithholdingTax = BasicPay * taxRate;

        // Calculate Net Pay
        NetPay = BasicPay - (SSSContribution + WithholdingTax);


        Intent intent = new Intent(this, PAYROLLSUMMARYDETAILS.class);
        intent.putExtra("EmployeeID", employeeID.getSelectedItem().toString());
        intent.putExtra("EmployeeName", employeeName.getText().toString());
        intent.putExtra("PositionCode", positionCode.getSelectedItem().toString());
        // Get the selected RadioButton from the RadioGroup for civil status
        int selectedId = civilStatus.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String civilStatusValue = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "Not specified";
        intent.putExtra("CivilStatus", civilStatusValue);
        intent.putExtra("DaysWorked", daysWorked.getSelectedItem().toString());
        intent.putExtra("BasicPay", BasicPay);
        intent.putExtra("SSSContribution", SSSContribution);
        intent.putExtra("WithholdingTax", WithholdingTax);
        intent.putExtra("NetPay", NetPay);
        startActivity(intent);
    }


}