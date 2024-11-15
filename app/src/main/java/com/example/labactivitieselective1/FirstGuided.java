package com.example.labactivitieselective1;

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

public class FirstGuided extends AppCompatActivity {
    EditText name, age;
    TextView result, ageResult;
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first_guided);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.etFullName);
        age = findViewById(R.id.etAge);
        result = findViewById(R.id.tvName);
        ageResult = findViewById(R.id.tvAge);
        click = findViewById(R.id.btnClick);
        showResult();

    }
    public void showResult(){
        // using .setOnClickListener for button click
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this will set the text value of the result
                result.setText("Name: " + name.getText().toString());
                ageResult.setText("Age: " + age.getText().toString());
                // this will clear the text value of name and age
                name.setText("");
                age.setText("");
                // this will set the cursor to name
                name.requestFocus();
            }
        });
    }


    public void showResultAnotherWay(View view){
        result.setText("Name: " + name.getText().toString());
       ageResult.setText("Age: " + age.getText().toString());
        name.setText("");
        age.setText("");
        name.requestFocus();
    }



}