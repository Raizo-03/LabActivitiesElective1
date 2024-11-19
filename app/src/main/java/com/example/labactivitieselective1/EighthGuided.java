package com.example.labactivitieselective1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EighthGuided extends AppCompatActivity {
    Spinner names;
    TextView result;
    ArrayAdapter adapter;
    String[] listOfNames = {"Name Here", "Papsi", "Pol", "Che", "Tin",
            "Renz", "Lou", "Chan", "Ven", "Jher"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eighth_guided);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listOfNames);
        names = findViewById(R.id.spnrNamesGE8);
        result = findViewById(R.id.tvResultGE8);
        showSelectedName();


    }

    public void showSelectedName(){
        names.setAdapter(adapter);
        names.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i > 0){
                    result.setText("Name: " + listOfNames[i]);
                    Toast.makeText(getApplicationContext(),
                            "Name: " + listOfNames[i], Toast.LENGTH_SHORT).show();
                }else{
                    result.setText("Name: ");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}

