package com.example.labactivitieselective1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FifthGuided extends AppCompatActivity {
    RadioButton red, blue, yellow, green;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fifth_guided);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        red = findViewById(R.id.rbRed);
        blue = findViewById(R.id.rbBlue);
        yellow = findViewById(R.id.rbYellow);
        green = findViewById(R.id.rbGreen);
        showSelectedColor();
    }

    public void showSelectedColor(){
        if(red.isChecked()){
            Toast.makeText(getApplicationContext(),"Color: RED", Toast.LENGTH_SHORT).show();
        }
        if(blue.isChecked()){
            Toast.makeText(getApplicationContext(),"Color: BLUE", Toast.LENGTH_SHORT).show();
        }
        if(yellow.isChecked()){
            Toast.makeText(getApplicationContext(),"Color: YELLOW", Toast.LENGTH_SHORT).show();
        }
        if(green.isChecked()){
            Toast.makeText(getApplicationContext(),"Color: GREEN", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeBackgroundColor(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        if (checked) {
            if (view.getId() == R.id.rbRed) {
                getWindow().getDecorView().setBackgroundColor(Color.RED);
            } else if (view.getId() == R.id.rbBlue) {
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            } else if (view.getId() == R.id.rbYellow) {
                getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            } else if (view.getId() == R.id.rbGreen) {
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            }
            showSelectedColor();
        }
    }


}
