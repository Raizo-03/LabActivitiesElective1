package com.example.labactivitieselective1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BookLibraryApp extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    Toolbar toolbar;

    MyDatabaseHelper myDB;
    ArrayList<String> book_id, book_title, book_author, book_pages;
    CustomAdapter customAdapter;

    ImageView empty_imageview;
    TextView textView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_library_app);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Book Library");
        }

        textView3 = findViewById(R.id.textView3);
        empty_imageview = findViewById(R.id.imageView);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.btnAdd);
        add_button.setOnClickListener(v ->{
            Intent intent = new Intent(BookLibraryApp.this, AddActivity.class);
            startActivity(intent);
        });


        myDB = new MyDatabaseHelper(BookLibraryApp.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();


        storeDataInArrays();


        customAdapter = new CustomAdapter(BookLibraryApp.this,this, book_id, book_title, book_author, book_pages);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(BookLibraryApp.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
        }else {
            while(cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
                empty_imageview.setVisibility(View.GONE);
                textView3.setVisibility(View.GONE);
            }
        }

    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete all" + "?");
        builder.setMessage("Are you sure you want to delete all books?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(BookLibraryApp.this);
            myDB.deleteAllBooks();
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, BookLibraryApp.class);
            startActivity(i);
            finish();
        });

        builder.setNegativeButton("No", (dialog, which) -> {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();

        });
        builder.show(); // Show the dialog
    }
}