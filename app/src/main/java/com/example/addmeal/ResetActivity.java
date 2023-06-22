package com.example.addmeal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResetActivity extends AppCompatActivity {

    TextView username;
    EditText pass , repass;
    Button confirm;
    MyDatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        username = findViewById(R.id.username_reset_text);
        pass = findViewById(R.id.password_reset);
        repass = findViewById(R.id.repassword_reset);
        confirm = findViewById(R.id.btnconfirm);
        db = new MyDatabaseHelper(this);

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();

               // Log.d("username>>>>>>",user);
               // Log.d("password>>>>>>",password);

                if (password.equals(repassword)) {


                    boolean checkpasswordupdate = db.updatepassword(user, password);
                    if (checkpasswordupdate) {
                        Intent intent = new Intent(getApplicationContext(), login.class);
                        startActivity(intent);
                        Toast.makeText(ResetActivity.this, "Password updated Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResetActivity.this, "Password Not updated Successfully", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ResetActivity.this, "Password Not Matching", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}