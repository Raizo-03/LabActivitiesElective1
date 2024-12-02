package com.example.labactivitieselective1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
    }

    // This method is triggered when a RadioButton is clicked
    public void changeBackgroundColor(View view) {
        ConstraintLayout mainLayout = findViewById(R.id.main); // Get the ConstraintLayout
        boolean checked = ((RadioButton) view).isChecked();

        if (checked) {
            if (view.getId() == R.id.rbRed) {
                mainLayout.setBackgroundColor(Color.RED);
                Toast.makeText(this, "Color: RED", Toast.LENGTH_SHORT).show();
            } else if (view.getId() == R.id.rbBlue) {
                mainLayout.setBackgroundColor(Color.BLUE);
                Toast.makeText(this, "Color: BLUE", Toast.LENGTH_SHORT).show();
            } else if (view.getId() == R.id.rbYellow) {
                mainLayout.setBackgroundColor(Color.YELLOW);
                Toast.makeText(this, "Color: YELLOW", Toast.LENGTH_SHORT).show();
            } else if (view.getId() == R.id.rbGreen) {
                mainLayout.setBackgroundColor(Color.GREEN);
                Toast.makeText(this, "Color: GREEN", Toast.LENGTH_SHORT).show();
            }
        }
    }


}