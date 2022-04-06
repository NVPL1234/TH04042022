package com.example.SQLLiteTH.lab7a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lab7a.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvName;
    DatabaseHandler databaseHandler;
    List<Person> personList;
    List<String> nameList=new ArrayList<>();
    Button btnAdd;
    Button btnRemove;
    Button btnCancel;
    EditText txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler=new DatabaseHandler(this);
        personList=databaseHandler.getPersonList();
        for (Person person: personList) {
            nameList.add(person.getName());
        }

        lvName=(ListView) findViewById(R.id.listName);
        ArrayAdapter arrayAdapter =new ArrayAdapter(this, android.R.layout.simple_list_item_1, nameList);
        lvName.setAdapter(arrayAdapter);

        txtName=(EditText) findViewById(R.id.txtName);
        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnRemove=(Button) findViewById(R.id.btnRemove);
        btnCancel=(Button) findViewById(R.id.btnCancel);
        lvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                txtName.setText(arrayAdapter.getItem(i).toString());
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=txtName.getText().toString();
                databaseHandler.addPerson(new Person(name));
                arrayAdapter.add(name);
                lvName.setAdapter(arrayAdapter);
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=txtName.getText().toString();
                databaseHandler.deletePerson(name);
                arrayAdapter.remove(name);
                lvName.setAdapter(arrayAdapter);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtName.setText("");
            }
        });
    }
}