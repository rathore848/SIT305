package com.example.task71;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
import com.example.task71.data.databaseclass;
import com.example.task71.model.Model;
import java.util.ArrayList;
import java.util.List;

public class MainActivity_2 extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    List<Model> list;
    databaseclass database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recyclerview);
        list = new ArrayList<>();
        database = new databaseclass(this);
        fetchAllNotesFromDatabase();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, MainActivity_2.this, list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int RequestCode, int ResultCode, @Nullable Intent data) {
        super.onActivityResult(RequestCode, ResultCode, data);
        if (RequestCode == 1){
            recreate();
        }
    }

    void fetchAllNotesFromDatabase()
    {
        Cursor cursor = database.readAllData();
        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                list.add(new Model(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            }
        }
    }

}
