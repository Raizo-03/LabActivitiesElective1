package com.example.labactivitieselective1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SQLiteGuided extends AppCompatActivity {

    EditText studentId, studentName, studentSemGrade;
    Button addRecord, deleteRecord, editRecord, viewRecord, viewAllRecord;
    // SQLiteDatabase is the base class for working with a SQLite database
    // in Android and provides methods to open, query, update and close the database.
    // It provides the execSQL() method, which allows to execute an SQL statement directly.
    SQLiteDatabase db;
    // A query returns a Cursor object. A Cursor represents the result of a query and basically
    // points to one row of the query result. This way Android can buffer the query results efficiently;
    // as it does not have to load all data into memory.
    Cursor cursor;
    AlertDialog.Builder builder;
    StringBuffer buffer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sqlite_guided);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        init();
        addStudentRecord();
        deleteStudentRecord();
        editStudentRecord();
        viewStudentRecord();
        viewAllStudentRecords();
    }

    public void init() {
        studentId = findViewById(R.id.etStudentID);
        studentName = findViewById(R.id.etStudentName);
        studentSemGrade = findViewById(R.id.etStudentSemGrade);
        addRecord = findViewById(R.id.btnAddRecord);
        deleteRecord = findViewById(R.id.btnDeleteRecord);
        editRecord = findViewById(R.id.btnEditRecord);
        viewRecord = findViewById(R.id.btnViewRecord);
        viewAllRecord = findViewById(R.id.btnViewAllRecord);
        builder = new AlertDialog.Builder(this);
        // openOrCreateDatabase()
        // It is used to open the database if it exists or create a new one if it does not exist.
        // The first parameter specifies the name of the database to be opened or created.
        // The second parameter, Context.MODE_PRIVATE indicates that the database file can only
        // be accessed by the calling application or all applications sharing the same user ID.
        // The third parameter is a Cursor factory object which can be left null if not required.
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        // execSQL()
        // it is used to execute any SQL command. Here it is used to create the student table
        // if it does not already exist in the database.
        db.execSQL("DROP TABLE IF EXISTS tbl_student;");
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_student (student_id INTEGER PRIMARY KEY AUTOINCREMENT, student_name TEXT, student_semGrade INTEGER);");
        studentId.setEnabled(false);
        studentName.requestFocus();
    }

    public void displayMessage(String title, String message) {
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearEntries() {
        studentName.setText("");
        studentId.setText("");
        studentSemGrade.setText("");
        studentName.requestFocus();
    }

    public void addStudentRecord() {
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (studentName.getText().toString().isEmpty() ||
                        studentSemGrade.getText().toString().isEmpty()) {
                    displayMessage("Error!", "Please Complete the Entries");
                    return;
                }
                db.execSQL("INSERT INTO tbl_student(student_name,student_semGrade) " +
                        "VALUES('" + studentName.getText().toString() + "'" + "," +
                        "'" + studentSemGrade.getText().toString() + "')");
                displayMessage("Information!", "Student Record has been successfully added!");
                clearEntries();
            }
        });
    }

    public void deleteStudentRecord() {
        deleteRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (studentId.getText().toString().isEmpty()) {
                    studentId.setEnabled(true);
                    displayMessage("Information!", "Please Enter a Student ID");
                    studentId.requestFocus();
                    return;
                }
                // rawQuery() It directly accepts an SQL select statement as input.
                cursor = db.rawQuery("SELECT * FROM tbl_student WHERE student_id =" + Integer.parseInt(studentId.getText().toString()), null);
                if (cursor.moveToFirst()) {
                    db.execSQL("DELETE FROM tbl_student WHERE student_id =" + Integer.parseInt(studentId.getText().toString()));
                    displayMessage("Information!", "Student Record has been successfully deleted!");
                } else {
                    displayMessage("Error!", "Invalid Student ID");
                }
                studentId.setEnabled(false);
                clearEntries();
            }
        });
    }

    public void editStudentRecord() {
        editRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (studentId.getText().toString().isEmpty()) {
                    studentId.setEnabled(true);
                    displayMessage("Information!", "Please Enter a Student ID");
                    editRecord.setText("SAVE");
                    studentId.requestFocus();
                    return;
                }
                cursor = db.rawQuery("SELECT * FROM tbl_student WHERE student_id =" + Integer.parseInt(studentId.getText().toString()), null);
                if (cursor.moveToFirst()) {
                    db.execSQL("UPDATE tbl_student SET studentName = '"+studentName.getText().toString()+"', studentSemGrade = '"+studentSemGrade.getText().toString()+
                            "'WHERE student_id='" + Integer.parseInt(studentId.getText().toString()) + "'");
                    displayMessage("Information!", "Student Record has been successfully modified!");
                } else {
                    displayMessage("Error!", "Invalid Student ID");
                }
                editRecord.setText("EDIT");
                studentId.setEnabled(false);
                clearEntries();
            }
        });
    }

    public void viewStudentRecord() {
        viewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (studentId.getText().toString().isEmpty()) {
                    studentId.setEnabled(true);
                    displayMessage("Information!", "Please Enter a Student ID");
                    studentId.requestFocus();
                    return;
                }
                cursor = db.rawQuery("SELECT * FROM tbl_student WHERE student_id =" + Integer.parseInt(studentId.getText().toString()), null);
                if (cursor.moveToFirst()) {
                    buffer = new StringBuffer();
                    buffer.append("Student ID: " + cursor.getString(0) + "\n");
                    buffer.append("Student Name: " + cursor.getString(1) + "\n");
                    buffer.append("Student SemGrade: " + cursor.getString(2) + "\n----------");
                    displayMessage("Student Record", buffer.toString());
                } else {
                    displayMessage("Error!", "Invalid Student ID");
                }
                studentId.setEnabled(false);
                clearEntries();
            }
        });
    }

    public void viewAllStudentRecords() {
        viewAllRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor = db.rawQuery("SELECT * FROM tbl_student", null);
                if (cursor.getCount() == 0) {
                    displayMessage("Information", "No Student Records Found!");
                    return;
                }
                buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("Student ID: " + cursor.getString(0) + "\n");
                    buffer.append("Student Name: " + cursor.getString(1) + "\n");
                    buffer.append("Student SemGrade: " + cursor.getString(2) + "\n----------\n");
                }
                displayMessage("Student Record", buffer.toString());
            }
        });
    }
}