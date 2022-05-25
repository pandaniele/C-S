package com.example.server;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=(Button)findViewById(R.id.button3);

        username=(EditText)findViewById(R.id.editTextTextPersonName);

        password=(EditText)findViewById(R.id.editTextTextPersonName2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.getText();
                password.getText();
                if(username.length()>0 & password.length()>0){
               Intent i;
               i=new Intent(getApplicationContext(),)
                }
            }
        });
    }
}