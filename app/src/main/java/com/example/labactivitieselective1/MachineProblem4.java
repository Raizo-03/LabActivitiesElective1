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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MachineProblem4 extends AppCompatActivity {
    private Spinner employeeID, positionCode, daysWorked;
    private TextView employeeName;
    private double positionValue, daysWorkedValue;
    private RadioGroup civilStatus;
    private RadioButton single, married, widowed;

    private double BasicPay, taxRate, SSSRate, SSSContribution, WithholdingTax, NetPay;
    private Button compute, clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_machine_problem4);
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
        employeeData.put("EMP05", "Asilito Caasi");

        List<String> employeeIds = new ArrayList<>(employeeData.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, employeeIds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employeeID.setAdapter(adapter);

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
        List<String> positionCodes = new ArrayList<>(positionData.keySet());
        ArrayAdapter<String> positionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, positionCodes);
        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        positionCode.setAdapter(positionAdapter);

        positionCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedId = parent.getItemAtPosition(position).toString();
                positionValue = positionData.get(selectedId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                positionValue = 0.0;
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

        String[] daysArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18",
                "19", "20", "21", "22", "23", "24", "25", "26",
                "27", "28", "29", "30"};
        ArrayAdapter<String> daysWorkedAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, daysArray);
        daysWorkedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Change made here*/
        daysWorked.setAdapter(daysWorkedAdapter);


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
        clear.setOnClickListener(v->{
            employeeID.setSelection(0);

            employeeName.setText("");

            positionCode.setSelection(0);

            daysWorked.setSelection(0);

            civilStatus.clearCheck();

            BasicPay = 0;
            SSSContribution = 0;
            WithholdingTax = 0;
            NetPay = 0;

            Toast.makeText(this, "Fields cleared", Toast.LENGTH_SHORT).show();
        });

        compute.setOnClickListener(v -> {
            computePayroll();
        });



    }
    private void computePayroll() {
        // Calculate BasicPay
        BasicPay = daysWorkedValue * positionValue;

        if (BasicPay >= 10000) {
            SSSRate = 0.07;
        } else if (BasicPay >= 5000 && BasicPay <= 9999) {
            SSSRate = 0.05;
        } else if (BasicPay >= 1000 && BasicPay <= 4999) {
            SSSRate = 0.03;
        } else {
            SSSRate = 0.01;
        }

        SSSContribution = BasicPay * SSSRate;

        WithholdingTax = BasicPay * taxRate;

        NetPay = BasicPay - (SSSContribution + WithholdingTax);

        String EmployeeID = employeeID.getSelectedItem().toString();
        String EmployeeName = employeeName.getText().toString();
        String PositionCode = positionCode.getSelectedItem().toString();
        int selectedId = civilStatus.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String civilStatusValue = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "Not specified";
        String daysW = daysWorked.getSelectedItem().toString();

        PayrollDatabaseHelper dbHelper = new PayrollDatabaseHelper(this);
        dbHelper.insertPayrollData(
                EmployeeID,
                EmployeeName,
                PositionCode,
                civilStatusValue,
                daysW,
                BasicPay,
                SSSContribution,
                WithholdingTax,
                NetPay
        );
        Intent intent = new Intent(this, MachineProblem4Result.class);
        intent.putExtra("EmployeeID", employeeID.getSelectedItem().toString());
        startActivity(intent);
    }
}