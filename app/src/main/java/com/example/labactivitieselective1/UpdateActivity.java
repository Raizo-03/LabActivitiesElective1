package com.example.labactivitieselective1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.Toolbar;


public class UpdateActivity extends AppCompatActivity {
    Toolbar toolbar;

    EditText title_input2, author_input2, pages_input2;
    Button update_button, delete_button;

    String id, title, author, pages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Update book");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Show back arrow
        }

        title_input2 = findViewById(R.id.title_input2);
        author_input2 = findViewById(R.id.author_input2);
        pages_input2 = findViewById(R.id.pages_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getIntentData();


        update_button.setOnClickListener(v->{
            title = title_input2.getText().toString();
            author = author_input2.getText().toString();
            pages = pages_input2.getText().toString();
            if (title.isEmpty() || author.isEmpty() || pages.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                int pagesInt = Integer.parseInt(pages);
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateBook(id, title, author, pages);
                Intent i = new Intent(this, BookLibraryApp.class);
                startActivity(i);
                finish();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter a valid number for pages", Toast.LENGTH_SHORT).show();
            }
        });

        delete_button.setOnClickListener(v->{
            confirmDialog();
        });


    }

    void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages")){

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");


            title_input2.setText(title);
            author_input2.setText(author);
            pages_input2.setText(pages);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();

        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + "?");
        builder.setMessage("Are you sure you want to delete " + title + "?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
            myDB.deleteBook(id);
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();

        });
        builder.show(); // Show the dialog
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