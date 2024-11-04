package com.example.labactivitieselective1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.Toolbar;


public class AddActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText title_input, author_input, pages_input;
    Button add_button;
    String title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Add book");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Show back arrow
        }

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        pages_input = findViewById(R.id.pages_input);
        add_button = findViewById(R.id.add_button);

        title = title_input.getText().toString();
        author = author_input.getText().toString();
        pages = pages_input.getText().toString();

        add_button.setOnClickListener(v -> {
            title = title_input.getText().toString();
            author = author_input.getText().toString();
            pages = pages_input.getText().toString();
            if (title.isEmpty() || author.isEmpty() || pages.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int pagesInt = Integer.parseInt(pages);
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(AddActivity.this);
                myDatabaseHelper.addBook(title, author, pagesInt);
                Intent i = new Intent(this, BookLibraryApp.class);
                startActivity(i);
                finish();

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter a valid number for pages", Toast.LENGTH_SHORT).show();
            }
        });





    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) { // Check if the back arrow was clicked
            finish(); // Close the activity and go back
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}