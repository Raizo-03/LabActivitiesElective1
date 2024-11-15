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

public class FourthGuided extends AppCompatActivity {
    EditText username, password;
    TextView result;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fourth_guided);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        result = findViewById(R.id.tvMessage);
        login = findViewById(R.id.btnLogin);
        showResult();


    }
    public void showResult(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("papsi") &&
                        password.getText().toString().equals("android")){
                    result.setText("Welcome " + username.getText().toString() +"!");
                    // this will set the result text color into GREEN
                    result.setTextColor(Color.GREEN);
                    clearEntry();

                }else{
                    result.setText("Username and Password does not exist!");
                    // this will set the result text color into RED
                    result.setTextColor(Color.RED);
                    clearEntry();

                }
            }
        });
    }

    public void clearEntry(){
        result.setVisibility(View.VISIBLE);
        username.setText("");
        password.setText("");
        username.requestFocus();
    }


}