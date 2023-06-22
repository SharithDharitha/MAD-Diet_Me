package com.example.addmeal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    private EditText loginUsername , loginPassword;
    private TextView forgot;
    private Button loginButton , backToSignUp;
    private MyDatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button btn2 = (Button)findViewById(R.id.ToSignUp);


        btn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(login.this, signup.class));
            }
        });

        loginUsername = findViewById(R.id.loginusername);
        loginPassword = findViewById(R.id.loginpassword);
        loginButton = findViewById(R.id.loginbutton);

        myDb = new MyDatabaseHelper(this);
        loginUser();

        // forgot password

        forgot = findViewById(R.id.btnForgot);


        forgot.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(login.this, PasswordActivity.class));
            }
        });




    }
    private void loginUser(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean var = myDb.checkUser(loginUsername.getText().toString(), loginPassword.getText().toString());
                if (var){
                    Toast.makeText(login.this,"Login Successfully !!" , Toast.LENGTH_SHORT).show();
                    finish();

                    //add after login
                   Intent intent = new Intent(login.this, Add.class);
//                    intent.putExtra("name", loginUsername.getText().toString());
                   startActivity(intent);
                }else{
                    Toast.makeText(login.this,"Login failed !!" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}