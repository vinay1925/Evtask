package com.example.evtask;

import static com.example.evtask.Register.MyPREFERENCES;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText lphno, lpassword;
    Button lbutton;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        lphno = findViewById(R.id.lphNo);
        lpassword = findViewById(R.id.lPassword);
        lbutton = findViewById(R.id.buttonlogin);

        lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = lphno.getText().toString();
                String password = lpassword.getText().toString();
                String sharedPhone = sharedPreferences.getString("Phone", "");
                String sharedPassword = sharedPreferences.getString("Password", "");


                if (TextUtils.isEmpty(phoneNo)) {
                    lphno.setError("phone number is required to login");
                } else if (TextUtils.isEmpty(password)) {
                    lpassword.setError("password is required to login");
                } else if (phoneNo.equals(sharedPhone) && password.equals(sharedPassword)) {
                    Toast.makeText(Login.this, "welcome", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);

                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "invalid credentials", Toast.LENGTH_SHORT).show();

                }
            }

        });

    }
}
