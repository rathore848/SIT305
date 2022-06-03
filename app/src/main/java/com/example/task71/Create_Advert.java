package com.example.task71;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.task71.data.databaseclass;

public class Create_Advert extends AppCompatActivity {

    private RadioGroup radio;
    String name;
    TextView tView;
    EditText title, desc, phone, date, loc;
    Button savebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity);

        tView = findViewById(R.id.textView);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.description);
        phone = findViewById(R.id.contact);
        date = findViewById(R.id.date);
        loc = findViewById(R.id.location);
        savebutton = findViewById(R.id.save_button);
        radio = (RadioGroup)findViewById(R.id.type);
        radio.clearCheck();

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup x, int checkedId)
                    {
                        RadioButton rbutton = (RadioButton)x.findViewById(checkedId);
                        name = rbutton.getText().toString();
                    }
                });

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(desc.getText().toString()) && !TextUtils.isEmpty(phone.getText().toString()) && !TextUtils.isEmpty(date.getText().toString()) && !TextUtils.isEmpty(loc.getText().toString()))
                {
                    databaseclass db = new databaseclass(Create_Advert.this);
                    db.insertdata((name + " " + title.getText().toString()),phone.getText().toString(), desc.getText().toString(),date.getText().toString(), loc.getText().toString());
                    Intent intent = new Intent(Create_Advert.this, MainActivity_2.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(Create_Advert.this, "Both Fields are Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
