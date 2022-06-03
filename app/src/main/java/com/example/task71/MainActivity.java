package com.example.task71;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void display_adverts(View view)
    {
        Intent intent1 = new Intent(this, MainActivity_2.class);
        startActivity(intent1);
    }

    public void create(View view) {
        Intent intent2 = new Intent(MainActivity.this, Create_Advert.class);
        startActivity(intent2);
    }

}