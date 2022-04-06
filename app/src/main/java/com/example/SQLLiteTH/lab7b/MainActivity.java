package com.example.SQLLiteTH.lab7b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.lab7a.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler databaseHandler;
    ListView lvPlace;
    List<Place> placeList;
    List<String> nameList=new ArrayList<>();
    TextInputEditText txtName;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        databaseHandler=new DatabaseHandler(this);
        placeList= databaseHandler.getPlaceList();
        for (Place place: placeList) {
            nameList.add(place.getName());
        }
        lvPlace=(ListView) findViewById(R.id.lvPlace);
        PlaceAdapter placeAdapter=new PlaceAdapter(this, nameList, R.layout.list_item_place);
        lvPlace.setAdapter(placeAdapter);

        txtName=(TextInputEditText) findViewById(R.id.txtName);
        btnSave=(Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=txtName.getText().toString();
                databaseHandler.addPlace(new Place(name));
                placeAdapter.addName(name);
                lvPlace.setAdapter(placeAdapter);
            }
        });
    }
}