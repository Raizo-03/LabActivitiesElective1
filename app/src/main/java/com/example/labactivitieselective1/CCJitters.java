package com.example.labactivitieselective1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.content.res.ColorStateList;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CCJitters extends AppCompatActivity {

    public ArrayList<Frappuccino> fraplist = new ArrayList<>();
    TextView title, fraptitle, disctitle;
    TextView subtotal, discount, net;
    TextView subtotalAmount, discountAmount, netAmount;
    LinearLayout frapCheckboxContainer;
    Button compute, clear;

    RadioButton disc5, disc10, disc15, disc0;

    double currentSubtotal = 0.0;
    double selectedDiscount = 0.0;

    ArrayList<Frappuccino> selectedFrappuccinos = new ArrayList<>();

    //Machine problem 3
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ccjitters);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        subtotal = findViewById(R.id.subtotal);
        discount = findViewById(R.id.discount);
        net = findViewById(R.id.net);

        title = findViewById(R.id.title);
        fraptitle = findViewById(R.id.frapTitle);
        disctitle = findViewById(R.id.discTitle);
        frapCheckboxContainer = findViewById(R.id.frapCheckboxContainer);
        compute = findViewById(R.id.btnCompute);
        clear = findViewById(R.id.btnClear);

        disc5 = findViewById(R.id.disc5);
        disc10 = findViewById(R.id.disc10);
        disc15 = findViewById(R.id.disc15);
        disc0 = findViewById(R.id.disc0);



        subtotalAmount = findViewById(R.id.subtotalAmount);
        discountAmount = findViewById(R.id.discAmount);
        netAmount = findViewById(R.id.netAmount);

        fraplist.add(new Frappuccino("Caffe Vanilla Frappuccino", 150.0));
        fraplist.add(new Frappuccino("Green Tea Frappuccino", 190.0));
        fraplist.add(new Frappuccino("S'mores Frappuccino", 199.0));
        fraplist.add(new Frappuccino("Mocha Frappuccino", 130.0));

        LayoutInflater inflater = LayoutInflater.from(this);

        for (Frappuccino frap : fraplist) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(frap.getName());
            checkBox.setChecked(false);
            checkBox.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.black)));
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {

                        Toast.makeText(CCJitters.this,
                                "You selected " + frap.getName() + " - Price: " + frap.getPrice(),
                                Toast.LENGTH_SHORT).show();

                        selectedFrappuccinos.add(frap);

                    }else{
                        selectedFrappuccinos.remove(frap);
                    }
                }
            });

            frapCheckboxContainer.addView(checkBox);
        }

            disc5.setOnClickListener(v ->{
                selectedDiscount = 5.0;
                showToast();
        });

        disc10.setOnClickListener(v ->{
            selectedDiscount = 10.0;
            showToast();
        });
        disc15.setOnClickListener(v ->{
            selectedDiscount = 15.0;
            showToast();
        });
        disc0.setOnClickListener(v ->{
            selectedDiscount = 0.0;
            showToast();
        });


        showToast();
        compute.setOnClickListener(v ->{
            if(selectedFrappuccinos.isEmpty()){
                // Reset all text views
                subtotalAmount.setText("0.00");
                discountAmount.setText("0.00");
                netAmount.setText("0.00");
            }else{
                calculate();
            }
        });
        clear.setOnClickListener(v -> clearAll());
    }



    public void showToast(){
        if(disc5.isChecked()){
            Toast.makeText(this, "You selected 5% discount", Toast.LENGTH_SHORT).show();
        }else if(disc10.isChecked()){
            Toast.makeText(this, "You selected 10% discount", Toast.LENGTH_SHORT).show();
        }else if(disc15.isChecked()){
            Toast.makeText(this, "You selected 15% discount", Toast.LENGTH_SHORT).show();
        }else if(disc0.isChecked()){
            Toast.makeText(this, "You selected 0% discount", Toast.LENGTH_SHORT).show();
        }else{
        }
    }

    private void calculate() {
        currentSubtotal = 0.0;
        for (Frappuccino frap : selectedFrappuccinos) {
            currentSubtotal += frap.getPrice();
        }

        subtotalAmount.setText(String.format("%.2f", currentSubtotal));

        double discountValue = currentSubtotal * (selectedDiscount / 100);
        discountAmount.setText(String.format("%.2f", discountValue));

        double netValue = currentSubtotal - discountValue;
        netAmount.setText(String.format("%.2f", netValue));
    }
    private void clearAll() {
        for (int i = 0; i < frapCheckboxContainer.getChildCount(); i++) {
            CheckBox checkBox = (CheckBox) frapCheckboxContainer.getChildAt(i);
            checkBox.setChecked(false);
        }

        selectedFrappuccinos.clear();

        subtotalAmount.setText("");
        discountAmount.setText("");
        netAmount.setText("");
        disc5.setChecked(false);
        disc10.setChecked(false);
        disc15.setChecked(false);
        disc0.setChecked(false);

    }

}