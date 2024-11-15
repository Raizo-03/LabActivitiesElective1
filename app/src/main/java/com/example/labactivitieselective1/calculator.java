package com.example.labactivitieselective1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class calculator extends AppCompatActivity {
    TextView title, result;
    EditText firstNumber, secondNumber;
    Button add, sub, mul, div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        title = findViewById(R.id.textView);
        result = findViewById(R.id.tvResult);
        firstNumber = findViewById(R.id.etFirstNumber);
        secondNumber = findViewById(R.id.etSecondNumber);

        add = findViewById(R.id.btnAdd);
        sub = findViewById(R.id.btnDiff);
        mul = findViewById(R.id.btnProd);
        div = findViewById(R.id.btnQuo);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!firstNumber.getText().toString().isEmpty() && !secondNumber.getText().toString().isEmpty()) {
                    double num1 = Double.parseDouble(firstNumber.getText().toString());
                    double num2 = Double.parseDouble(secondNumber.getText().toString());

                    double calculationResult = calculate(num1, num2, Operations.Add);
                    result.setText("Total SUM is: " + calculationResult);
                    setColorResult(calculationResult);

                } else {
                    result.setText("Please enter both numbers!");
                }


            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstNumber.getText().toString().isEmpty() && !secondNumber.getText().toString().isEmpty()) {
                    double num1 = Double.parseDouble(firstNumber.getText().toString());
                    double num2 = Double.parseDouble(secondNumber.getText().toString());

                    double calculationResult = calculate(num1, num2, Operations.Sub);
                    result.setText("Total DIFF is: " + calculationResult);
                    setColorResult(calculationResult);

                } else {
                    result.setText("Please enter both numbers!");
                }
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstNumber.getText().toString().isEmpty() && !secondNumber.getText().toString().isEmpty()) {
                    double num1 = Double.parseDouble(firstNumber.getText().toString());
                    double num2 = Double.parseDouble(secondNumber.getText().toString());

                    double calculationResult = calculate(num1, num2, Operations.Mul);
                    result.setText("Total PROD is: " + calculationResult);
                    setColorResult(calculationResult);

                } else {
                    result.setText("Please enter both numbers!");
                }

            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstNumber.getText().toString().isEmpty() && !secondNumber.getText().toString().isEmpty()) {
                    double num1 = Double.parseDouble(firstNumber.getText().toString());
                    double num2 = Double.parseDouble(secondNumber.getText().toString());

                    double calculationResult = calculate(num1, num2, Operations.Div);
                    result.setText("Total QUO is: " + calculationResult);
                    setColorResult(calculationResult);

                } else {
                    result.setText("Please enter both numbers!");
                }
            }
        });




    }

    public double calculate (double num1, double num2, Operations operation){
        double result = 0.0;
        switch (operation) {
            case Add:
                result = num1 + num2;
                break;
            case Sub:
                result = num1 - num2;
                break;
            case Mul:
                result = num1 * num2;
                break;
            case Div:
                result = num1 / num2;
                break;
        }
        return result;

    }

    public void setColorResult(double val){
        if(val % 2 == 1){
            result.setTextColor(Color.RED);
        }else {
            result.setTextColor(Color.BLUE);
        }
    }
    public enum Operations {
        Add, Sub, Mul, Div
    }
}