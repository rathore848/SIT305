package com.example.task71.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class databaseclass extends SQLiteOpenHelper {

    Context context;
    public static final String DatabaseName = "7.1";
    public static final int DatabaseVersion = 1;

    public static final String TableName = "ADVERT";
    public static final String ColumnId = "id";
    public static final String ColumnTitle = "title";
    public static final String ColumnPhone = "phone";
    public static final String ColumnDescription = "description";
    public static final String ColumnDate = "date";
    public static final String ColumnLocation = "location";

    public databaseclass(@Nullable Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TableName +
                " (" + ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ColumnTitle + " TEXT, " + ColumnPhone + " TEXT, " + ColumnDescription + " TEXT, " + ColumnDate + " TEXT, " + ColumnLocation + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    public void insertdata(String title, String phone, String description, String date, String location)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ColumnTitle, title);
        cv.put(ColumnPhone, phone);
        cv.put(ColumnDescription, description);
        cv.put(ColumnDate, date);
        cv.put(ColumnLocation, location);

        long result = db.insert(TableName,null,cv);
        if (result == -1)
        {
            Toast.makeText(context, "Data not stored", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Data insertion done successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData()
    {
        String query = "SELECT * FROM " + TableName;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;
        if(database!= null)
        {
            cursor = database.rawQuery(query,null);
        }
        return cursor;
    }

    public void deleteRow(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long RESULT = db.delete(TableName,"id=?", new String[]{id});

        if(RESULT == -1)
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}