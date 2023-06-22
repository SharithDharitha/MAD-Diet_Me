package com.example.addmeal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Update extends AppCompatActivity {
    EditText  category, foodName, address;
    Button btn_update,btn_delete;

    String Item_id, Category, FoodName_Type, Address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        category = findViewById(R.id.et_up_mName1);
        foodName =  findViewById(R.id.et_up_mName2);
        address =  findViewById(R.id.et_up_mName3);

        btn_update =  findViewById(R.id.btn_update);
        btn_delete =findViewById(R.id.btn_delete);
        getAndSetIntentData();

        ActionBar ab =getSupportActionBar();
        if (ab != null) {
            ab.setTitle(Category);
        }

        btn_update.setOnClickListener(view -> {
            MyDatabaseHelper db = new MyDatabaseHelper(Update.this);
            Category = category.getText().toString().trim();
            FoodName_Type = foodName.getText().toString().trim();
            Address =  address.getText().toString().trim();
            db.UpdateData(Item_id, Category, FoodName_Type,Address);
            finish();
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConfirmDelete();
            }
        });

    }

    void getAndSetIntentData(){
        if (getIntent().hasExtra("Item_id") && getIntent().hasExtra("Category") &&
                getIntent().hasExtra("FoodName_Type") && getIntent().hasExtra("Address")){

            //get data  from intent
            Item_id = getIntent().getStringExtra("Item_id");
            Category = getIntent().getStringExtra("Category");
            FoodName_Type = getIntent().getStringExtra("FoodName_Type");
            Address = getIntent().getStringExtra("Address");

            //set intent data
                //category, foodName, address
            category.setText(Category);
            foodName.setText(FoodName_Type);
            address.setText(Address);


        }else{
            Toast.makeText(this, "No data . ", Toast.LENGTH_SHORT).show();
        }
    }

    void ConfirmDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + Category + "?");
        builder.setMessage("Are you sure you want to delete "+Category+" Account ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Update.this);
                myDB.deleteOneRow(Item_id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }
}
