package com.mihirvp.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

import static com.mihirvp.sqlitetest.DatabaseHelper.*;

public class MainActivity extends AppCompatActivity {


    //references to Variables
    EditText textURl,textPassword;
    Button btnAdd, btnDisplayAll;
    Switch activeField;
    ListView passList;

    ArrayAdapter storeArrayAdapter;

    DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add ID's

        //Buttons
        btnAdd = findViewById(R.id.button_add);
        btnDisplayAll = findViewById(R.id.button_display);
        //Text fields
        textPassword = findViewById(R.id.editTextTextPassword);
        textURl = findViewById(R.id.editTextTextURL);
        //switch
        activeField = findViewById(R.id.switch_update);
        //Password list
        passList = findViewById(R.id.passList);


        //database helper

         dbh = new DatabaseHelper(MainActivity.this);
        //Show in table : display items on start

        showPublicAdapter();

        //add onclickListener (Toast TEST)
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Add Button Clicked",Toast.LENGTH_SHORT).show();

                //Add Activity To button
                //this is how hardcoded storage looks like: StoreModel storeModel = new StoreModel(1,"www.gmail.google.com","Pvmlaer",true);



                StoreModel storeModel;
                try {

                        storeModel = new StoreModel(1, textURl.getText().toString(), textPassword.getText().toString(), activeField.isChecked());

                        //add new tables I guess

                        //Toast.makeText(MainActivity.this, StoreModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                        Toast.makeText(MainActivity.this, "Add Proper Values", Toast.LENGTH_SHORT).show();

                        storeModel = new StoreModel(-1, "NoNew","EMPTY",false);

                }

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                boolean added = databaseHelper.addOne(storeModel);

                Toast.makeText(MainActivity.this,"Successful Status: "+ added, Toast.LENGTH_SHORT).show();

                showPublicAdapter();


            }
        });

        btnDisplayAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Display Button Clicked",Toast.LENGTH_SHORT).show();

                //Add Activity To button

                List<StoreModel> allOfThem = dbh.getEveryone();


                //Toast.makeText(MainActivity.this, allOfThem.toString() , Toast.LENGTH_SHORT).show();

                dbh = new DatabaseHelper(MainActivity.this);

                showPublicAdapter();


            }
        });

        passList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                StoreModel clickedURL = (StoreModel) parent.getItemAtPosition(position);


                dbh.deleteOne(clickedURL);


            }
        });

    }

    public void showPublicAdapter() {
        storeArrayAdapter = new ArrayAdapter<StoreModel>(MainActivity.this, android.R.layout.simple_selectable_list_item, dbh.getEveryone());
        passList.setAdapter(storeArrayAdapter);
    }


}