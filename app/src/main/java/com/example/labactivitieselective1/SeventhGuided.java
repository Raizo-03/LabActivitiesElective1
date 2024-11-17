package com.example.labactivitieselective1;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SeventhGuided extends AppCompatActivity {
    RatingBar ratingBar;
    TextView rate;
    Button click, close;
    AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seventh_guided);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        alertDialogBuilder = new AlertDialog.Builder(this);
        ratingBar = findViewById(R.id.ratingBar);
        rate = findViewById(R.id.tvResultGE7);
        click = findViewById(R.id.btnClickGE7);
        close = findViewById(R.id.btnCloseGE7);
        rate.setTextColor(Color.RED);

        showRating();
        closeApplication();

        handleOnBackPressed();

    }
    public void showRating(){
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if(ratingBar.getRating() >= 3){
                    rate.setTextColor(Color.GREEN);
                    rate.setVisibility(View.VISIBLE);

                }else{
                    rate.setTextColor(Color.RED);
                    rate.setVisibility(View.VISIBLE);
                }
                rate.setText("Rate: " + ratingBar.getRating());
                rate.setVisibility(View.VISIBLE);
            }
        });
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Rate: " + ratingBar.getRating(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void closeApplication() {
        close.setOnClickListener(view -> alertDialogBuilder.setTitle("Warning Message!")
                .setMessage("Do you want to close the Application?")
                .setPositiveButton("Yes", (dialogInterface, i) -> finish())
                .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel())
                .setCancelable(false)
                .show());
    }
    private void handleOnBackPressed() {
        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Handle back button press with a confirmation dialog
                alertDialogBuilder.setTitle("Warning Message!")
                        .setMessage("Do you want to close the Application?")
                        .setPositiveButton("Yes", (dialogInterface, i) -> finish())
                        .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel())
                        .setCancelable(false)
                        .show();
            }
        });
    }
}
