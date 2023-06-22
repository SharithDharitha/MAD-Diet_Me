package com.example.addmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class View_list extends AppCompatActivity {
    RecyclerView recyclerView;

    MyDatabaseHelper myDB;
    ArrayList<String> Item_id,Category, Food_Name, Address;
    com.example.addmeal.ViewListAdapter ViewListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        recyclerView = findViewById(R.id.account_recycler);


        myDB = new MyDatabaseHelper(View_list.this);
        Item_id = new ArrayList<>();
        Category = new ArrayList<>();
        Food_Name = new ArrayList<>();
        Address = new ArrayList<>();



        storeDataInArrays ();

        ViewListAdapter = new ViewListAdapter(View_list.this, this,Item_id,Category, Food_Name, Address);
        recyclerView.setAdapter(ViewListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(View_list.this));

    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data", Toast.LENGTH_SHORT) .show();
        }else{
            while (cursor.moveToNext()){
                Item_id.add(cursor.getString(0));
                Category.add(cursor.getString(1));
                Food_Name.add(cursor.getString(2));
                Address.add(cursor.getString(3));
            }
        }
    }

}