package com.example.task71;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
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
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Create_Advert extends AppCompatActivity {

    private RadioGroup radio;
    String name;
    TextView tView;
    EditText title, desc, phone, date, loc;
    Button savebutton, getloc;
    FusedLocationProviderClient flpclient;

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
        getloc = findViewById(R.id.getloc_button);
        radio = (RadioGroup)findViewById(R.id.type);
        radio.clearCheck();

        flpclient = LocationServices.getFusedLocationProviderClient(this);
        Places.initialize(getApplicationContext(), "AIzaSyDwgFF3dGoycxKEZQMts4iUFF4ZSnbr5ws");
        loc.setFocusable(false);
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS,Place.Field.LAT_LNG,Place.Field.NAME);

                Intent intent1 = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,fieldList).build(AddNotesActivity.this);
                startActivityForResult(intent1,100);
            }
        });

        getloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if (ActivityCompat.checkSelfPermission(Create_Advert.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                   loc_finder();}
             else {
                    ActivityCompat.requestPermissions(Create_Advert.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
               }
            }
        });

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup x, int check)
                    {
                        RadioButton rbutton = (RadioButton)x.findViewById(check);
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
    private void loc_finder() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        flpclient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location y = task.getResult();
                if (y != null) {
                    try {
                        Geocoder geocoder = new Geocoder(Create_Advert.this,
                                Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(y.getLatitude(), y.getLongitude(), 1);
                        String address = addresses.get(0).getAddressLine(0);
                        y.setText(address);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int RequestCode, int ResultCode, @Nullable Intent xy) {
        super.onActivityResult(RequestCode, ResultCode, xy);
        if (RequestCode == 100 && ResultCode == RESULT_OK)
        {
            Place place = Autocomplete.getPlaceFromIntent(xy);
            loc.setText(place.getAddress());
        }
        else if (ResultCode == AutocompleteActivity.RESULT_ERROR)
        {
            Status status = Autocomplete.getStatusFromIntent(xy);
            Toast.makeText(getApplicationContext(),status.getStatusMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
