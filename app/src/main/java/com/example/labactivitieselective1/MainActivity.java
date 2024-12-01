package com.example.labactivitieselective1;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.DeleteRangeGesture;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button, button2, button3, button4, button5, btn1stguided, btn2ndguided, btn3rdguided, btn4thguided, btn5thguided, btn6thguided, btn7thguided, btn8thguided, btn9thguided;
    Button btn10thguided, btn11thguided, btn12thguided, btnSQLiteGuided , btnDragDrop, btnNotificationBroadcast,btnEmailCamera, btnSMS;
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

        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, calculator.class);
            startActivity(i);
        });

        btn1stguided = findViewById(R.id.btn1stguided);
        btn1stguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, FirstGuided.class);
            startActivity(i);
        });
        btn2ndguided = findViewById(R.id.btn2ndguided);
        btn2ndguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, Second_Guided.class);
            startActivity(i);
        });
        btn3rdguided= findViewById(R.id.btn3rdguided);
        btn3rdguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, ThirdGuided.class);
            startActivity(i);
        });

        btn4thguided= findViewById(R.id.btn4thguided);
        btn4thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, FourthGuided.class);
            startActivity(i);
        });

        btn5thguided= findViewById(R.id.btn5thguided);
        btn5thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, FifthGuided.class);
            startActivity(i);
        });

        btn6thguided= findViewById(R.id.btn6thguided);
        btn6thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, SixthGuided.class);
            startActivity(i);
        });
        btn7thguided = findViewById(R.id.btn7thguided);
        btn7thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, SeventhGuided.class);
            startActivity(i);
        });

        btn8thguided= findViewById(R.id.btn8thguided);
        btn8thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, EighthGuided.class);
            startActivity(i);
        });

        btn9thguided = findViewById(R.id.btn9thguided);
        btn9thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, NinthGuided.class);
            startActivity(i);
        });

        btn10thguided= findViewById(R.id.btn10thguided);
        btn10thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, TenthGuided.class);
            startActivity(i);
        });

        btn11thguided = findViewById(R.id.btn11thguided);
        btn11thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, EleventhGuided.class);
            startActivity(i);
        });

        btn12thguided = findViewById(R.id.btn12thguided);
        btn12thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, TwelvethGuided.class);
            startActivity(i);
        });

        btnSQLiteGuided = findViewById(R.id.btnSQLiteGuided);
        btnSQLiteGuided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, SQLiteGuided.class);
            startActivity(i);
        });


        btnDragDrop = findViewById(R.id.btnDragDrop);
        btnDragDrop.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, DragandDrop.class);
            startActivity(i);
        });

        btnNotificationBroadcast = findViewById(R.id.btnNotificationBroadcast);
        btnNotificationBroadcast.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, BroadcastNotification.class);
            startActivity(i);
        });

        btnEmailCamera = findViewById(R.id.btnEmailCamera);
        btnEmailCamera.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, EmailCamera.class);
            startActivity(i);
        });


        btnSMS = findViewById(R.id.btnSMS);
        btnSMS.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, EmailCamera.class);
            startActivity(i);
        });





    }
}