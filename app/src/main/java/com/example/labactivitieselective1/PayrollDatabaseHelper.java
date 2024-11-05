package com.example.labactivitieselective1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class PayrollDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "payroll_table";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_EMPLOYEEID = "employee_id";
    private static final String COLUMN_NAME = "employee_name";
    private static final String COLUMN_POSITION = "position_code";
    private static final String COLUMN_CIVILSTATUS = "civil_status";
    private static final String COLUMN_DAYSWORKED = "days_worked";
    private static final String COLUMN_BASICPAY = "basic_pay";
    private static final String COLUMN_SSSCONTRIBUTION = "sss_contribution";
    private static final String COLUMN_WITHHOLDINGTAX = "withholding_tax";
    private static final String COLUMN_NETPAY = "net_pay";

    public PayrollDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMPLOYEEID + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_POSITION + " TEXT, " +
                COLUMN_CIVILSTATUS + " TEXT, " +
                COLUMN_DAYSWORKED + " TEXT, " +
                COLUMN_BASICPAY + " REAL, " +
                COLUMN_SSSCONTRIBUTION + " REAL, " +
                COLUMN_WITHHOLDINGTAX + " REAL, " +
                COLUMN_NETPAY + " REAL);";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertPayrollData(String employeeID, String employeeName, String positionCode, String civilStatus, String daysWorked, double basicPay, double sssContribution, double withholdingTax, double netPay) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_EMPLOYEEID, employeeID);
        values.put(COLUMN_NAME, employeeName);
        values.put(COLUMN_POSITION, positionCode);
        values.put(COLUMN_CIVILSTATUS, civilStatus);
        values.put(COLUMN_DAYSWORKED, daysWorked);
        values.put(COLUMN_BASICPAY, basicPay);
        values.put(COLUMN_SSSCONTRIBUTION, sssContribution);
        values.put(COLUMN_WITHHOLDINGTAX, withholdingTax);
        values.put(COLUMN_NETPAY, netPay);

        long result = db.insert(TABLE_NAME, null, values);
        Toast.makeText(context, result == -1 ? "Failed insertion" : "Insertion successful", Toast.LENGTH_SHORT).show();
        db.close();
    }

    public Cursor getEmployeeDataByID(String employeeID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EMPLOYEEID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{employeeID});
        return cursor;
    }
}
