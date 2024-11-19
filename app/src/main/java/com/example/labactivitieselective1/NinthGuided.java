package com.example.labactivitieselective1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NinthGuided extends AppCompatActivity {
    ListView name;
    ArrayAdapter adapter;
    String[] listOfNames = {"Papsi","Majoy","Pol","Che","Tin","Lou","Renz","Pet","Roven","Chan","Jher"};
    double[] listOfSemGrades = {1.00,1.50,2.00,1.25,3.00,5.00,1.75,2.25,3.00,1.00,2.25};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ninth_guided);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        name = findViewById(R.id.lvNameGE9);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listOfNames);
        name.setAdapter(adapter);

        showSemGrade();

    }

    public void showSemGrade(){

        name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Name: " + name.getItemAtPosition(i)
                        + "\nSem Grade: " + listOfSemGrades[i],Toast.LENGTH_SHORT).show();
            }
        });
    }

}