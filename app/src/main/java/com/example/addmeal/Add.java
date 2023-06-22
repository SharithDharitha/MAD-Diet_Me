package com.example.addmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add extends AppCompatActivity {

    EditText  category_input,foodName_input, address_input;
    Button btn_save, btn_View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);



        category_input = findViewById(R.id.et_mName1);
        foodName_input = findViewById(R.id.et_mName2);
        address_input = findViewById(R.id.et_mName4);


        btn_save = findViewById(R.id.btn_save);
        btn_View = findViewById(R.id.btn_View);



        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateCategory() | !validateFoodName() | !validateAddress()) {
                    return;
                }


                MyDatabaseHelper myDB = new MyDatabaseHelper(Add.this);
                myDB.addItem(


                        category_input.getText().toString().trim(),
                        foodName_input.getText().toString().trim(),
                        address_input.getText().toString().trim());

            }
        });
        btn_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Add.this);


                Intent intent = new Intent(Add.this, View_list.class);
                startActivity(intent);

            }
        });


    }
    private boolean validateCategory() {
        String val = category_input.getText().toString().trim();
        if (val.isEmpty()) {
            category_input.setError("Field can not be empty");
            return false;
        } else {
            category_input.setError(null);
            category_input.setEnabled(false);
            return true;
        }
    }
    private boolean validateFoodName() {
        String val = foodName_input.getText().toString().trim();
        if (val.isEmpty()) {
            foodName_input.setError("Field can not be empty");
            return false;
        } else {
            foodName_input.setError(null);
            foodName_input.setEnabled(false);
            return true;
        }
    }

    private boolean validateAddress() {
        String val = address_input.getText().toString().trim();
        if (val.isEmpty()) {
            address_input.setError("Field can not be empty");
            return false;
        } else {
            address_input.setError(null);
            address_input.setEnabled(false);
            return true;
        }
    }

}
