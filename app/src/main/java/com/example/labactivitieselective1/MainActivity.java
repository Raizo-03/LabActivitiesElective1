package com.example.labactivitieselective1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button, button2, button3, button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity.this, CCJitters.class);
            startActivity(i);

        });
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity.this, EMPLOYEEPAYROLLCOMPUTATION.class);
            startActivity(i);

        });
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity.this, BookLibraryApp.class);
            startActivity(i);

        });
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity.this, MachineProblem4.class);
            startActivity(i);

        });

    }
}