package com.example.task71;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.task71.data.databaseclass;

public class Display extends AppCompatActivity {

    EditText title, contact, desc, date, loc;
    Button delete;
    String i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);

        title = findViewById(R.id.title);
        desc = findViewById(R.id.description);
        contact = findViewById(R.id.contact);
        date = findViewById(R.id.date);
        loc = findViewById(R.id.location);
        delete = findViewById(R.id.deleteButton);

        Intent i = getIntent();
        title.setText(i.getStringExtra("title"));
        contact.setText(i.getStringExtra("phone"));
        desc.setText(i.getStringExtra("description"));
        date.setText(i.getStringExtra("date"));
        loc.setText(i.getStringExtra("location"));
        this.i = i.getStringExtra("id");
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void confirmDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title.getText().toString() + " ?");
        builder.setMessage("Do you want to continue after DELETION " + title.getText().toString() + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int check) {
                databaseclass db2 = new databaseclass(Display.this);
                db2.deleteRow(i);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int check) {
            }
        });
        builder.create().show();
    }
}
